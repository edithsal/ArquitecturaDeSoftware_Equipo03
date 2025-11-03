/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Blackboard;

/**
 *
 * @author Emma
 */
import ProtocoloDispatch.DispatchProtocol;
import ProtocoloDispatch.BlackboardListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Pizarra compartida (Blackboard)
 * Registra mensajes y los distribuye mediante el protocolo Dispatch.
 * 
 * @author Emma
 */
public class Blackboard {

    private static Blackboard instancia; 
    private final List<String> registros; 
    private final DispatchProtocol dispatchProtocol; 
    private FrmBlackboard vista; 

    private Blackboard() {
        registros = new ArrayList<>();
        dispatchProtocol = new DispatchProtocol();
    }

    public static synchronized Blackboard getInstancia() {
        if (instancia == null) {
            instancia = new Blackboard();
        }
        return instancia;
    }

    public void setVista(FrmBlackboard vista) {
        this.vista = vista;
    }

    public synchronized void publicar(String mensaje) {
        registros.add(mensaje);
        System.out.println("[Blackboard] " + mensaje);

        if (vista != null) vista.actualizar(mensaje);

     
        dispatchProtocol.despachar(mensaje);
    }

    public void limpiar() {
        registros.clear();
        if (vista != null) vista.limpiar();
    }

    public synchronized List<String> getRegistros() {
        return new ArrayList<>(registros);
    }

    public void agregarListener(BlackboardListener listener) {
        dispatchProtocol.registrarListener(listener);
    }

    public void eliminarListener(BlackboardListener listener) {
        dispatchProtocol.eliminarListener(listener);
    }
}
