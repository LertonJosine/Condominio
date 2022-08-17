package Condominio;

//Importação das classes necessarias para o programa

import java.io.IOException;
import java.io.Serializable;

//Criação da classe localizacao

public class Localizacao implements Serializable {

    private String rua;
    private int nr_casa;

    public Localizacao(String rua, int nr_casa) throws IOException {
        this.nr_casa = nr_casa;
        this.rua = rua;

    }

    public String getRua() {
        return rua;
    }

    public int getNr_casa() {
        return nr_casa;
    }
}
