package matheus.com.br.oscarapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import matheus.com.br.oscarapp.model.Diretor;
import matheus.com.br.oscarapp.model.Filme;

import static matheus.com.br.oscarapp.MainActivity.u;

public class DetailDiretorActivity extends AppCompatActivity {

    TextView nomeTextView;
    String nome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_diretor);

        nomeTextView = (TextView) findViewById(R.id.nomeTextView);

        Intent it = getIntent();
        if (it != null) {
            Diretor diretor = it.getExtras().getParcelable("diretor");
            nome = diretor.getNome();
            nomeTextView.setText(nome);
        }
    }

    public void votar(View view){
        u.setDiretor(nome);
        Toast.makeText(this, "Seu voto no diretor foi cadastrado localmente" , Toast.LENGTH_SHORT).show();

    }
}
