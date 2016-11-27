package es.ujaen.git.practica1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

/**
 * @author Emilio Sánchez Catalán y Víctor Manuel Pérez Cámara
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity implements Service {

    /**
     * Metodo encargado de crear la Activity principal. Donde se vincula la activity al layout principal
     * y realiza la transacción con el fragmento Authfragment.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPager viewPager = (ViewPager) findViewById(R.id.activity_main_pager_viewpager);
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());
        //TODO R.string.menu_0 a clientes;
        adapter.addFragment(new AuthQRFragment(), "clientes");
        adapter.addFragment(new AuthFragment(), "propietarios");
        viewPager.setAdapter(adapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.activity_main_tabs_tablayout);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected void onResume() {
        super.onResume();
        String sesion_id, expires;
        SharedPreferences preferences = getSharedPreferences(sharedpreferences, 0);
        sesion_id = preferences.getString(lresp[0], null);
        expires = preferences.getString(lresp[1], null);
        if (sesion_id != null && expires != null) {
            Verificacion verf = new Verificacion();
            verf.execute(sesion_id, expires);

        }
    }

    private class Verificacion extends AsyncTask<String, Void, String> implements Cliente {
        @Override
        protected String doInBackground(String... params) {
            return verificacion(params[0], params[1]);
        }

        @Override
        protected void onPostExecute(String s) {
            ParseServerResponses serverResponses = new ParseServerResponses(s);
            if (serverResponses.verify() == true) {
                Intent intent = new Intent(MainActivity.this, VistaClientes.class);
                startActivity(intent);
            } else {

            }

        }

        @Override
        public String autentificacion(String cod_mensaje, String num_session) {
            return null;
        }

        @Override
        public void listar() {
        }

        @Override
        public void pedido(String cod_pedido, String cantidad) {
        }

        @Override
        public void cerrar_session(String cod_mensaje, String num_session) {
        }

        @Override
        public String verificacion(String sesion_id, String expires) {
            Datos datos = new Datos(4, sesion_id, expires);
            Mensaje mensaje = new Mensaje(4, datos);
            ServiceRest rest = new ServiceRest(mensaje.toString());
            return rest.reqGet();
        }
    }
}


