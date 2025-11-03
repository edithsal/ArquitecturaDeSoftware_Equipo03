/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Socket;

/**
 *
 * @author LENOVO
 */

import com.google.gson.Gson;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author valen
 */
public class Servidor {
    private ServerSocket server;
    private List<ObjectOutputStream> clientes = new CopyOnWriteArrayList<>();
    private java.util.function.Consumer<MensajeJuego> callbackGlobal;

    public void escucharMensajes(java.util.function.Consumer<MensajeJuego> callback) {
        this.callbackGlobal = callback;
    }
    
    public void iniciarServidor(int puerto) {
        new Thread(() -> {
                try {
                    server = new ServerSocket(puerto);
                    System.out.println("Servidor iniciado en puerto " + puerto);
                    int contadorClientes = 0;

                    while (true) {
                        Socket socket = server.accept();
                        contadorClientes++;
                        System.out.println("Cliente conectado: " + socket.getInetAddress().getHostAddress());

                        ObjectOutputStream salida = new ObjectOutputStream(socket.getOutputStream());
                        ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream());
                        clientes.add(salida);

                        if (contadorClientes == 2) {
                            System.out.println("Jugador 2 se ha conectado. El juego puede comenzar.");
                        }

                        new Thread(() -> manejarCliente(socket, entrada, salida)).start();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start(); 
        }

 
        private void manejarCliente(Socket socket, ObjectInputStream entrada, ObjectOutputStream salida) {
            try {
                Object obj;
                Gson gson = new Gson(); 
                while ((obj = entrada.readObject()) != null) {
                    if (obj instanceof String json) { 
                       
                        MensajeJuego mensaje = gson.fromJson(json, MensajeJuego.class);

                     
                        if (callbackGlobal != null) {
                            callbackGlobal.accept(mensaje);
                        }

                      
                        reenviarATodos(mensaje);
                    }
                }
            } catch (Exception e) {
                System.out.println("Cliente desconectado: " + socket.getInetAddress().getHostAddress());
            } finally {
                try {
                    entrada.close();
                    salida.close();
                    socket.close();
                    clientes.remove(salida);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


           private void reenviarATodos(MensajeJuego mensaje) {
        Gson gson = new Gson();
        String json = gson.toJson(mensaje);
        for (ObjectOutputStream cliente : clientes) {
            try {
                cliente.writeObject(json);
                cliente.flush();
                System.out.println("[JSON REENVIADO] " + json);
            } catch (IOException e) {
                System.out.println("Error enviando a cliente.");
            }
        }
    }

    public void enviar(MensajeJuego mensaje) {
        reenviarATodos(mensaje);
    }

    }