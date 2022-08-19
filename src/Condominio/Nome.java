package Condominio;

import java.io.*;

public class Nome implements Serializable {
    private static final long serialVersionUID = 1L;

    protected String primeiro;
    protected String segundo;
    protected String ultimo;

    public Nome(String primeiro, String segundo, String ultimo) throws IOException {
        this.primeiro = primeiro;
        this.segundo = segundo;
        this.ultimo = ultimo;
    }

    public Nome() {

    }

    public String toString() {
        return "\t" + primeiro + "\t" + segundo + "\t" + ultimo;
    }
}
