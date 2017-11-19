package matheus.com.br.oscarapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    EditText usuario;
    EditText senha;
    TextView out;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        out = (TextView) findViewById(R.id.output);
        usuario = (EditText) findViewById(R.id.usuario);
        senha = (EditText) findViewById(R.id.senha);


    }

    private MainActivity getContext() {
        return this;
    }


    public void login(View view) {
        if (usuario.length() == 0 || senha.length() == 0) {
            Toast.makeText(this, "Verifique se há campos vazios", Toast.LENGTH_SHORT).show();
        } else {
            String user = usuario.getText().toString();
            String password = senha.getText().toString();
            new TaskLogin().execute();
        }
    }


    private class TaskLogin extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            String url = "http://10.0.2.2:8080/usuarios/" + usuario.getText().toString();
            String jsonStr = new HTTPHandler().makeServiceCall(url);
            if (jsonStr != null) {
                try {
                    JSONObject usuarioRecebido = new JSONObject(jsonStr);
                    Long id = usuarioRecebido.getLong("id");
                    String username = usuarioRecebido.getString("username");
                    String password = usuarioRecebido.getString("password");
                    String filme = usuarioRecebido.getString("filme");
                    String diretor = usuarioRecebido.getString("diretor");
                    Usuario u = new Usuario(id, username, password, filme, diretor);

                    if (senha.getText().toString().equals(u.getPassword())) {
                        Intent it = new Intent(getContext(), MenuActivity.class);
                        it.putExtra("usuario", u);
                        startActivity(it);
                        finish();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
    }


}

