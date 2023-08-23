package com.example.bazar_uft;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class eliminarClienteWS extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_cliente);
    }
    public void eliminarCliente(View view) {
        try {

            EditText auxTxtRut = (EditText) findViewById(R.id.txtRutCliente);


            EliminarClienteConexion aux = new EliminarClienteConexion();
            String auxRut = auxTxtRut.getText().toString();
            aux.execute(String.valueOf(auxRut));
            this.mensaje("Cliente Eliminado");
        }
        catch(Exception ex)
        {
            this.mensaje("Cliente no eliminado " + ex.getMessage());

        }
    }
    public void mensaje(String auxMensaje)
    {
        Toast.makeText(this, auxMensaje, Toast.LENGTH_LONG).show();
    }
}