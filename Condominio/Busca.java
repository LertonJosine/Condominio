package Condominio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Vector;

public class Busca {

    public Busca() throws IOException {

    }

    public void VerCasaDisp() throws IOException, ClassNotFoundException {
        Validacoes vl = new Validacoes();
        Vector vc;
        Casa array[];
        try {
            FileInputStream fi = new FileInputStream("Stock.dat");
            ObjectInputStream obj = new ObjectInputStream(fi);

            vc = (Vector) obj.readObject(); // atribuicao do conteudo do ficheiro ao vector
            if (vc.size() == 0)
                System.out.println("Não temos casas disponiveis");
            obj.close();
            /*
             * Criei um array para receber todos os objectos que estão no vector
             * Uma vez que tentado aceder aos metodos dos objectos puxando pelo vector
             * não estava a ser possivel
             */

            for (int c = 0; c < vc.size(); c++) {
                System.out.println(((Casa) vc.elementAt(c)).toString());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Não temos casas disponiveis por enquanto.");
        }
    }
}
