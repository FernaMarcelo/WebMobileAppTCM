package com.example.bazar_uft;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Compra extends AppCompatActivity {

    private String NombreProducto;
    private String Proveedor;
    private String CodigoProducto;
    private String TipoBoleta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compra);

    }


    public void guardarCompra(View view) {
        try {

        EditText auxNombreProducto = findViewById(R.id.NombreProducto);
        EditText auxProveedor = findViewById(R.id.Proveedor);
        EditText auxCodigoProducto = findViewById(R.id.CodigoProducto);
        String auxTipoBoleta="Compra";


        Compra auxCompra = new Compra();
        auxCompra.setNombreProducto(auxNombreProducto.getText().toString());
        auxCompra.setProveedor(auxProveedor.getText().toString());
        auxCompra.setCodigoProducto(auxCodigoProducto.getText().toString());
        auxCompra.setTipoBoleta(auxTipoBoleta);

        BD_BazarUFT auxBazarUFT= new BD_BazarUFT(this);
        auxBazarUFT.insertarCompra(auxCompra);

        auxNombreProducto.setText("");
        auxProveedor.setText("");
        auxCodigoProducto.setText("");
        this.mensaje("Compra Registrada");

    }
        catch (Exception ex)
    {
        this.mensaje("No se registro la compra"+ex.getMessage());
    }

    }

    public void mensaje(String auxMensaje)
    {
        Toast.makeText(this, auxMensaje, Toast.LENGTH_LONG).show();
    }

    public void volver1(View view) {
        Intent auxIntent = new Intent(this,MainActivity.class);
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

        msgCompras.setText("El numero de compras totales es de: "+BoletasCompra.toString());

    }

    public String getTipoBoleta() {
        return TipoBoleta;
    }

    public void setTipoBoleta(String tipoBoleta) {
        TipoBoleta = tipoBoleta;
    }
}