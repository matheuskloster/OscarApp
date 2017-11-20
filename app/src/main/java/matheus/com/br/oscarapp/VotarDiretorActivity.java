package matheus.com.br.oscarapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import static matheus.com.br.oscarapp.MainActivity.u;
import static matheus.com.br.oscarapp.MenuActivity.diretoresList;

public class VotarDiretorActivity extends AppCompatActivity {

    public static ListView listView;
    public static ListCellDiretor adapter;

    private VotarDiretorActivity getContext() {
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_votar_diretor);

        adapter = new ListCellDiretor(VotarDiretorActivity.this);
        listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent(getContext(), DetailDiretorActivity.class);
                it.putExtra("diretor",diretoresList.get(position));
                startActivity(it);
            }
        });



    }
}

