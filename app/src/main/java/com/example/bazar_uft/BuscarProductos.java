package com.example.bazar_uft;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class BuscarProductos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_productos);
    }

    public void BuscarProductos(View view) {
        EditText auxnombreproducto = findViewById(R.id.txtNombreProducto);

        Castañito auxBazarUFT= new Castañito(this);

        Compra auxproducto = auxBazarUFT.buscarProducto(auxnombreproducto.getText().toString());
        ArrayList<String > auxListcompra = new ArrayList<String>();
        auxListcompra.add(auxproducto.getNombreProducto());
        auxListcompra.add(auxproducto.getCodigoProducto());

        ListView auxListview = findViewById(R.id.MostrarProducto);
        auxListview.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, auxListcompra));
        auxnombreproducto.setText("");

    }
}