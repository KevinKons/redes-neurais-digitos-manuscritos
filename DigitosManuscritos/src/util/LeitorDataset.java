package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LeitorDataset {

    private static LeitorDataset instance;

    public static LeitorDataset getInstance() {
        if(instance == null)
            instance = new LeitorDataset();

        return instance;
    }

    private LeitorDataset() {}

    public void lerDataset(double[][] entradas, double[][] saidas, String nomeArquivo) {
        try {
            BufferedReader ler = new BufferedReader(new FileReader("../dataset-digitos-manuscritos/" + nomeArquivo));
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
            entradas[countLinha][i] = Integer.parseInt(linha[i].replace(".","-").split("-")[0]);
        }

        for (int i = 0; i < 10; i++) {
            saidas[countLinha][i] = Double.parseDouble(linha[i + 256]);
        }
    }

}
