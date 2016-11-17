package es.ujaen.git.practica1;

/**
 * Created by windic on 15/11/2016.
 */

public interface Cliente {

    public void autentificacion (String cod_mensaje, String num_session);
    public void listar();
    public void pedido(String cod_pedido, String cantidad);
    public void cerrar_session(String cod_mensaje, String num_session);
}
