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
import java.net.Socket;

/**
 *
 * @author valen
 */
public class Cliente {
    private Socket socket;
    private ObjectOutputStream salida;
    private ObjectInputStream entrada;

    public void conectar(String host, int puerto) {
        try {
            socket = new Socket(host, puerto);
            System.out.println("Conectado al servidor: " + host + ":" + puerto);

            salida = new ObjectOutputStream(socket.getOutputStream());
            entrada = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

   public void enviar(MensajeJuego mensaje) {
    try {
        Gson gson = new Gson();
        String json = gson.toJson(mensaje);
        salida.writeObject(json); 
        salida.flush();
        System.out.println("[JSON ENVIADO] " + json);
    } catch (IOException e) {
        e.printStackTrace();
    }
}

public void escucharMensajes(java.util.function.Consumer<MensajeJuego> callback) {
    new Thread(() -> {
        try {
            Gson gson = new Gson();
            while (true) {
                String json = (String) entrada.readObject();
                MensajeJuego mensaje = gson.fromJson(json, MensajeJuego.class);
                callback.accept(mensaje);
                System.out.println("[JSON RECIBIDO] " + json);
            }
        } catch (Exception e) {
            System.out.println("Conexion cerrada o error: " + e.getMessage());
        }
    }).start();
}

    public void cerrar() {
        try {
            entrada.close();
            salida.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

