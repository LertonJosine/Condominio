package Condominio;

import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;

public class Arrendar {

	public Arrendar(byte esc, int numero_casa, byte mes) throws IOException, ClassNotFoundException {
		Vector vc, vc2 = new Vector();
		Validacoes vl = new Validacoes();
		Casa array[];
		controle_renda_parcela crp = new controle_renda_parcela();

		byte cont = 0; // contador de voltas no loop
		try {

			// procedimento para leitura do ficheiro
			FileInputStream fop = new FileInputStream("Stock.dat"); // carregamento do ficheiro
			ObjectInputStream objo = new ObjectInputStream(fop);
			vc = (Vector) objo.readObject();
			objo.close();

			// inicializacao dos arrays
			array = new Casa[vc.size()];

			// Caso não tenha casas cadastradas no array
			if (vc.size() == 0)
				System.out.println("Não temos casas disponinveis.");

			// se tiver...
			else {
				String primeiro, segundo, ultimo, t_doc, n_doc;
				// Vai inserir as casas e as suas localizacoes no seu devido array
				for (int c = 0; c < vc.size(); c++) {

					// Caso ache a casa requisitada

					if (((Casa) vc.elementAt(c)).getLocalizcao().getNr_casa() == numero_casa) {
						// Tem que pagar a renda
						cont += 1;
						((Casa) vc.elementAt(c)).Inicializador(esc);
						((Casa) vc.elementAt(c)).setCalendarioRend(mes);
						primeiro = vl.ValidarStr("Introduza o nome");
						segundo = vl.ValidarStr("Introduza o sobre nome");
						ultimo = vl.ValidarStr("mIntroduza o apelido");
						t_doc = vl.ValidarStr("Introduza o tipo de documento");
						n_doc = vl.ValidarStr("Introduza o numero do documento");

						((Casa) vc.elementAt(c)).setMorador(primeiro, segundo, ultimo, t_doc, n_doc);

						try {

							// Apos o procedimento de "ocupação da casa iremos gravar a casa no ficheiro
							// Casa.dat"
							FileInputStream aa = new FileInputStream("Casa.dat");
							ObjectInputStream bb = new ObjectInputStream(aa);

							vc2 = (Vector) bb.readObject();
							bb.close();

							// Procedimento de gravacao
							vc2.addElement(vc.elementAt(c));

							FileOutputStream fo2 = new FileOutputStream("Casa.dat");
							ObjectOutputStream obo = new ObjectOutputStream(fo2);

							obo.writeObject(vc2);
							obo.close();

							// Vai eliminar a casa do ficheiro stock
							FileOutputStream fo3 = new FileOutputStream("Stock.dat");
							ObjectOutputStream obo2 = new ObjectOutputStream(fo3);
							vc.remove(c);
							obo2.writeObject(vc);
							obo2.close();

							System.out.println("Processo concluido com exito!");
						} catch (FileNotFoundException e) {

							vc2.addElement(vc.elementAt(c));
							FileOutputStream fo2 = new FileOutputStream("Casa.dat");
							ObjectOutputStream obo = new ObjectOutputStream(fo2);

							obo.writeObject(vc2);
							obo.close();

							// Vai eliminar a casa do ficheiro stock
							FileOutputStream fo3 = new FileOutputStream("Stock.dat");
							ObjectOutputStream obo2 = new ObjectOutputStream(fo3);
							vc.remove(c);
							obo2.writeObject(vc);
							obo2.close();

							System.out.println("Processo concluido com exito!");
						}
					}
				}

			}
		} catch (FileNotFoundException e) {
			System.out.println("Ficheiro não encontrado");
		}
	}

}
