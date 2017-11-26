package matheus.com.br.oscarapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import matheus.com.br.oscarapp.model.Usuario;

import static matheus.com.br.oscarapp.MenuActivity.voted;

public class MainActivity extends AppCompatActivity implements Response.Listener, Response.ErrorListener {

    public static Usuario u, u2;
    public static final String REQUEST_TAG = "MainActivity";
    private RequestQueue mQueue;
    Button button;
    EditText usuario;
    EditText senha;
    TextView out;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);
        out = (TextView) findViewById(R.id.output);
        usuario = (EditText) findViewById(R.id.usuario);
        senha = (EditText) findViewById(R.id.senha);
    }

    private MainActivity getContext() {
        return this;
    }

    public CustomJSONObjectRequest makeRequest(String username, String password) {
        String url = "http://10.0.2.2:8080/login";
        JSONObject request = new JSONObject();
        try {
            request.put("username", username);
            request.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        final CustomJSONObjectRequest jsonRequest = new CustomJSONObjectRequest(Request.Method.POST, url, request, this, this);
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
                if (usuario.length() == 0 || senha.length() == 0) {
                    Toast.makeText(getContext(), "Verifique se há campos vazios", Toast.LENGTH_SHORT).show();
                } else {
                    mQueue.add(makeRequest(usuario.getText().toString(), senha.getText().toString()));
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
        out.setText("Usuário ou senha inválidos.");
    }

    @Override
    public void onResponse(Object response) {
        JSONObject jsonResponse = (JSONObject) response;
        Intent it = new Intent(getContext(), MenuActivity.class);
        try {
            u = new Usuario(null, jsonResponse.getString("username"), jsonResponse.getString("password"), jsonResponse.getString("filme"), jsonResponse.getString("diretor"));
            if (u.getDiretor().equals("null") && u.getFilme().equals("null")) {
                voted = false;
                u.setDiretor(null);
                u.setFilme(null);
            } else {
                voted = true;
                it.putExtra("menuTextView", "Seus votos foram para:\n" + u.getDiretor() + "\n" + u.getFilme());

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        startActivity(it);
        finish();
    }

}

