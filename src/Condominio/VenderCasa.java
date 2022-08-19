package Condominio;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;
import java.io.IOException;
import java.io.ObjectInputStream;
public class VenderCasa {
	private float pagamento;
	public VenderCasa() {
		
	}
	public void Vender(int numero_casa)throws IOException, ClassNotFoundException {
		Localizacao lc[];
		Casa cs [];
		Vector vc = new Vector();
		Validacoes vl = new Validacoes();
		
		//Caregamento do ficheiro Stock para obtenção da casa
		
		FileInputStream fi = new FileInputStream("Stock.dat");
		ObjectInputStream obj = new ObjectInputStream(fi);
		
		vc = (Vector) obj.readObject();
		vc.trimToSize();
		obj.close();
		
		cs = new Casa[vc.size()];
		lc = new Localizacao[vc.size()];
		
		//Leitura das casa do vector e pasagem para os arrays
		
		for(int c=0; c<vc.size();c++) {
			cs[c]= (Casa) vc.elementAt(c);
			lc[c]= (Localizacao) cs[c].getLocalizcao();
			
			//Caso ache a casa vai mostrar as informações e proceder com a venda
			
			if(lc[c].getNr_casa()==numero_casa) {
				System.out.println(cs[c].toString());
				byte esc;
				esc = vl.ValidarByte("1-Confirmar\n2-Cancelar", (byte)1, (byte)2);
				if(esc == 1) {
					pagamento = vl.ValidarFloat("Introduza o valor pago");
					if(pagamento >= cs[c].getValor()) {
						FileOutputStream fo = new FileOutputStream("Stock.dat");
						ObjectOutputStream objo = new ObjectOutputStream(fo);
						
						//processo de eliminacao da casa do ficheiro de stock
						vc.remove(c);
						vc.trimToSize();
						objo.writeObject(vc);
						objo.close();
						System.out.println("Casa Vendida");
						
					}
					else {
						System.out.println("Valor insuficiente!Venda não concluida.");
					}
				}
				
			}
		}
	}
	
}
