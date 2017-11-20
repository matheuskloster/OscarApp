package matheus.com.br.oscarapp;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonWriter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Network;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static matheus.com.br.oscarapp.MainActivity.u;
import static matheus.com.br.oscarapp.MainActivity.u2;

public class ConfirmarVotoActivity extends AppCompatActivity implements Response.Listener, Response.ErrorListener {

    public static final String REQUEST_TAG = "ConfirmarVotoActivity";
    public static final String URL = "http://10.0.2.2:8080/usuarios";
    private RequestQueue mQueue;
    TextView filmeVotado, diretorVotado;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar_voto);

        button = (Button)  findViewById(R.id.button);
        filmeVotado = (TextView) findViewById(R.id.filmeVotado);
        diretorVotado = (TextView) findViewById(R.id.diretorVotado);
        diretorVotado.setText((u.getDiretor() != null) ? u.getDiretor() : "N/A");
        filmeVotado.setText((u.getFilme() != null) ? u.getFilme() : "N/A");
    }

    private ConfirmarVotoActivity getContext() {
        return this;
    }

    public CustomJSONObjectRequest makeRequest() {
        JSONObject request = new JSONObject();
        try {
            request.put("username", u.getUsername());
            request.put("password", u.getPassword());
            request.put("diretor", u.getDiretor());
            request.put("filme", u.getFilme());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        final CustomJSONObjectRequest jsonRequest = new CustomJSONObjectRequest(Request.Method.PUT, URL, request, this,this);
        jsonRequest.setTag(REQUEST_TAG);
        return jsonRequest;
    }

    @Override
    protected void onStart() {
        super.onStart();
        mQueue = CustomVolleyRequestQueue.getmInstance(this.getApplicationContext()).getRequestQueue();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (u.getDiretor() == null || u.getFilme() == null) {
                    Toast.makeText(getContext(), "Você deve votar para um filme e um diretor.", Toast.LENGTH_SHORT).show();
                } else {
                    mQueue.add(makeRequest());
                }
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mQueue != null) {
            mQueue.cancelAll(REQUEST_TAG);
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        String message = "Erro ao registrar o voto.";
        NetworkResponse response = error.networkResponse;
        if (response != null && response.data != null) {
            switch (response.statusCode) {
                case 400:
                    message = new String(response.data);
                    message = trimMessage(message, "message");
            }
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage(message)
                .setCancelable(false)
                .setPositiveButton("OK", null);
        AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    public void onResponse(Object response) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("Voto registrado com sucesso!")
                .setCancelable(false)
                .setPositiveButton("OK", null);
        AlertDialog alert = builder.create();
        alert.show();
    }

    public String trimMessage(String json, String key){
        String trimmedString = null;

        try{
            JSONObject obj = new JSONObject(json);
            trimmedString = obj.getString(key);
        } catch(JSONException e){
            e.printStackTrace();
            return null;
        }

        return trimmedString;
    }
}





