package com.example.bazar_uft;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class IngresarCliente extends AppCompatActivity {
    private String Nombre;
    private String Rut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresar_cliente_ws);
    }


    public void guardarCliente(View view) {
        try {
            EditText auxrut = (EditText) findViewById(R.id.txtRut);
            EditText auxNombre = (EditText) findViewById(R.id.txtNombre);

            String nombre = auxNombre.getText().toString();
            String rut = auxrut.getText().toString();

            IngresarCliente auxcliente = new IngresarCliente();
            auxcliente.setNombre(nombre);
            auxcliente.setRut(rut);

            Castañito auxBazarUFT = new Castañito(this);
            auxBazarUFT.insertarCliente(auxcliente);

            auxNombre.setText("");
            auxrut.setText("");

            this.mensaje("Datos Grabados");
        } catch(Exception ex) {
            this.mensaje("Datos No Grabados " + ex.getMessage());
        }
    }
    public void mensaje(String auxMensaje)
    {
        Toast.makeText(this, auxMensaje, Toast.LENGTH_LONG).show();
    }


    public void MostrarClientes(View view) {
        try {
            Castañito auxBazarUFT = new Castañito(this);

            List<String> auxListaClientes = auxBazarUFT.retornarclientes(); // Asignar el resultado a una variable

            ListView auxListView = findViewById(R.id.listaClientes);
            auxListView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, auxListaClientes));
        } catch (Exception ex) {
            mensaje("Error al mostrar clientes: " + ex.getMessage());
        }


}
    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getRut() {
        return Rut;
    }

    public void setRut(String Rut) {
        this.Rut = Rut;
    }
}

