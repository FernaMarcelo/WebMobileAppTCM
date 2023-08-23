package com.example.bazar_uft;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class MostrarProveedores extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_proveedores);
    }

    public void MostrarProveedores(View view) {
        Castañito auxBazarUFT = new Castañito(this);

        List<String> auxListaProveedores = auxBazarUFT.retornarProveedores();

        ListView auxListView = findViewById(R.id.ListViewProveedores);

        auxListView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, auxListaProveedores));
    }
}