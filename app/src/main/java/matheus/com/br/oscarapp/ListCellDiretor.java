package matheus.com.br.oscarapp;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import matheus.com.br.oscarapp.model.Diretor;

import static matheus.com.br.oscarapp.MenuActivity.diretoresList;

public class ListCellDiretor extends ArrayAdapter<Diretor> {

    private final Activity context;

    public ListCellDiretor(Activity context) {
        super(context, R.layout.list_cell_diretor, diretoresList);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.list_cell_diretor, null, true);

        TextView titleTextView = (TextView) rowView.findViewById(R.id.nome);

        titleTextView.setText(diretoresList.get(position).getNome());

        return rowView;
    }
}
