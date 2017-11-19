package matheus.com.br.oscarapp.model;

/**
 * Created by Matheus on 18/11/17.
 */

public class Diretor {
    private Integer id;
    private String nome;

    public Diretor(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Diretor() {
    }

    public int getId() {
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
}
