package Condominio;

import java.io.IOException;

public class Casa extends Morador {

    private byte t_casa;
    private float valor, renda, parcela;
    private String interior;// organizar;
    private Localizacao lc;
    private Morador morador;
    private String[][] calendariorend, calendarioparc;

    public Casa(String primeiro, String segundo, String ultimo, String rua, int nr_casa, String t_doc, String n_doc,
            byte t_casa, float valor) throws IOException {

        // contrutor da super classe
        super(primeiro, segundo, ultimo, t_doc, n_doc);

        lc = new Localizacao(rua, nr_casa);
        this.t_casa = t_casa;
        this.valor = valor;

        // Valor da renda
        renda = valor / 100;

        // Valor da parcela
        parcela = valor / 60;

    }

    public Casa(String rua, int nr_casa, byte t_casa, float valor) throws IOException {
        lc = new Localizacao(rua, nr_casa);
        this.t_casa = t_casa;
        this.valor = valor;
        renda = valor / 100;

        parcela = valor / 60;

    }

    public Casa() {

    }

    public void Inicializador(byte esc) throws IOException {
        if (esc == 2) {
            calendarioparc = new String[2][13];
            for (int coluna = 0; coluna < 13; coluna++) {
                calendarioparc[0][coluna] = String.valueOf(coluna + 1);
                calendarioparc[1][coluna] = "Em divida";
            }
        } else if (esc == 1) {
            calendariorend = new String[2][13];
            for (int coluna = 0; coluna < 13; coluna++) {
                calendariorend[0][coluna] = String.valueOf(coluna + 1);
                calendariorend[1][coluna] = "Em divida";
            }
        }
        Validacoes vl = new Validacoes();
        // esc = vl.ValidarByte("1-Parcela\n2-Renda", (byte) 1, (byte) 2);

    }

    // Area de setters

    public void setMorador(String primeiro, String segundo, String ultimo, String t_doc, String n_doc)
            throws IOException {
        morador = new Morador(primeiro, segundo, ultimo, t_doc, n_doc);
    }

    // Configurar a informação do interior
    public String setInterior() throws IOException {
        Validacoes vl = new Validacoes();

        char in;
        in = vl.ValidarChar("A casa está mobiliada?", 's', 'n');
        if (in == 's')
            interior = "mobiliada";
        else
            interior = "não mobiliada";
        return interior;
    }

    // Area de getters

    // Saber se a casa está mobiliada
    public String getInterior() {
        return interior;
    }

    // Saber o valor da renda
    public float getRenda() {
        renda = (int) valor / 100;
        return renda;
    }

    public float getValor() {
        return valor;
    }

    // Saber o valor da parcela
    public float getParcela() {
        return parcela;
    }

    // Saber o valor das parcelas
    public byte getTipo_Casa() {
        return t_casa;
    }

    // Obter a localizacao

    public Localizacao getLocalizcao() {
        return lc;
    }

    public Morador getMorador() {
        return morador;
    }

    public String[][] getCalendariorend() {
        return calendariorend;
    }

    public String[][] getCalendarioparc() {
        return calendarioparc;
    }

    // pagamento da parcela
    public void setCalendarioPar(byte mes) throws IOException, ClassNotFoundException {
        String estado;
        Validacoes vl = new Validacoes();
        controle_renda_parcela crp = new controle_renda_parcela();
        System.out.println("O valor da parcela � " + parcela + "Mt");
        float a;
        a = vl.ValidarFloat("Introduza o valor pago"); // a vatiavel 'a' � o valor pago pelo cliente
        // Caso tenha pago

        if (parcela == a) {
            estado = "Pago";
            calendarioparc[1][mes] = estado;
        }
        if (a == 0) {
            estado = "Em divida";
            calendarioparc[1][mes] = estado;
        }
        System.out.println("Total já pago: " + crp.CalCumulativo(lc.getNr_casa()));
    }

    // para pagamento da renda
    public void setCalendarioRend(byte mes) throws IOException {
        Validacoes vl = new Validacoes();
        System.out.println("O valor da renda é " + renda + "Mt");
        float a;
        a = vl.ValidarFloat("Introduza o valor pago"); // a variavel 'a' � o valor pago pelo cliente
        // Caso tenha pago
        String estado;

        if (renda == a) {
            estado = "Pago";
            calendariorend[1][mes] = estado;
        }
        if (a == 0) {
            estado = "Em divida";
            calendariorend[1][mes] = estado;
        }
    }

    @Override
    public String toString() {
        if (morador == null) {
            return "\nTipo de casa: " + t_casa + "\nValor: " + valor + "\nValor da Renda "
                    + renda + "\nValor da parcela: " + parcela + "\nInterior: " +
                    interior + "\nNumero da casa: " + lc.getNr_casa();
        } else {

            return "\n\tInformações do Morador" + morador.toString() + "\n\n\tInformações da Casa" + "\nTipo de casa: "
                    + t_casa + "\nValor: " + valor + "\nValor da Renda "
                    + renda + "\nValor da parcela: " + parcela + "\nInterior: " +
                    interior + "\nNumero da casa: " + lc.getNr_casa();
        }
    }

}
