package com.example.almacenamientoenlared;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.AsyncTaskLoader;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

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

            new MyTask().execute("https://www.google.com/humans.txt");

        }


    }

    class MyTask extends AsyncTask<String, Void, String> {

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

            TextView texto = (TextView) findViewById(R.id.texto);
            texto.setText(result);


        }
    }
}