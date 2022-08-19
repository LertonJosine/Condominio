package Condominio;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Vector;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class Condominio {

	public static void Gravar(Object ob, String ficheiro) throws IOException, ClassNotFoundException {

		Vector vc;
		try {
			FileInputStream fi = new FileInputStream(ficheiro);
			ObjectInputStream obj = new ObjectInputStream(fi);

			vc = (Vector) obj.readObject();
			obj.close();

			vc.addElement(ob);
			vc.trimToSize();

			FileOutputStream fo = new FileOutputStream(ficheiro, false);
			ObjectOutputStream objo = new ObjectOutputStream(fo);

			objo.writeObject(vc);
			objo.close();

			System.out.println("Gravado com exito!");
		} catch (FileNotFoundException e) {
			vc = new Vector();
			vc.addElement(ob);

			FileOutputStream fo = new FileOutputStream(ficheiro, false);
			ObjectOutputStream objo = new ObjectOutputStream(fo);

			objo.writeObject(vc);
			objo.close();
			System.out.println("Gravado com exito!");
		}
	}

	public static Vector Abrir(String ficheiro) throws IOException, ClassNotFoundException {
		Vector vc = new Vector();
		try {
			FileInputStream fi = new FileInputStream(ficheiro);
			ObjectInputStream obj = new ObjectInputStream(fi);

			vc = (Vector) obj.readObject();
			vc.trimToSize();
			obj.close();

		} catch (FileNotFoundException e) {
			System.out.println("Ficheiro não encontrado");
		}
		return vc;

	}

	public static void Actualizar(Vector vc, String ficheiro) throws IOException {

		FileOutputStream fo = new FileOutputStream(ficheiro);
		ObjectOutputStream obj = new ObjectOutputStream(fo);

		obj.writeObject(vc);
		obj.close();
	}

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Validacoes vl = new Validacoes(); // Instaciacao da classe validacao
		Vector vc = new Vector(); // Intanciacao do vector
		VenderCasa vdc;
		controle_renda_parcela crp = new controle_renda_parcela();
		Casa cs;
		int cont = 0;
		int numero_casa = 0;
		byte mes = 0;
		Arrendar ar;
		byte escp, esc = 0;
		do {
			// Ilustarar o menu na tela
			System.out.println("-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_");
			System.out.println("\t\tMENU");
			System.out.println("-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_");
			System.out.println("\t1-Registrar Casa\n\t2-Ver informações de Casas"
					+ "\n\t3-Pagamentos e controle"
					+ "\n\t4-Sair");
			escp = (byte) vl.ValidaIntv("", 1, 4);

			switch (escp) {

				case 1:// Caso o usuario deseje cadastrar nova casa
					String rua;
					byte t_casa;
					float valor;
					int nr_casa;
					rua = vl.ValidarStr("Introduza o nome da rua");
					nr_casa = vl.ValidarIntNeg("Introduza o numero da casa");
					t_casa = vl.ValidarByte("Introduza o tipo de casa", (byte) 1, (byte) 40);
					valor = vl.ValidarFloat("Introduza o valor da casa");
					cs = new Casa(rua, nr_casa, t_casa, valor);
					cs.setInterior();
					Gravar(cs, "Stock.dat");

					break;

				// Caso deseje visualisar informações de casas
				case 2:// Caso queira ver informações das casas
					esc = vl.ValidarByte("\t1-Ver informações de casa disponiveis\n\t2-Ver" +
							"informações de casa ocupadas", (byte) 1, (byte) 2);
					switch (esc) {
						case 1:// Caso queira ver informações de casa disponiveis
							Busca bc = new Busca();
							bc.VerCasaDisp();
							break;
						case 2: // caso queira ver informações de uma casa ocupada
							numero_casa = vl.ValidarIntNeg("Introduza o numero da casa");
							boolean sinal = false;
							vc = Abrir("Casa.dat");

							// Inicializacao dos arrays

							// Passagem dos objectos para os arrays
							for (int c = 0; c < vc.size(); c++) {

								// caso o numero da casa seja achado
								if (numero_casa == ((Casa) vc.elementAt(c)).getLocalizcao().getNr_casa()) {
									System.out.println(((Casa) vc.elementAt(c)).toString());
									sinal = true;
								} else if (c == (vc.size() - 1) && sinal == false)
									System.out.println("Casa não encotrada");

							}

							break;
					}

					break;

				case 3:// Caso deseje efectuar pagamentos
					esc = vl.ValidarByte("\t1-Pagamentos\n\t2-Controle de pagamento", (byte) 1, (byte) 2);
					switch (esc) {
						case 1:// Caso deseje efectuar pagamentos
							esc = vl.ValidarByte("\t1-Arrendar\n\t2-Parcelar\n\t3-Comprar" +
									"\n\t4-Pagar Renda\n\t5-Pagar Parcela", (byte) 1, (byte) 5);
							switch (esc) {

								case 1:// caso queira arrendar

									System.out.println("Em desenvolvimento");
									int n;
									n = vl.ValidarIntNeg("Introduza o numero da casa");
									mes = vl.ValidarByte("Introduza o mes", (byte) 1, (byte) 12);

									ar = new Arrendar(esc, n, mes);
									break;

								// Caso queira Parcelar
								case 2:
									System.out.println("Em desenvolvimento");
									int nx;
									n = vl.ValidarIntNeg("Introduza o numero da casa");
									crp.controle_parcela(numero_casa, mes);
									vc = Abrir("Casa.dat");
									for (int q = 0; q < vc.size(); q++) {

										if (((Casa) vc.elementAt(q)).getLocalizcao().getNr_casa() == numero_casa) {
											mes = vl.ValidarByte("Introduza o mes [1-12]", (byte) 1, (byte) 12);
											if (((Casa) vc.elementAt(q)).getCalendarioparc() == null) {
												System.out.println(
														"nao e possivel pagar a parcelar desta casa\na casa ja esta sendo parcelada");
											}

											else {
												((Casa) vc.elementAt(q)).getParcela();
												System.out.println("Parcela feito com exito");

											}
										}

										break;
									}
									// Caso queira vender
								case 3:// Caso queira vender
									System.out.println("Em desenvolvimento\n----- ");
									vdc = new VenderCasa();
									numero_casa = vl.ValidarIntNeg("Introduza o numero da casa");
									vdc.Vender(numero_casa);
									break;

								// Caso queira pagar renda
								case 4:// Caso queira pagar renda
									System.out.println("EM desenvolvimento");
									numero_casa = vl.ValidarIntNeg("Introduza o numero da casa");
									vc = Abrir("Casa.dat");
									cont = 0;
									for (int c = 0; c < vc.size(); c++) {

										if (((Casa) vc.elementAt(c)).getLocalizcao().getNr_casa() == numero_casa) {
											cont += 1;
											mes = vl.ValidarByte("Introduza o mes [1-12]", (byte) 1, (byte) 12);
											if (((Casa) vc.elementAt(c)).getCalendariorend() == null)
												System.out.println(
														"Não é possivel pagar renda\nA casa está sendo parcelada");
											else {
												((Casa) vc.elementAt(c)).setCalendarioRend(mes);
												System.out.println("Pagamento feito com exito");
												Actualizar(vc, "Casa.dat");
											}
										}
									}
									if (cont == 0) {
										System.out.println("A casa não foi encontrada.");
									}
									break;

								// Caso queira pagar parcela
								case 5:
									numero_casa = vl.ValidarIntNeg("Introduza o numero da casa");
									mes = vl.ValidarByte("Introduza o mes", (byte) 1, (byte) 12);

									vc = Abrir("Casa.dat");

									for (int c = 0; c < vc.size(); c++) {

										if (((Casa) vc.elementAt(c)).getLocalizcao().getNr_casa() == numero_casa) {
											mes = vl.ValidarByte("Introduza o mes [1-12]", (byte) 1, (byte) 12);
											if (((Casa) vc.elementAt(c)).getCalendarioparc() == null)
												System.out.println(
														"Não é possivel pagar a parcela desta casa\nA casa já está sendo arrendada");
											else {
												((Casa) vc.elementAt(c)).setCalendarioPar(mes);
												System.out.println("Pagamento feito com exito");
											}
										}
									}
									break;

							}
							break;

						case 2:// Caso queira verificar os meses em divida
							esc = vl.ValidarByte("\t1-Controle de Renda\n\t2-Controle de parcela", (byte) 1, (byte) 2);
							switch (esc) {
								case 1:// caso queira ver o calendario de renda
									numero_casa = vl.ValidarIntNeg("Introduza o numero da casa");
									mes = vl.ValidarByte("Introduza o mes\n[1-12]", (byte) 1, (byte) 12);
									System.out.println("Resultado para o estado de pagamento do mês " + mes);
									crp.controle_renda(numero_casa, mes);
									break;
								case 2:// caso queira ver o estado do pagamento de um mes
									numero_casa = vl.ValidarIntNeg("Introduza o numero da casa");
									mes = vl.ValidarByte("Introduza o mes\n[1-12]", (byte) 1, (byte) 12);
									System.out.println("Resultado para o estado de pagamento do mês " + mes);
									crp.controle_parcela(numero_casa, mes);
									break;
							}
							break;
					}

					break;

			}
		} while (escp != 4);

	}
}