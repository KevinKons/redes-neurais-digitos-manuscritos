package controller;

import rns.RedeNeural;
import util.LeitorDataset;

public class Main {

    public static void main(String[] args) {
        double[][] entradas = new double[1593][256];
        double[][] saidas  = new double[1593][10];

        LeitorDataset leitorDataset = LeitorDataset.getInstance();
        leitorDataset.lerDataset(entradas, saidas,"semeion.data");

        RedeNeural rn = new RedeNeural();
        rn.aprender(entradas, saidas,1);
    }
}
