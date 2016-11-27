package es.ujaen.git.practica1;

import java.util.HashMap;
import java.util.Map;

public class ParseServerResponses implements Service{

    private String resp;
    final private Map<String,String> responses = new HashMap<>();

    public ParseServerResponses(String resp) {
        this.resp = resp;
    }
    public boolean autentica() {
        if (resp.substring(0, 2).equalsIgnoreCase(OK)) {
            String clave_valor [];
            resp = resp.replace("\r\n", "");
            clave_valor =resp.substring(resp.indexOf(" ") + 1, resp.length()).split("&");
            responses.put(clave_valor[0].split("::")[0],clave_valor[0].split("::")[1]);
            responses.put(clave_valor[1].split("::")[0],clave_valor[1].split("::")[1]);
            return true;
        } else if (resp.substring(0, 2).equalsIgnoreCase(ERROR)) {
            return false;
        }
        return false;
    }
    public boolean verify(){
        if(resp.substring(0, 2).equalsIgnoreCase(OK)){
            return true;
        }else if (resp.substring(0, 2).equalsIgnoreCase(ERROR)){
            return false;
        }
        return false;
    }
    public String getSessionID(){
        if (responses.containsKey(lresp[0])){
            return responses.get(lresp[0]);
        }else return null;
    }
    public String getExpires(){
        if (responses.containsKey(lresp[1])){
            return responses.get(lresp[1]);
        }else return null;
    }
}
