package matheus.com.br.oscarapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.ByteArrayOutputStream;

import static matheus.com.br.oscarapp.MenuActivity.filmesList;

public class VotarFilmeActivity extends AppCompatActivity {

    public static ListView listViewFilme;
    public static ListCell filmeAdapter;


    private VotarFilmeActivity getContext() {
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_votar_filme);

        filmeAdapter = new ListCell(VotarFilmeActivity.this);
        listViewFilme = (ListView) findViewById(R.id.list);
        listViewFilme.setAdapter(filmeAdapter);
        listViewFilme.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent(getContext(), DetailFilmeActivity.class);
                Bundle params = new Bundle();
                params.putInt("position", position);
                params.putInt("id", filmesList.get(position).getId());

                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                filmesList.get(position).getFoto().compress(Bitmap.CompressFormat.JPEG, 50, stream);
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