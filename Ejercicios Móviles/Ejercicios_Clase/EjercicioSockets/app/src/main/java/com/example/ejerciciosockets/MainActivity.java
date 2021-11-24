package com.example.ejerciciosockets;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    ServerSocket server;
    Socket socket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button encender = (Button) findViewById(R.id.encendido);
        encender.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                new Thread(new Runnable() {

                    @Override
                    public void run() {

                        try {

                            server = new ServerSocket(10001);

                            while (true) {

                                socket = server.accept();
                                BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                                String datos = entrada.readLine();

                                Log.d("mostrar", "datos: " + datos);


                                socket.close();
                            }
                        }

                        catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();

            }
        });

        Button apagar = (Button) findViewById(R.id.apagado);
        apagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {

                    server.close();
                }

                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        Button cliente = (Button) findViewById(R.id.lanzaCliente);
        cliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        Socket socket_cliente = null;
                        try {

                            socket_cliente = new Socket("127.0.0.1", 10001);

                            BufferedWriter miEscritor = new BufferedWriter(new OutputStreamWriter(socket_cliente.getOutputStream()));
                            miEscritor.write("texto");
                            miEscritor.flush();//Fuerza la escritura
                            miEscritor.close();
                        }

                        catch (IOException e) {

                            e.printStackTrace();
                        }

                        finally {

                            try {
                                socket_cliente.close();
                            }

                            catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();

            }
        });
    }
}