package es.ujaen.git.practica1;

/**
 * @author Emilio Sánchez Catalán y Víctor Manuel Pérez Cámara
 * @version 1.0
 */

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

public class VistaClientes extends AppCompatActivity implements Service{
    /**
     * Metodo encargado de la recepción de los valores de autentificación.
     *
     * @param savedInstanceState
     */
    Button cerrar_sesion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista_clientes);
        cerrar_sesion = (Button) findViewById(R.id.vistaclientes_cerrarsesion_button);
        cerrar_sesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref = getSharedPreferences("MiPref", 0);
                SharedPreferences.Editor editorPref = pref.edit();
                editorPref.putString(lresp[0], null);
                editorPref.putString(lresp[1], null);
                editorPref.commit();
                finish();
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == event.KEYCODE_BACK){
            finishAffinity();
        }
        return super.onKeyDown(keyCode, event);
    }
}
