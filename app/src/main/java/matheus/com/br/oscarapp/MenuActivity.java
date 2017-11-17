package matheus.com.br.oscarapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Intent it = getIntent();
        if (it != null) {
            Usuario u = it.getExtras().getParcelable("usuario");
            System.out.println(u.getId());
        }
    }

}

