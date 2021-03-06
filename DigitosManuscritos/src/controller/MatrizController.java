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

}
