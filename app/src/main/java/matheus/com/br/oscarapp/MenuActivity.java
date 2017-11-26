package matheus.com.br.oscarapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import matheus.com.br.oscarapp.model.Diretor;
import matheus.com.br.oscarapp.model.Filme;
import matheus.com.br.oscarapp.model.Usuario;

import static matheus.com.br.oscarapp.MainActivity.u;

public class MenuActivity extends AppCompatActivity {

    public static List<Diretor> diretoresList = new ArrayList<>();
    public static List<Filme> filmesList = new ArrayList<>();
    public static String urlDiretor = "https://dl.dropboxusercontent.com/s/4scnaqzioi3ivxc/diretor.json";
    public static String urlFilme = "https://dl.dropboxusercontent.com/s/luags6sv8uxdoj1/filme.json";
    public static boolean voted = false;
    TextView menuTextView;


    private MenuActivity getContext() {
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        menuTextView = (TextView) findViewById(R.id.menuTextView);

        Intent it = getIntent();
        if (it != null) {
            if (it.getExtras() != null) {
                menuTextView.setText(it.getExtras().getString("menuTextView"));
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (voted) {
            menuTextView.setText("Seus votos foram para:\n" + u.getDiretor() + "\n" + u.getFilme());
        }
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (voted) {
            menu.findItem(R.id.votarFilme).setEnabled(false);
            menu.findItem(R.id.votarDiretor).setEnabled(false);
            menu.findItem(R.id.confirmarVoto).setEnabled(false);
        }
        return true;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.votarFilme:
                new TaskFilmes().execute(null, null, null);
                break;
            case R.id.votarDiretor:
                new TaskDiretor().execute(null, null, null);
                break;
            case R.id.confirmarVoto:
                Intent itConfirmar = new Intent(this, ConfirmarVotoActivity.class);
                startActivity(itConfirmar);
                break;
            case R.id.sair:
                this.finishAffinity();
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    private class TaskDiretor extends AsyncTask<Void, ProgressDialog, Void> {

        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(getContext());
            progressDialog.setMessage("Carregando lista de diretores");
            progressDialog.show();


        }

        @Override
        protected Void doInBackground(Void... voids) {
            HTTPHandler httpHandler = new HTTPHandler();
            String jsonStr = httpHandler.makeServiceCall(urlDiretor);
            if (jsonStr != null && diretoresList.size() == 0) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);
                    JSONArray diretores = jsonObj.getJSONArray("diretor");

                    for (int i = 0; i < diretores.length(); i++) {
                        JSONObject d = diretores.getJSONObject(i);

                        int id = d.getInt("id");
                        String nome = d.getString("nome");

                        Diretor diretor = new Diretor(id, nome);
                        diretoresList.add(diretor);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Intent itDiretor = new Intent(getContext(), VotarDiretorActivity.class);
            startActivity(itDiretor);
              progressDialog.dismiss();
        }

    }


    private class TaskFilmes extends AsyncTask<Void, Void, Void> {

        private ProgressDialog pDialog;

        protected void onPreExecute() {

            super.onPreExecute();
            pDialog = new ProgressDialog(getContext());
            pDialog.setMessage("Carregando lista de filmes");
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... voids) {
            HTTPHandler httpHandler = new HTTPHandler();
            String jsonStr = httpHandler.makeServiceCall(urlFilme);
            if (jsonStr != null && filmesList.size() == 0) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);
                    JSONArray filmes = jsonObj.getJSONArray("filme");

                    for (int i = 0; i < filmes.length(); i++) {
                        JSONObject f = filmes.getJSONObject(i);
                        Integer id = f.getInt("id");
                        String nome = f.getString("nome");
                        String genero = f.getString("genero");
                        Bitmap foto = httpHandler.downloadImage(f.getString("foto"));
                        Filme filme = new Filme(id, nome, genero, foto);
                        filmesList.add(filme);
                    }
                } catch (final JSONException e) {
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), "Json parsing error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }

        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            pDialog.dismiss();
            Intent itFilme = new Intent(getContext(), VotarFilmeActivity.class);
            startActivity(itFilme);
        }
    }
}



