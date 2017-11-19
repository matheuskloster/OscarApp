package matheus.com.br.oscarapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static matheus.com.br.oscarapp.MenuActivity.filmesList;
import static matheus.com.br.oscarapp.MainActivity.u;
import static matheus.com.br.oscarapp.MenuActivity.urlFilme;

public class VotarFilmeActivity extends AppCompatActivity {

    public static ListView listView;
    public static ListCell adapter;


    private VotarFilmeActivity getContext() {
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_votar_filme);

        adapter = new ListCell(VotarFilmeActivity.this);
        listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent(getContext(), DetailFilmeActivity.class);
                Bundle params = new Bundle();
                params.putInt("position", position);
                params.putInt("id", filmesList.get(position).getId());

                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                filmesList.get(position).getFoto().compress(Bitmap.CompressFormat.JPEG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                params.putByteArray("foto", byteArray);

                params.putString("nome", filmesList.get(position).getNome());
                params.putString("genero", filmesList.get(position).getGenero());
                it.putExtras(params);
                startActivity(it);
            }
        });
    }
}