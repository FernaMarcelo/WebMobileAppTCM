package com.example.bazar_uft;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Compra extends AppCompatActivity {

    private String NombreProducto;
    private String Proveedor;
    private String CodigoProducto;
    private String TipoBoleta;
    private ImageView img;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compra);

        img = (ImageView) findViewById(R.id.imageView);

        //Validacion de permisos
        if (ContextCompat.checkSelfPermission(Compra.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(Compra.this, Manifest.permission.CAMERA) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(Compra.this, new String[]
                    {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, 1000);
        }


    }

    String currentPhotoPath;

    //Metodo para crear un nombre unico para cada fotografia
    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "Backup_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            img.setImageBitmap(imageBitmap);
        }
    }

    static final int REQUEST_TAKE_PHOTO = 1;

    public void guardarCompra(View view) {
        try {

            EditText auxNombreProducto = findViewById(R.id.NombreProducto);
            EditText auxProveedor = findViewById(R.id.Proveedor);
            EditText auxCodigoProducto = findViewById(R.id.CodigoProducto);
            String auxTipoBoleta = "Compra";


            Compra auxCompra = new Compra();
            auxCompra.setNombreProducto(auxNombreProducto.getText().toString());
            auxCompra.setProveedor(auxProveedor.getText().toString());
            auxCompra.setCodigoProducto(auxCodigoProducto.getText().toString());
            auxCompra.setTipoBoleta(auxTipoBoleta);

            BD_BazarUFT auxBazarUFT = new BD_BazarUFT(this);
            auxBazarUFT.insertarCompra(auxCompra);

            auxNombreProducto.setText("");
            auxProveedor.setText("");
            auxCodigoProducto.setText("");
            this.mensaje("Compra Registrada");

        } catch (Exception ex) {
            this.mensaje("No se registro la compra" + ex.getMessage());
        }

    }

    public void mensaje(String auxMensaje) {
        Toast.makeText(this, auxMensaje, Toast.LENGTH_LONG).show();
    }

    public void volver1(View view) {
        Intent auxIntent = new Intent(this, MainActivity.class);
        startActivity(auxIntent);
    }

    public String getNombreProducto() {
        return NombreProducto;
    }

    public String setNombreProducto(String nombreProducto) {
        NombreProducto = nombreProducto;
        return nombreProducto;
    }

    public String getProveedor() {
        return Proveedor;
    }

    public String setProveedor(String proveedor) {
        Proveedor = proveedor;
        return proveedor;
    }

    public String getCodigoProducto() {
        return CodigoProducto;
    }

    public String setCodigoProducto(String codigoProducto) {
        CodigoProducto = codigoProducto;
        return codigoProducto;
    }


    public void comprasTotal(View view) {
        BD_BazarUFT auxBdd = new BD_BazarUFT(this);
        Integer BoletasCompra = auxBdd.contarCompras("Compra");

        TextView msgCompras = findViewById(R.id.txtComprasTotales);

        msgCompras.setText("El numero de compras totales es de: " + BoletasCompra.toString());

    }

    public String getTipoBoleta() {
        return TipoBoleta;
    }

    public void setTipoBoleta(String tipoBoleta) {
        TipoBoleta = tipoBoleta;
    }

    //Metodo para tomar la fotografia
    public void tomarFoto(View view) {
        //Le indica a la app que cerrara de manera momentania la activity actual y mostrara lo que la camara esta viendo
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Verifica si se tomo una foto anteriormente
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {

            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {

            }
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.android.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }
}