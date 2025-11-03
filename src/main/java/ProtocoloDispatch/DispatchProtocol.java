/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ProtocoloDispatch;

/**
 *
 * @author LENOVO
 */


import java.util.ArrayList;
import java.util.List;

/**
 * Clase que implementa el protocolo Dispatch.
 * Se encarga de administrar los suscriptores (listeners)
 * y de distribuir los mensajes del Blackboard.
 * 
 * @author Emma
 */
public class DispatchProtocol {

    private final List<BlackboardListener> listeners;

    public DispatchProtocol() {
        listeners = new ArrayList<>();
    }

    /**
     * Registra un nuevo listener (módulo que desea recibir mensajes)
     */
    public void registrarListener(BlackboardListener listener) {
        listeners.add(listener);
    }

    /**
     * Elimina un listener
     */
    public void eliminarListener(BlackboardListener listener) {
        listeners.remove(listener);
    }

    /**
     * Despacha un mensaje a los listeners registrados.
     * Aquí se puede aplicar lógica de filtrado o prioridad.
     */
    public void despachar(String mensaje) {
        System.out.println("[DispatchProtocol] Despachando mensaje: " + mensaje);

        for (BlackboardListener listener : listeners) {
            listener.onMensajePublicado(mensaje);
        }
    }

    public void limpiarListeners() {
        listeners.clear();
    }
}
