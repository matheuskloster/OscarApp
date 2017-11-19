package matheus.com.br.oscarapp;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonWriter;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static matheus.com.br.oscarapp.MainActivity.u;
import static matheus.com.br.oscarapp.MainActivity.u2;

public class ConfirmarVotoActivity extends AppCompatActivity {

    public static final String REQUEST_TAG = "ConfirmarVotoActivity";
    TextView filmeVotado, diretorVotado;
    private RequestQueue mQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar_voto);

        filmeVotado = (TextView) findViewById(R.id.filmeVotado);
        diretorVotado = (TextView) findViewById(R.id.diretorVotado);

        filmeVotado.setText(u.getFilme());
        diretorVotado.setText(u.getDiretor());

    }

    public void confirmarVoto(){




       StringRequest strReq = new StringRequest(Request.Method.POST, "http://localhost:8080/usuarios/", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Salvar", response.toString());
                //funcionou
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("SalvarErro", "Error: " + error.toString());
            }
        }){
            @Override
            protected Map<String, String> getParams(){
                Map<String, String>  params = new HashMap<String, String>();
                Gson g = new Gson();
                String usuarioJson = g.toJson(u);
                usuarioJson= usuarioJson.toString();
                params.put("strObject", usuarioJson);
                return params;
            }
        };
    }
}





