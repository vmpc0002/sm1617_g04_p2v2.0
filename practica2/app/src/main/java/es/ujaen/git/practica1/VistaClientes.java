package es.ujaen.git.practica1;

/**
 * @author Emilio Sánchez Catalán y Víctor Manuel Pérez Cámara
 * @version 1.0
 */

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class VistaClientes extends AppCompatActivity {
    /**
     * Metodo encargado de la recepción de los valores de autentificación.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista_clientes);


    }
}
