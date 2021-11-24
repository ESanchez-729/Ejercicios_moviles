package com.example.almacenamientoenlared_json;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConnectivityManager conexion = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo estado = conexion.getActiveNetworkInfo();

        if (estado != null && estado.isConnected() && estado.isAvailable()) {

            new MyJsonTask().execute("https://servicios.ine.es/wstempus/js/es/DATOS_TABLA/43491?tip=AM");
        }
    }

    class MyJsonTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {

            HttpURLConnection httpConnection = null;

            try {

                URL enlace = new URL(strings[0]);
                httpConnection = (HttpURLConnection) enlace.openConnection();
                String datos;
                StringBuilder resultado = new StringBuilder();

                int codigo = httpConnection.getResponseCode();

                if (codigo == 200) {

                    httpConnection.setRequestMethod("GET");
                    InputStream lectura = new BufferedInputStream(httpConnection.getInputStream());
                    BufferedReader lecturaAvanzada = new BufferedReader(new InputStreamReader(lectura));

                    while ((datos = lecturaAvanzada.readLine()) != null) {

                        resultado.append(datos);
                    }
                }

                return resultado.toString();

            }

            catch (MalformedURLException e) {

                return null;
            }

            catch (IOException e) {

                return null;
            }

            finally {

                httpConnection.disconnect();
            }
        }

        protected void onPostExecute(String result) {

            super.onPostExecute(result);

            try {

                JSONArray jsonArray = new JSONArray(result);

                for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject jsonObj = jsonArray.getJSONObject(i);
                    Log.d("prueba", "nombre: " + jsonObj.getString("Nombre"));

                    JSONArray meta = new JSONArray(jsonObj.getString("MetaData"));

                    JSONObject md = meta.getJSONObject(0);
                    Log.d("metadatos", "Metadatos: " + md.getString("Codigo"));


                    JSONArray valores = new JSONArray(jsonObj.getString("Data"));
                    JSONObject datos = valores.getJSONObject(0);
                    Log.d("valores", "valor: " + datos.getString("Valor"));



                }
            }

            catch (JSONException e) {
                e.printStackTrace();
            }


        }
    }


}