package Condominio;

import java.io.IOException;

public class Morador extends Nome {
    protected String t_doc, n_doc;

    public Morador(String primeiro, String segundo, String ultimo, String t_doc, String n_doc) throws IOException {
        super(primeiro, segundo, ultimo);
        this.n_doc = n_doc;
        this.t_doc = t_doc;

    }

    public Morador() {

    }

    public String getNome() {
        return super.toString();
    }

    public String getTipo_de_doc() {
        return t_doc;
    }

    public String getNumero_de_doc() {
        return n_doc;
    }

    public String toString() {
        return "\nNome: " + super.toString() + "\nTipo de documento: " + t_doc + "\nNumero de documento: " + n_doc;
    }
}
