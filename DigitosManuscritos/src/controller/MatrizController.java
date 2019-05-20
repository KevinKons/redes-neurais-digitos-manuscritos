package controller;

import model.Matriz;

public class MatrizController {

    private Matriz matriz;

    public int getQntLinhas() {
        if (matriz == null) {
            return 0;
        }
        return matriz.getQntLinhas();
    }

    public int getQntColunas() {
        if (matriz == null) {
            return 0;
        }
        return matriz.getQntColunas();
    }

    public void criarMatriz() {
        matriz = Matriz.getInstance();
        matriz.montarMatriz();
    }

    public boolean[][] getMatriz() {
        return matriz.getCelulas();
    }

    public boolean matrizClicada(int y, int x) {
        matriz.getCelulas()[y][x] = !matriz.getCelulas()[y][x];
        return matriz.getCelulas()[y][x];
    }

    public void treinarRede(){

    }

    public double[][] converterBooleanToDouble(boolean[][] matriz){
        double[][] matrizConvertida = new double[matriz.length][matriz[0].length];

        for(int i = 0; i < matriz.length; i++){
            for(int k = 0; k < matriz[0].length; i++){
                if(!matriz[i][k])
                    matrizConvertida[i][k] = 0.0;
                else
                    matrizConvertida[i][k] = 1.0;
            }
        }
        return matrizConvertida;
    }
}
