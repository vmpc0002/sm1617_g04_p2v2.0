package es.ujaen.git.practica1;

/**
 * Created by windic on 20/11/2016.
 */

public class ParseServerResponses implements Service{

    String resp;
    public ParseServerResponses(String resp) {
        this.resp = resp;
    }
    public boolean autentica() {
        if (resp.substring(0, 2).equalsIgnoreCase(OK)) {
            return true;
        } else if (resp.substring(0, 2).equalsIgnoreCase(ERROR)) {
            return false;
        }
        return false;
    }
}
