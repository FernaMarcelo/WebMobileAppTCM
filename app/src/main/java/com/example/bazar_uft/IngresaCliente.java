package com.example.bazar_uft;

import android.os.AsyncTask;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;



public class IngresaCliente extends AsyncTask<String, Void, Void> {

    String resultado = "";
    @Override
    protected Void doInBackground(String... params)
    {
        try{
            String NAMESPACE ="http://tempuri.org/";
            String URL = "http://192.168.1.7/WebServiceMongoFinis2022.asmx";
            String  METHOD_NAME = "insertarCliente";
            String  SOAP_ACTION = "http://tempuri.org/insertarCliente";

            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("rut", params[0].toString());
            request.addProperty("nombre",params[1].toString());


            SoapSerializationEnvelope envelope =
                    new SoapSerializationEnvelope(SoapEnvelope.VER11);

            envelope.dotNet=true;
            envelope.setOutputSoapObject(request);
            HttpTransportSE transporte=new HttpTransportSE(URL);
            transporte.debug=true;

            try
            {
                transporte.call(SOAP_ACTION, envelope);
            }
            catch (Exception e)
            {
                resultado ="error";
            }


        }
        catch (Exception exception) {
            exception.printStackTrace();
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

            switch (resultado){
                case "true":

                    break;
                default:
                    // Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();
                    break;
            }

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


}