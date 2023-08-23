package com.example.bazar_uft;

import android.os.AsyncTask;

import org.bson.BsonArray;
import org.bson.BsonDocument;
import org.bson.BsonString;

public class consultaCliente extends AsyncTask<String, Void, Void> {
    private String[] stringArr;



    @Override
    protected Void doInBackground(String... params)
    {
        try {
            Cone ws = new Cone();



            String resulCliente =  ws.devuelveCliente();

            // int inicioCli = resulCliente.indexOf("<consultaClienteJsonResult>") + 27;
            //  int inicioCli = resulCliente.indexOf("<consultaClienteJsonResult>");
            //  int finCli = resulCliente.indexOf("</consultaClienteJsonResult>");
            int inicioCli = resulCliente.indexOf("[");
            int finCli = resulCliente.indexOf("]") + 1;

            final String limpJsonCli = resulCliente.substring(inicioCli, finCli);
            //---cambiar en caso de usar un motor relacional

            BsonArray auxBsonArray = BsonArray.parse(limpJsonCli);
            final String[] auxStringArr = new String[auxBsonArray.size()];

            Object[] auxArrayObject = auxBsonArray.toArray();

            for (int i = 0; i < auxArrayObject.length; i++)
            {
                BsonString auxRut = (BsonString)((BsonDocument) auxArrayObject[i]).get("rut");
                BsonString auxNombre = (BsonString)((BsonDocument) auxArrayObject[i]).get("nombre");

                auxStringArr[i] = auxRut.getValue() + " " + auxNombre.getValue();
            }
            this.setStringArr(auxStringArr);


        }
        catch (final Exception ex)
        {
            /*runOnUiThread(new Runnable() {
                public void run() {
                    TextView aux = (TextView)findViewById(R.id.txtTest);
                    aux.setText(ex.getMessage());
                }
            });*/
        }
        return null;
    }
    @Override
    protected void onPreExecute()
    {
/*            pro.setVisibility(View.VISIBLE);
            btnIngresar.setEnabled(false);
            btnCancelar.setEnabled(false);
            txtRut.setEnabled(false);
            txtContra.setEnabled(false); */
    }

    @Override
    protected void onPostExecute(Void args)
    {
        try {

            //      auxListView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,this.getStringArr()));



            // switch (resultado){
            //     case "true":

            //         break;
            //     default:
            // Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();
            //         break;
            // }

        }catch (final Exception ex)
        {
            //  runOnUiThread(new Runnable() {
            //    public void run() {
            //TextView aux = (TextView)findViewById(R.id.txtTest);
            //aux.setText(ex.getMessage());
            //  }
            //});
        }
    }


    public String[] getStringArr() {
        return stringArr;
    }

    public void setStringArr(String[] stringArr) {
        this.stringArr = stringArr;
    }
}
