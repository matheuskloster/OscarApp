package matheus.com.br.oscarapp.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Matheus on 18/11/17.
 */

public class Diretor implements Parcelable{
    private Integer id;
    private String nome;

    public Diretor(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Diretor() {
    }

    protected Diretor(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        nome = in.readString();
    }

    public static final Creator<Diretor> CREATOR = new Creator<Diretor>() {
        @Override
        public Diretor createFromParcel(Parcel in) {
            return new Diretor(in);
        }

        @Override
        public Diretor[] newArray(int size) {
            return new Diretor[size];
        }
    };

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
            parcel.writeInt(id);
        }
        parcel.writeString(nome);
    }
}
