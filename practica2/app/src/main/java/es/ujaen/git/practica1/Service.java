package es.ujaen.git.practica1;

/**
 * Created by windic on 19/11/2016.
 */

public interface Service {
    public static final String servidor = "192.168.13.154";
    public static final String servicio = "Servidor_g05";
    public static final int port = 8080;
    public static final String headers[] = {"autentica.php", "listar.php", "pedido.php", "cierre_session.php"};
    public static final String params[][] = {{"cod_mesa", "num_session"}, {}, {"cod_productos", "cantidad"},
            {"cod_mesa", "num_session"}};
    public static final String ERROR = "ER";
    public static final String OK = "OK";

}
