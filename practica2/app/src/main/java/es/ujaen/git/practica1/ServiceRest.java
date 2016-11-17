package es.ujaen.git.practica1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by windic on 13/11/2016.
 */

public class ServiceRest {
    final static String Servidor = "http://192.168.1.1/Servidor_g05/";
    private String urlget;

    public ServiceRest(String mensajeReq) {
        urlget = Servidor+mensajeReq;
    }

    public String reqGet(){
        String res = "";
        try{
            URL url = new URL(urlget);
            URLConnection conexion = url.openConnection();
            conexion.connect();
            InputStream in = conexion.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            char[] buffer = new char[1000];
            int leido;
            while ((leido = br.read(buffer)) > 0) {
                res = res + new String(buffer, 0, leido);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return res;
    }
}
