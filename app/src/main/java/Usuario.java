/**
 * Created by Matheus on 15/11/17.
 */

public class Usuario {

    private int id;
    private String nome;
    private String password;


    public Usuario() {}

    public Usuario(int id, String nome, String password) {
        this.id = id;
        this.nome = nome;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
