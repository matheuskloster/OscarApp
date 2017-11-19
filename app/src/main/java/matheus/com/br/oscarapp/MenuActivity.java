package matheus.com.br.oscarapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity {
    public static Usuario u;
    public static List<Diretor> diretoresList = new ArrayList<>();
    public static List<Filme> filmesList = new ArrayList<>();
    public static String urlDiretor = "https://dl.dropboxusercontent.com/s/4scnaqzioi3ivxc/diretor.json";
    public static String urlFilme = "https://dl.dropboxusercontent.com/s/luags6sv8uxdoj1/filme.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.votarFilme:
                Intent itFilme = new Intent(this, VotarFilmeActivity.class);
                //baixar filmes e colocar na lista de filmes

                startActivity(itFilme);
                break;
            case R.id.votarDiretor:
                new TaskDiretor().execute(null,null,null);
                Intent itDiretor = new Intent(this, VotarDiretorActivity.class);
                startActivity(itDiretor);
                break;
            case R.id.confirmarVoto:
                Intent itConfirmar = new Intent(this, ConfirmarVotoActivity.class);
                startActivity(itConfirmar);
                break;
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
           // progressDialog = new ProgressDialog(getBaseContext());
            //progressDialog.setMessage("Carregando lista");
            //progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            HTTPHandler httpHandler = new HTTPHandler();
            String jsonStr = httpHandler.makeServiceCall(urlDiretor);
            if (jsonStr != null) {
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

        protected void onPostExecute(Void result){
            super.onPostExecute(result);
            progressDialog.dismiss();


        }

    }
}



