/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Socket;

/**
 *
 * @author LENOVO
 */

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

    public void enviar(Object obj) {
        try {
            salida.writeObject(obj);
            salida.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void escucharMensajes(java.util.function.Consumer<MensajeJuego> callback) {
        new Thread(() -> {
            try {
                while (true) {
                    MensajeJuego mensaje = (MensajeJuego) entrada.readObject();
                    callback.accept(mensaje);
                }
            } catch (Exception e) {
                System.out.println("Conexión cerrada o error: " + e.getMessage());
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

