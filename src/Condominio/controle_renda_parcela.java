package Condominio;

import java.io.FileNotFoundException;
import java.util.Vector;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.IOException;

public class controle_renda_parcela {
	private float cumulativo;
	private float valor_parcela; // valor da prestacao
	private String[][] calendario = new String[2][13];
	// private Morador morador;

	// Damos a conhecer o mes em quest�o, o valor da renda e o estado do pagamente
	// inicia com falso
	public controle_renda_parcela() {

	}

	public void controle_renda(int numero_casa, byte mes) throws IOException, ClassNotFoundException {
		Validacoes vl = new Validacoes();
		Vector vc = new Vector();
		Casa cs[]; // array que vai receber as casas
		Localizacao lc[]; // array que vai receber a localizacao da casa

		// Carregamento do ficheiro casa

		try {
			FileInputStream fi = new FileInputStream("Casa.dat");
			ObjectInputStream obi = new ObjectInputStream(fi);

			vc = (Vector) obi.readObject(); // leitura do ficheiro para o Vector
			obi.close();

			String[][] calendario = new String[2][13];

			for (int p = 0; p < vc.size(); p++) {

				if (((Casa) vc.elementAt(p)).getLocalizcao().getNr_casa() == numero_casa) { // compara o numero da casa
																							// que est� sendo inserida
																							// com o
					// numero digitado pelo usuario
					calendario = ((Casa) vc.elementAt(p)).getCalendariorend();
					System.out.println(calendario[1][mes]);
				}
			}

		} catch (FileNotFoundException e) {
			System.out.println("Ficheiro não encontrado");
		}
	}

	public void controle_parcela(int numero_casa, byte mes) throws IOException, ClassNotFoundException {
		Validacoes vl = new Validacoes();
		Vector vc = new Vector();
		Casa cs[]; // array que vai receber as casas
		Localizacao lc[]; // array que vai receber a localizacao da casa

		// Carregamento do ficheiro casa

		try {
			FileInputStream fi = new FileInputStream("Casa.dat");
			ObjectInputStream obi = new ObjectInputStream(fi);

			vc = (Vector) obi.readObject(); // leitura do ficheiro para o Vector
			obi.close();
			String[][] calendario = new String[2][13];
			// Abaixo pretende-se obter o valor da renda
			/*
			 * para tal vamos vasculhar em cada casa quem tem o nr igual ao incerido
			 * uma vez descoberta a casa iremos nas informa��es da mesma saber o valor da
			 * renda
			 */
			for (int p = 0; p < vc.size(); p++) {
				if (((Casa) vc.elementAt(p)).getLocalizcao().getNr_casa() == numero_casa) { // compara o numero da casa
																							// que est� sendo inserida
																							// com o
					// numero digitado pelo usuario
					calendario = ((Casa) vc.elementAt(p)).getCalendarioparc();
					System.out.println(calendario[1][mes]);
				}
			}

		} catch (FileNotFoundException e) {
			System.out.println("Ficheiro não encontrado");
		}
	}

	public float CalCumulativo(int numero_casa) throws IOException, ClassNotFoundException {

		Vector vc2 = new Vector();
		Casa cs2[];
		Localizacao lc2[];

		try {
			FileInputStream fi2 = new FileInputStream("Casa.dat");
			ObjectInputStream obi2 = new ObjectInputStream(fi2);

			vc2 = (Vector) obi2.readObject(); // leitura do ficheiro para o Vector
			obi2.close();

			for (int p = 0; p < vc2.size(); p++) {

				if (((Casa) vc2.elementAt(p)).getLocalizcao().getNr_casa() == numero_casa) { // compara o numero da casa
																								// que est� sendo
																								// inserida com o
					// numero
					// digitado pelo usuario
					valor_parcela = ((Casa) vc2.elementAt(p)).getParcela();
					calendario = ((Casa) vc2.elementAt(p)).getCalendarioparc();
					// se for igual ira obter o valor da renda dessa casa
				}
			}
			for (int coluna = 0; coluna < calendario[1].length; coluna++) {
				if (1 < calendario.length) {
					if (calendario[1][coluna].equals("pago")) {
						cumulativo += valor_parcela;
					}
				}
			}

		} catch (FileNotFoundException e) {
			System.out.println("Ficheiro n�o encontrado");
		}
		return cumulativo;
	}

	public String[][] getCalendario() {
		return calendario;
	}
	/*
	 * public boolean getEstado(){
	 * return estado;
	 * }
	 */
}
