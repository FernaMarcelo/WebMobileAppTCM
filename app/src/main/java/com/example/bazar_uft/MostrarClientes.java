package com.example.bazar_uft;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bazar_uft.DBHelper;
import com.example.bazar_uft.R;

import java.util.ArrayList;

public class MostrarClientes extends AppCompatActivity {

    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mostrar_cliente);

        // Inicializar el DBHelper
        dbHelper = new DBHelper(this);
    }

    public void accederBaseDatos(View view) {
        try {
            // Obtener una instancia de la base de datos SQLite
            SQLiteDatabase db = dbHelper.getReadableDatabase();

            // Realizar la consulta en la base de datos para obtener los clientes
            Cursor cursor = db.rawQuery("SELECT * FROM clientes", null);

            // Crear un ArrayList para almacenar los datos de los clientes
            ArrayList<String> clientes = new ArrayList<>();

            // Recorrer el cursor y obtener los datos de los clientes
            if (cursor.moveToFirst()) {
                do {
                    @SuppressLint("Range") String nombre = cursor.getString(cursor.getColumnIndex("nombre"));
                    @SuppressLint("Range") String rut = cursor.getString(cursor.getColumnIndex("rut"));
                    // Agregar los datos del cliente al ArrayList
                    clientes.add(nombre + " - " + rut);
                } while (cursor.moveToNext());
            }

            // Cerrar el cursor y la conexión a la base de datos
            cursor.close();
            db.close();

            // Obtener una referencia al ListView con el id listaClientes
            ListView auxListView = findViewById(R.id.listaClientes);

            // Crear un ArrayAdapter con los datos de los clientes y asignarlo al ListView
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, clientes);
            auxListView.setAdapter(adapter);
        } catch (Exception ex) {
            mensaje("Datos no mostrados");
        }
    }

    private void mensaje(String mensaje) {
        // Implementa la lógica para mostrar un mensaje en la aplicación
    }
}
