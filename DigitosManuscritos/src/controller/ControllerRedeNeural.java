package controller;

import rns.RedeNeural;
import util.LeitorDataset;

public class ControllerRedeNeural {

    @SuppressWarnings("Duplicates")
    public void montarEstruturaCrossValidation(int folds) {
        double[][] entradas = new double[RedeNeural.quantidadeExemplos][RedeNeural.quantidadePixels];
        double[][] saidas  = new double[RedeNeural.quantidadeExemplos][10];

        LeitorDataset leitorDataset = LeitorDataset.getInstance();
        leitorDataset.lerDataset(entradas, saidas,"semeion.data");

        int tamanhoFold = (int) Math.floor(RedeNeural.quantidadeExemplos / folds);
        for(int i = 0; i < folds; i++) {
            treinarApartirDeModelo(i, tamanhoFold, entradas, saidas);
        }
    }

    private void treinarApartirDeModelo(int numeroModelo, int tamanhoFold, double[][] todasEntradas, double[][] todasSaidas) {
        double[][] entradasParaTeste = new double[tamanhoFold][RedeNeural.quantidadePixels];
        double[][] saidasParaTeste  = new double[tamanhoFold][10];

        double[][] entradasParaConstrucao = new double[RedeNeural.quantidadeExemplos - tamanhoFold][RedeNeural.quantidadePixels];
        double[][] saidasParaConstrucao = new double[RedeNeural.quantidadeExemplos - tamanhoFold][10];

        int menorPosicaoVaziaTeste = 0;
        int menorPosicaoVaziaConstrucao = 0;

        int primeiraPosParaTeste = tamanhoFold * numeroModelo;
        for(int i = 0; i < todasEntradas.length; i++) {
            if(i >= primeiraPosParaTeste && i < primeiraPosParaTeste + tamanhoFold) {
                entradasParaTeste[menorPosicaoVaziaTeste] = todasEntradas[i];
                saidasParaTeste[menorPosicaoVaziaTeste] = todasSaidas[i];
                menorPosicaoVaziaTeste++;
            } else {
                entradasParaConstrucao[menorPosicaoVaziaConstrucao] = todasEntradas[i];
                saidasParaConstrucao[menorPosicaoVaziaConstrucao] = todasSaidas[i];
                menorPosicaoVaziaConstrucao++;
            }
        }

        RedeNeural rn = new RedeNeural();
        rn.aprender(entradasParaConstrucao, saidasParaConstrucao, entradasParaTeste, saidasParaTeste, numeroModelo);

    }
}
