package com.example.bazar_uft;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Venta extends AppCompatActivity {
    private String NombreProducto;
    private String CodigoProducto;
    private int Precio;
    private String TipoBoleta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venta);
    }

    public void guardarVenta(View view) {
        try {
            EditText auxNombreProducto = findViewById(R.id.NombreProductoVenta);
            EditText auxCodigoProducto = findViewById(R.id.CodigoProductoVenta);
            EditText auxPrecio = findViewById(R.id.Precio);
            String auxTipoBoleta="Venta";


            Venta auxVenta = new Venta();
            auxVenta.setNombreProducto(auxNombreProducto.getText().toString());
            auxVenta.setCodigoProducto(auxCodigoProducto.getText().toString());
            auxVenta.setPrecio(Integer.parseInt(auxPrecio.getText().toString()));
            auxVenta.setTipoBoleta(auxTipoBoleta);

            Castañito auxBazarUFT = new Castañito(this);

            auxBazarUFT.insertarVenta(auxVenta);
            auxNombreProducto.setText("");
            auxCodigoProducto.setText("");
            auxPrecio.setText("");
            this.mensaje("Venta Registrada");
        }
        catch (Exception ex)
        {
            this.mensaje("No se registro la venta"+ex.getMessage());
        }

    }
    public void mensaje(String auxMensaje)
    {
        Toast.makeText(this, auxMensaje, Toast.LENGTH_LONG).show();
    }


    public void volver2(View view) {
        Intent auxIntent = new Intent(this,MainActivity.class);
        startActivity(auxIntent);
    }



    public String getNombreProducto() {
        return NombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        NombreProducto = nombreProducto;
    }

    public String getCodigoProducto() {
        return CodigoProducto;
    }

    public void setCodigoProducto(String codigoProducto) {
        CodigoProducto = codigoProducto;
    }


    public int getPrecio() {
        return Precio;
    }

    public void setPrecio(int precio) {
        Precio = precio;
    }

    public String getTipoBoleta() {
        return TipoBoleta;
    }

    public void setTipoBoleta(String tipoBoleta) {
        TipoBoleta = tipoBoleta;
    }

    public void ventasTotal(View view) {
        Castañito auxBdd = new Castañito(this);
        Integer BoletasVenta = auxBdd.contarVentas("Venta");

        TextView msgVentas = findViewById(R.id.txtVentasTotales);

        msgVentas.setText("El numero de ventas totales es de: "+BoletasVenta.toString());
    }
}