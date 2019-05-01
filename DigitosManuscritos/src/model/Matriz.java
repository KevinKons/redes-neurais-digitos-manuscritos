package model;

public class Matriz {

    private static Matriz instance;

    public synchronized static Matriz getInstance() {
        if(instance == null)
            instance = new Matriz();
        return instance;
    }

    private Matriz() {}

    private boolean[][] celulas;
    private final int qntLinhas = 16;
    private final int qntColunas = 16;

    public void montarMatriz() {
        this.celulas = new boolean[qntLinhas][qntColunas];

        for(boolean[] linha : celulas)
            for(boolean celula : linha)
                celula = false;
    }

    public int getQntLinhas() {
        return qntLinhas;
    }

    public int getQntColunas() {
        return qntColunas;
    }

    public boolean[][] getCelulas() {
        return celulas;
    }
}
