package matheus.com.br.oscarapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static matheus.com.br.oscarapp.MainActivity.u;

public class DetailFilmeActivity extends AppCompatActivity {

    TextView nomeTextView, generoTextView;
    ImageView imageView;
    String nome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_filme);

        imageView = (ImageView) findViewById(R.id.imageView);
        nomeTextView = (TextView) findViewById(R.id.nomeTextView);
        generoTextView = (TextView) findViewById(R.id.generoTextView);

        Intent it = getIntent();
        if (it != null) {
            Bundle params = it.getExtras();
            if (params != null) {
                nome = params.getString("nome");;
                byte[] byteArray = params.getByteArray("foto");
                Bitmap picture = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);

                imageView.setImageBitmap(picture);
                nomeTextView.setText(params.getString("nome"));
                generoTextView.setText(params.getString("genero"));
            }
        }
    }

    public void votar(View view) {
       u.setFilme(nome);
       Toast.makeText(this, "Seu voto no filme foi cadastrado localmente " , Toast.LENGTH_SHORT).show();
    }

}
