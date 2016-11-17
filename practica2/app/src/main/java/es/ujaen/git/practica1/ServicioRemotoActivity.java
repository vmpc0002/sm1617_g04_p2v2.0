package es.ujaen.git.practica1;

/**
 * @author Emilio Sánchez Catalán y Víctor Manuel Pérez Cámara
 * @version 1.0
 */

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ServicioRemotoActivity extends AppCompatActivity {
    /**
     * Metodo encargado de la recepción de los valores de autentificación.
     *
     * @param savedInstanceState
     */
    public String muser, mpass, mip;
    public int mport;
    public TextView user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.servicio_remoto);
        user = (TextView)findViewById(R.id.servicio_remoto_reqserver_textview);
        muser = getIntent().getStringExtra("session-id");
        mpass = getIntent().getStringExtra("expires");
        user.setText(muser + mpass);
    }
}
