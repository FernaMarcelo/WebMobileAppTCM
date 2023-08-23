package com.example.bazar_uft;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class IngresarClienteWS extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresar_cliente_ws);
    }


    public void guardarCliente(View view) {
        try {

            EditText auxTxtRut = (EditText) findViewById(R.id.txtRut);
            EditText auxTxtNombre = (EditText) findViewById(R.id.txtNombre);


            IngresaClienteWservice aux = new IngresaClienteWservice();
            String auxRut = auxTxtRut.getText().toString();
            String auxNom = auxTxtNombre.getText().toString();
            aux.execute(String.valueOf(auxRut), auxNom);
            this.mensaje("Datos Grabados");
        }
        catch(Exception ex)
        {
            this.mensaje("Datos No Grabados " + ex.getMessage());

        }
    }
    public void mensaje(String auxMensaje)
    {
        Toast.makeText(this, auxMensaje, Toast.LENGTH_LONG).show();
    }


    public void MostrarClientes(View view) {

        try {

            consultaClienteWS aux = new consultaClienteWS();
            aux.execute();

            ListView auxListView = (ListView) findViewById(R.id.listaClientes);

            Thread.sleep(1000);

            auxListView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,aux.getStringArr()));

        }
        catch(Exception ex)
        {
            this.mensaje("Datos No Mostrado");

        }
    }
    /*public void eliminarCliente(View view) {
        try {

            EditText auxTxtRut = (EditText) findViewById(R.id.txtRut);
           // EditText auxTxtNombre = (EditText) findViewById(R.id.txtNombre);


            IngresaClienteWservice aux = new IngresaClienteWservice();
            String auxRut = auxTxtRut.getText().toString();
            String auxNom = auxTxtNombre.getText().toString();
            aux.execute(String.valueOf(auxRut), auxNom);
            this.mensaje("Datos Grabados");
        }
        catch(Exception ex)
        {
            this.mensaje("Datos No Grabados " + ex.getMessage());

        }
    }*/
}