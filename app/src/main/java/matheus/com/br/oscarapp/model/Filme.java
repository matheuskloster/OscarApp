package matheus.com.br.oscarapp.model;

import android.graphics.Bitmap;

/**
 * Created by Matheus on 18/11/17.
 */

public class Filme {

    private Integer id;
    private String nome;
    private String genero;
    private Bitmap foto;


    public Filme() {
    }

    public Filme(Integer id, String nome, String genero, Bitmap foto) {
        this.id = id;
        this.nome = nome;
        this.genero = genero;
        this.foto = foto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Bitmap getFoto() {
        return foto;
    }

    public void setFoto(Bitmap foto) {
        this.foto = foto;
    }
}

