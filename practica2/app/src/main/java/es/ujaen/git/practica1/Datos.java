package es.ujaen.git.practica1;

import java.util.ArrayList;

/**
 * Created by windic on 14/11/2016.
 */

/**
 * Clase que proporciona una estructura a los datos que van a ser enviado mediante el metodo Get.
 */
public class Datos {

    // posibles parametros del servicio
    final static protected String params[][] = {{"cod_mesa", "num_session"}, {}, {"cod_productos", "cantidad"},
            {"cod_mesa", "num_session"}};
    //values valores de los parametros
    protected ArrayList<String> values = new ArrayList<>();
    //parametros a usar en función del tipo de mensaje.
    protected String [] param;

    /**
     * Constructor de la clase
     * @param tipo parametros de:
     *             0:Autentificación
     *             1:Listar
     *             2:Pedido
     *             3:cerrar_session
     * @param Valores los valores asociados a cada parametro en el orden correcto para:
     *                tipo = 0: 2 valores.
     *                tipo = 1: null.
     *                tipo = 2: 2 valores.
     *                tipo = 3: 2 valores.
     */
    public Datos (int tipo, String...Valores){
        param = params[tipo];
        if (Valores != null){
            for (int i = 0; i<param.length;i++){
                values.add(Valores[i]);
            }
        }
    }

    /**
     *
     * @return devuelve la cadena de caracteres ya formateada para ser enviada mediante el metodo Get.
     */
    @Override
    public String toString() {
        String allparams = "";
        if (param.length != 0){
            for (int i = 0; i<param.length;i++){
                allparams = allparams + param[i] + "=" + values.get(i);
                if (i != param.length -1){
                    allparams = allparams + "&";
                }
            }
        }
        return allparams;
    }
}
