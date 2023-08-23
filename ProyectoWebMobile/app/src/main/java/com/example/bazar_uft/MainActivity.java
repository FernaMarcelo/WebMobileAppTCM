package com.example.bazar_uft;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void compra(View view) {

        Intent  auxIntent = new Intent(this,Compra.class);
        startActivity(auxIntent);
    }

    public void venta(View view) {
        Intent  auxIntent = new Intent(this,Venta.class);
        startActivity(auxIntent);
    }

    public void comprasTotal(View view) {
        Intent  auxIntent = new Intent(this,ComprasTotal.class);
        startActivity(auxIntent);

    }

    public void ventasTotal(View view) {
        Intent  auxIntent = new Intent(this,VentasTotal.class);
        startActivity(auxIntent);
    }

    public void PantallaBuscarCompra(View view) {
        Intent  auxIntent = new Intent(this,BuscarCompra.class);
        startActivity(auxIntent);
    }

    public void PantallaBuscarVenta(View view) {
        Intent  auxIntent = new Intent(this,BuscarVenta.class);
        startActivity(auxIntent);
    }

    public void PantallaBuscarProducto(View view) {
        Intent  auxIntent = new Intent(this,BuscarProductos.class);
        startActivity(auxIntent);
    }

    public void Clientes(View view) {
        Intent  auxIntent = new Intent(this,IngresarClienteWS.class);
        startActivity(auxIntent);
    }

    public void Eliminar(View view) {
        Intent  auxIntent = new Intent(this,eliminarClienteWS.class);
        startActivity(auxIntent);
    }

    public void PantallaMostrarProveedores(View view) {
        Intent  auxIntent = new Intent(this,MostrarProveedores.class);
        startActivity(auxIntent);
    }
}