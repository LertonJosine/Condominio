package Condominio;

import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;
import java.util.Vector;

public class teste {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Casa cs;
		Localizacao lc;
		Vector vc = new Vector();

		try {
			FileInputStream a = new FileInputStream("Casa.dat");
			ObjectInputStream b = new ObjectInputStream(a);

			vc = (Vector) b.readObject();
			vc.trimToSize();

			for (int c = 0; c < vc.size(); c++) {
				System.out.println(vc.elementAt(c).getClass());
			}

			System.out.println(vc.size());
		} catch (FileNotFoundException e) {
			System.out.println("Ficheiro n�o encontrado");
		}
	}
}
