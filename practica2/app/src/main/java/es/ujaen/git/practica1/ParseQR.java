package es.ujaen.git.practica1;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by windic on 17/11/2016.
 */

public class ParseQR {
    //http://192.168.1.1/Servidor_g05/autentica.php?cod_mesa=""&num_session=""
    URL qr = null;
    private ArrayList<String> params = new ArrayList();
    // TODO: 17/11/2016 crear la interfaz que almacene las constantes de la aplicacion.
    final static String servidor = "192.168.1.1";
    public ParseQR (String contenido){
        try {
            qr =  new URL(contenido);
        }catch (MalformedURLException ex){
        }
    }
    private String parseoServicio(){
        return qr.getPath().substring(qr.getPath().indexOf("/"), qr.getPath().lastIndexOf("/") + 1);
    }
    private String parseoHeader(){
        return qr.getFile().substring(qr.getFile().lastIndexOf("/") + 1,qr.getFile().lastIndexOf("?"));
    }
    public boolean parseado (){
        if (qr != null) {
            if (qr.getProtocol().equals("http") && qr.getHost().equals(servidor) && parseoServicio().equals("/Servidor_g05/")
                    && parseoHeader().equals("Autentica.php")) {
                return true;
            } else {
                return false;
            }
        }
        else return false;
    }
}
