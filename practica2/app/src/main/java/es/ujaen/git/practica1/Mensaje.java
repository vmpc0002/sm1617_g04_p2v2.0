package es.ujaen.git.practica1;

/**
 * Created by windic on 14/11/2016.
 */

public class Mensaje {
    protected final String headers [] = {"autentica.php", "listar.php", "pedido.php", "cierre_session.php"};
    protected int tipo;
    protected String header = "";
    protected Datos datos;

    /**
     *
     * @param tipo
     * @param datos
     */
    public Mensaje(int tipo, Datos datos){
        this.tipo = tipo;
        header = headers[tipo];
        this.datos = datos;
    }

    @Override
    public String toString() {
        return header + "?" + datos.toString();
    }
}
