package com.example.bazar_uft;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class BuscarVenta extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_venta);
    }

    public void BuscarVenta(View view ) {
        EditText auxid_venta = findViewById(R.id.txtBoletaVenta);

        Castañito auxBazarUFT= new Castañito(this);


        Venta auxVenta = auxBazarUFT.buscarVenta(Integer.parseInt(auxid_venta.getText().toString()));
        ArrayList<String > auxListventa = new ArrayList<String>();
        auxListventa.add(auxVenta.getNombreProducto());
        auxListventa.add(auxVenta.getCodigoProducto());
        auxListventa.add(Integer.toString(auxVenta.getPrecio()));




        ListView auxListview = findViewById(R.id.MostrarVenta);
        auxListview.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, auxListventa));
        auxid_venta.setText("");

    }
}