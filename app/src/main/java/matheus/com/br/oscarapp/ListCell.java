package matheus.com.br.oscarapp;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import matheus.com.br.oscarapp.model.Filme;

import static matheus.com.br.oscarapp.MenuActivity.filmesList;

/**
 * Created by Matheus on 19/11/17.
 */


public class ListCell extends ArrayAdapter<Filme> {

    private final Activity context;

    public ListCell(Activity context) {
        super(context, R.layout.list_cell, filmesList);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.list_cell, null, true);

        ImageView img = (ImageView) rowView.findViewById(R.id.img);
        TextView titleTextView = (TextView) rowView.findViewById(R.id.nome);
        TextView subTitleTextView = (TextView) rowView.findViewById(R.id.genero);

        img.setImageBitmap(filmesList.get(position).getFoto());
        titleTextView.setText(filmesList.get(position).getNome());
        subTitleTextView.setText(filmesList.get(position).getGenero());

        return rowView;
    }
}

