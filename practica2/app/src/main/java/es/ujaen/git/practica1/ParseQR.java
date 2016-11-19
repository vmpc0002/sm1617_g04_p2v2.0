package es.ujaen.git.practica1;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class ParseQR implements Service {

    URL qr = null;
    public ArrayList<String> valores = new ArrayList<>();

    public ParseQR(String contenido) {
        try {
            qr = new URL(contenido);
        } catch (MalformedURLException ex) {
        }
    }

    private String parseoServicio() {
        return qr.getPath().substring(qr.getPath().indexOf("/") + 1, qr.getPath().lastIndexOf("/"));
    }

    private String parseoHeader() {
        return qr.getFile().substring(qr.getFile().lastIndexOf("/") + 1, qr.getFile().lastIndexOf("?"));
    }

    private boolean parseoParams() {
        String str_params = qr.getFile().substring(qr.getFile().lastIndexOf("?") + 1, qr.getFile().length());
        String[] parse_param = str_params.split("&");
        if (parse_param.length == 2) {
            for (int i = 0; i < parse_param.length; i++) {
                String[] param_valor = parse_param[i].split("=");
                if (param_valor[0].equals(params[0][i])) {
                    valores.add(param_valor[1]);
                }
            }
        }
        if (valores.size() == 2) {
            return true;
        } else {
            return false;
        }
    }

    public boolean parseado() {
        if (qr != null) {
            if (qr.getProtocol().equals("http") && qr.getHost().equals(servidor) && parseoServicio().equals(servicio)
                    && parseoHeader().equals(headers[0]) && parseoParams()) {
                return true;
            } else {
                return false;
            }
        } else return false;
    }
}
