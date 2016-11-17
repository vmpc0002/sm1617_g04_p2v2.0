package es.ujaen.git.practica1;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class AuthQRFragment extends Fragment {

    private Button mButton;
    public AuthQRFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmento = inflater.inflate(R.layout.fragment_auth_qr, container, false);
        mButton = (Button) fragmento.findViewById(R.id.autenticacionQR_scan_button);
        //textView = (TextView) fragmento.findViewById(R.id.textView);
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
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            if (resultCode == MainActivity.RESULT_OK) {
                String contents = data.getStringExtra("SCAN_RESULT");

            } else {
                //Quiere decir que NO se obtuvo resultado
                Toast toast = Toast.makeText(getActivity(),
                        "No scan data received!", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }
    private class AutenticacionQR extends AsyncTask<String,Void, String>{
        @Override
        protected String doInBackground(String... params) {
            ServiceRest rest = new ServiceRest(params[0]);
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }
}
