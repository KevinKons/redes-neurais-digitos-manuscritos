package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LeitorDataset {

    public void lerDataset(int[][] entradas, int[][] saidas, String nomeArquivo) {

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

    private void adicionarDadosLinha(int[][] entradas, int[][] saidas, String[] linha, int countLinha) {

        for (int i = 0; i < 256; i++) {
            entradas[countLinha][i] = Integer.parseInt(linha[i].replace(".","-").split("-")[0]);
        }
        for (int i = 256; i < 266; i++) {
            if (Integer.parseInt(linha[i]) != 0) {
                saidas[countLinha][0] = i - 256;
                break;
            }
        }
    }

}
