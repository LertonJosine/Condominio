package Condominio;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Validacoes {
    private String str;
    private int inteiro, intervalo;
    private float fl;
    private char caracter;
    private byte bt;

    public Validacoes(String msg, int ini, int fim, char vl1, char vl2, byte inib, byte fimb) throws IOException {
        str = ValidarStr(msg);
        intervalo = ValidaIntv(msg, ini, fim);
        fl = ValidarFloat(msg);
        inteiro = ValidarIntNeg(msg);
        caracter = ValidarChar(msg, vl1, vl2);
        bt = ValidarByte(msg, inib, fimb);
    }

    public Validacoes() throws IOException {

    }

    // Valida string em caso de ela estar vazia

    public String ValidarStr(String msg) throws IOException {
        BufferedReader c = new BufferedReader(new InputStreamReader(System.in));
        do {
            System.out.println(msg);
            str = c.readLine();
            if (str.equals(""))
                System.out.println("N�o foi introduzida nenhuma informa��o\n"
                        + "Porfavor tente novamente.");
        } while (str.equals(""));
        return str;
    }

    // Valida caso o numero seja negativo e faz tratamento de erro

    public int ValidarIntNeg(String msg) throws IOException {
        BufferedReader c = new BufferedReader(new InputStreamReader(System.in));
        boolean erro = false;
        do {
            System.out.println(msg);
            try {
                inteiro = Integer.parseInt(c.readLine());
                erro = false;
            } catch (NumberFormatException e) {
                System.out.println("Tipo de dado incorrecto!\nTente novamente");
                erro = true;
            }
            if (inteiro < 0)
                System.out.println("Dado invalido!\nTente novamente.");
        } while (inteiro < 0 || erro == true);
        return inteiro;
    }

    public float ValidarFloat(String msg) throws IOException {
        BufferedReader c = new BufferedReader(new InputStreamReader(System.in));
        boolean erro = false; // informa se existe algum erro ou n�o

        do {
            System.out.println(msg);
            try {
                fl = Float.parseFloat(c.readLine());
                erro = false;
            } catch (NumberFormatException e) {
                System.out.println("Tio de dado incorrecto!\nTente novamente.");
                erro = true; // caso tenha erro recebe true
            }
        } while (erro == true);
        return fl;
    }

    // Validar, tratar erro de dados do tipo byte
    public int ValidaIntv(String msg, int ini, int fim) throws IOException {
        BufferedReader c = new BufferedReader(new InputStreamReader(System.in));
        boolean erro = false;
        do {
            System.out.println(msg);
            try {
                intervalo = Byte.parseByte(c.readLine());
                erro = false;
            } catch (NumberFormatException e) {
                System.out.println("Tipo de dado incorrecto!\nTente novamente.");
                erro = true;
            }
            if (intervalo < ini || intervalo > fim)
                System.out.println("Valor elevado ou muito pequeno!\nTente novamente.");
        } while (intervalo < ini || intervalo > fim || erro == true);
        return intervalo;
    }

    public char ValidarChar(String msg, char vl1, char vl2) throws IOException {
        BufferedReader c = new BufferedReader(new InputStreamReader(System.in));
        boolean erro = false;
        do {
            System.out.println(msg);
            try {
                caracter = c.readLine().charAt(0);
                erro = false;
            } catch (Exception e) {
                System.out.println("Tipo de dado incorrecto!\nTente novamente.");
                erro = true;
            }
            if (caracter != vl1 & caracter != vl2)
                System.out.println("Valor invalido!\nTente novamente.");
        } while (erro == true || (caracter != vl1 & caracter != vl2));
        return caracter;
    }

    public byte ValidarByte(String msg, byte ini, byte fim) throws IOException {
        BufferedReader c = new BufferedReader(new InputStreamReader(System.in));
        boolean erro = false;
        do {
            System.out.println(msg);
            try {
                bt = Byte.parseByte(c.readLine());
                erro = false;
            } catch (Exception e) {
                System.out.println("Tipo de dado incrrecto!\nTente novamente");
                erro = true;
            }
        } while (erro || bt < ini || bt > fim);
        return bt;

    }

    public String ValidarStrInt(String msg, byte max) throws IOException {
        do {
            str = ValidarStr(msg);
            if (str.length() < max || str.length() > max)
                System.out.println("Tamanho da string diferente de " + max);
        } while (str.length() < max || str.length() > max);
        return str;
    }
}
