package com.example.bazar_uft;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class BuscarCompra extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_compra);
    }


    public void BuscarCompra(View view) {
        EditText auxid_compra = findViewById(R.id.txtBoletaCompra);

        BD_BazarUFT auxBazarUFT= new BD_BazarUFT(this);


       Compra auxCompra = auxBazarUFT.buscarCompra(Integer.parseInt(auxid_compra.getText().toString()));
        ArrayList<String > auxListcompra = new ArrayList<String>();
        auxListcompra.add(auxCompra.getNombreProducto());
        auxListcompra.add(auxCompra.getProveedor());
        auxListcompra.add(auxCompra.getCodigoProducto());




        ListView auxListview = findViewById(R.id.MostrarCompra);
        auxListview.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, auxListcompra));
        auxid_compra.setText("");



    }
}
