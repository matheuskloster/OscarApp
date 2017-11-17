package matheus.com.br.oscarapp;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Matheus on 16/11/17.
 */

public class Usuario implements Parcelable {

    private Long id;
    private String username;
    private String password;
    private String filme;
    private String diretor;


    public Usuario(Long id, String username, String password,  String filme, String diretor) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.filme = filme;
        this.diretor = diretor;

    }

    public Usuario() {
    }

    protected Usuario(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
        username = in.readString();
        password = in.readString();
        filme = in.readString();
        diretor = in.readString();
    }

    public static final Creator<Usuario> CREATOR = new Creator<Usuario>() {
        @Override
        public Usuario createFromParcel(Parcel in) {
            return new Usuario(in);
        }

        @Override
        public Usuario[] newArray(int size) {
            return new Usuario[size];
        }
    };

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDiretor() {
        return diretor;
    }

    public String getFilme() {
        return filme;
    }

    public void setFilme(String filme) {
        this.filme = filme;
    }

    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (id == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeLong(id);
        }
        parcel.writeString(username);
        parcel.writeString(password);
        parcel.writeString(filme);
        parcel.writeString(diretor);
    }
}
