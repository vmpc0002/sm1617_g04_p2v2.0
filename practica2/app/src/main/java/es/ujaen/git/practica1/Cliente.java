package es.ujaen.git.practica1;

public interface Cliente {

    public String autentificacion (String cod_mensaje, String num_session);
    public void listar();
    public void pedido(String cod_pedido, String cantidad);
    public void cerrar_session(String cod_mensaje, String num_session);
    public String verificacion(String sesion_id, String expires);
}
