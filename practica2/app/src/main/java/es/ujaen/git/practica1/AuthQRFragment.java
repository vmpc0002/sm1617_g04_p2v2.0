package es.ujaen.git.practica1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class AuthQRFragment extends Fragment implements Service {

    private ImageButton mButton;
    private ParseQR parseQR;

    public AuthQRFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmento = inflater.inflate(R.layout.fragment_auth_qr, container, false);
        mButton = (ImageButton) fragmento.findViewById(R.id.autenticacionQR_scan_button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.google.zxing.client.android.SCAN");
                intent.putExtra("com.google.zxing.client.android.SCAN.SCAN_MODE", "QR_CODE_MODE");
                startActivityForResult(intent, 0);

            }
        });
        return fragmento;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            if (resultCode == MainActivity.RESULT_OK) {
                String contents = data.getStringExtra("SCAN_RESULT");
                parseQR = new ParseQR(contents);
                if (parseQR.parseado() == true) {
                    Autenticator auth = new Autenticator();
                    auth.execute(parseQR.getCod_mesa(), parseQR.getNum_session());
                } else {
                    Toast.makeText(getActivity(), R.string.fail_auth, Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getActivity(), R.string.no_qr, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private class Autenticator extends AsyncTask<String, Void, String> implements Cliente {
        @Override
        protected String doInBackground(String... params) {
            return autentificacion(params[0], params[1]);
        }

        @Override
        protected void onPostExecute(String s) {
            ParseServerResponses serverResponses = new ParseServerResponses(s);
            if (serverResponses.autentica() == true) {
                SharedPreferences pref = getActivity().getSharedPreferences(sharedpreferences, 0);
                SharedPreferences.Editor sharedEditor = pref.edit();
                sharedEditor.putString(lresp[0], serverResponses.getSessionID());
                sharedEditor.putString(lresp[1], serverResponses.getExpires());
                sharedEditor.commit();
                Intent intent = new Intent(getActivity(), VistaClientes.class);
                startActivity(intent);
            } else {
                Toast.makeText(getActivity(), R.string.fail_auth, Toast.LENGTH_SHORT).show();
            }

        }

        @Override
        public String autentificacion(String cod_mensaje, String num_session) {
            Datos datos = new Datos(0, cod_mensaje, num_session);
            Mensaje mensaje = new Mensaje(0, datos);
            ServiceRest rest = new ServiceRest(mensaje.toString());
            return rest.reqGet();

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
            return null;
        }
    }
}
