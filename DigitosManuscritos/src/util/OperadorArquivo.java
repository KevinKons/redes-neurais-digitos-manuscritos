package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class OperadorArquivo {

    private static OperadorArquivo instance;

    public static OperadorArquivo getInstance() {
        if (instance == null)
            instance = new OperadorArquivo();

        return instance;
    }

    private OperadorArquivo() {
    }

    public void lerDataset(double[][] entradas, double[][] saidas, String nomeArquivo) {
        try {
            BufferedReader ler = new BufferedReader(new FileReader("./" + nomeArquivo));
//            BufferedReader ler = new BufferedReader(new FileReader("../dataset-digitos-manuscritos/" + nomeArquivo));
            String str;
            int countLinha = 0;

            while ((str = ler.readLine()) != null) {
                String[] linha = str.split(" ");
                adicionarDadosLinha(entradas, saidas, linha, countLinha);
                countLinha++;
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void adicionarDadosLinha(double[][] entradas, double[][] saidas, String[] linha, int countLinha) {

        for (int i = 0; i < 256; i++) {
            entradas[countLinha][i] = Integer.parseInt(linha[i].replace(".", "-").split("-")[0]);
        }

        for (int i = 0; i < 10; i++) {
            saidas[countLinha][i] = Double.parseDouble(linha[i + 256]);
        }
    }

    public void escreverSaida(String epocasError, String entradasSaidas, int numeroModelo) {
        try {
            FileWriter writer = new FileWriter("output.txt", true);
            writer.write("Modelo: " + numeroModelo);
            writer.write("\n\n");
            writer.write("\n\n");
            writer.write(epocasError);
            writer.write("\n\n");
            writer.write(entradasSaidas);
            writer.write("\n\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void escreverSaidas(int[] vetorSaidaReal, int[] vetorSaidaPrevista, double acuracia, double precisaoMedia, double sensibilidade, double fScore) {
        String valoresTabela = "";
        valoresTabela = "\nAcuracia: " + acuracia
                + "\nPrecisao: " + precisaoMedia
                + "\nRecall: " + sensibilidade
                + "\nF1-Score: " + fScore;
        try {
            FileWriter writer = new FileWriter("output.txt", true);
            writer.write(valoresTabela);
            writer.write("\n");
            writer.write("--------------------------------------------------------------------------------------");
            writer.write("\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
