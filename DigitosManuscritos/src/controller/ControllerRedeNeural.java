package controller;

import rns.RedeNeural;
import util.OperadorArquivo;
import util.OperacaoComMatriz;

public class ControllerRedeNeural {

    private int tamanhoFold;

    @SuppressWarnings("Duplicates")
    public void montarEstruturaCrossValidation(int folds) {
        double[][] entradas = new double[RedeNeural.qntExemplos][RedeNeural.qntPixels];
        double[][] saidas  = new double[RedeNeural.qntExemplos][10];

        OperadorArquivo operadorArquivo = OperadorArquivo.getInstance();
        operadorArquivo.lerDataset(entradas, saidas,"semeion.data");

        tamanhoFold = RedeNeural.qntExemplos / folds;
        for(int i = 0; i < folds; i++)
            treinarApartirDeModelo(i, entradas, saidas);
    }

    private void treinarApartirDeModelo(int numeroModelo,
                                        double[][] todasEntradas, double[][] todasSaidas) {
        double[][] entradasParaTeste = new double[tamanhoFold][RedeNeural.qntPixels];
        double[][] saidasParaTeste  = new double[tamanhoFold][10];

        double[][] entradasParaConstrucao = new double[RedeNeural.qntExemplos - tamanhoFold][RedeNeural.qntPixels];
        double[][] saidasParaConstrucao = new double[RedeNeural.qntExemplos - tamanhoFold][10];

        int menorPosicaoVaziaTeste = 0;
        int menorPosicaoVaziaConstrucao = 0;

        int primeiraPosParaTeste = tamanhoFold * numeroModelo;
        for(int i = 0; i < todasEntradas.length; i++)
            if(i >= primeiraPosParaTeste && i < primeiraPosParaTeste + tamanhoFold) {
                entradasParaTeste[menorPosicaoVaziaTeste] = todasEntradas[i];
                saidasParaTeste[menorPosicaoVaziaTeste] = todasSaidas[i];
                menorPosicaoVaziaTeste++;
            } else {
                entradasParaConstrucao[menorPosicaoVaziaConstrucao] = todasEntradas[i];
                saidasParaConstrucao[menorPosicaoVaziaConstrucao] = todasSaidas[i];
                menorPosicaoVaziaConstrucao++;
            }

        RedeNeural rn = new RedeNeural();
        double[][] matrizSaida = rn.aprender(entradasParaConstrucao, saidasParaConstrucao, entradasParaTeste,
                saidasParaTeste, numeroModelo);

        int[] vetorSaidaPrevista = new int[matrizSaida.length];
        int[] vetorSaidaReal = new int[matrizSaida.length];
        for(int i = 0; i < matrizSaida.length; i++) {
            vetorSaidaPrevista[i] = retornaSaida(matrizSaida[i]);
            vetorSaidaReal[i] = retornaSaida(saidasParaTeste [i]);
        }


        int[][] matrizConfusao = geraMatrizConfusao(vetorSaidaReal, vetorSaidaPrevista);
        double acuracia = calculaAcuracia(matrizConfusao);
        double[] precisao = calculaPresicao(matrizConfusao);
        double[] sensibilidade = calculaSensibilidade(matrizConfusao);
        double precisaoMedia = calculaPrecisaoMedia(precisao);
        double sensibilidadeMedia = calculaSensibilidadeMedia(precisao);

        System.out.println( "Acuracia " + acuracia);
        System.out.println("Precisao Media: " + precisaoMedia);
        System.out.println("Precisao");
        for(int i = 0; i < RedeNeural.qntClasses; i++) {
            System.out.print(precisao[i] + "\t");
        }

        System.out.println("\n" + "Sensiblidade Media: " + sensibilidadeMedia);
        System.out.println("\n" + "Sensibilidades:");
        for(int i = 0; i < RedeNeural.qntClasses; i++) {
            System.out.print(sensibilidade[i] + "\t");
        }

        System.out.println("\n" + "F1-Score");
        double fScore = 2 * ((precisaoMedia * sensibilidadeMedia) / (precisaoMedia + sensibilidadeMedia));
        System.out.println(fScore);

        OperadorArquivo.getInstance().escreverSaidas(vetorSaidaReal, vetorSaidaPrevista, acuracia, precisaoMedia, sensibilidadeMedia, fScore);
    }

    @SuppressWarnings("Duplicates")
    private double calculaPrecisaoMedia(double[] precisao) {
        double somaPrecisoes = 0;
        for(int i = 0; i < RedeNeural.qntClasses; i++)
            somaPrecisoes += precisao[i];
        return somaPrecisoes / RedeNeural.qntClasses;

    }

    @SuppressWarnings("Duplicates")
    private double calculaSensibilidadeMedia(double[] precisao) {
        double somaSensibilidade = 0;
        for(int i = 0; i < RedeNeural.qntClasses; i++)
            somaSensibilidade += precisao[i];
        return somaSensibilidade / RedeNeural.qntClasses;
    }

    @SuppressWarnings("Duplicates")
    private double[] calculaSensibilidade(int[][] matrizConfusao) {
        int[] vetorFalsoNegativo = new int[RedeNeural.qntClasses];
        double[] vetorVerdadeiroPositivo = new double[RedeNeural.qntClasses];
        for(int i = 0; i < RedeNeural.qntClasses; i++)
            for(int j = 0; j < RedeNeural.qntClasses; j++)
                if(i == j)
                    vetorVerdadeiroPositivo[i] = matrizConfusao[i][j];
                else
                    vetorFalsoNegativo[i] += matrizConfusao[i][j];

        double[] sensibilidades = new double[RedeNeural.qntClasses];
        for(int i = 0; i < RedeNeural.qntClasses; i++)
            sensibilidades[i] = vetorVerdadeiroPositivo[i] / (vetorVerdadeiroPositivo[i] + vetorFalsoNegativo[i]);
        return sensibilidades;
    }

    @SuppressWarnings("Duplicates")
    private double[] calculaPresicao(int[][] matrizConfusao) {
        matrizConfusao = OperacaoComMatriz.calculaMatrizTransposta(matrizConfusao);
        int[] vetorFalsoPositivo = new int[RedeNeural.qntClasses];
        double[] vetorVerdadeiroPositivo = new double[RedeNeural.qntClasses];
        for(int i = 0; i < RedeNeural.qntClasses; i++)
            for(int j = 0; j < RedeNeural.qntClasses; j++)
                if(i == j)
                    vetorVerdadeiroPositivo[i] = matrizConfusao[i][j];
                else
                    vetorFalsoPositivo[i] += matrizConfusao[i][j];

        double[] precisoes = new double[RedeNeural.qntClasses];
        for(int i = 0; i < RedeNeural.qntClasses; i++)
            precisoes[i] = vetorVerdadeiroPositivo[i] / (vetorVerdadeiroPositivo[i] + vetorFalsoPositivo[i]);
        return precisoes;
    }

    private double calculaAcuracia(int[][] matrizConfusao) {
        int vp = calculaVerdadeiroPositivo(matrizConfusao);
        double tamanhoFold = this.tamanhoFold;
        return vp / tamanhoFold;
    }

    private int calculaVerdadeiroPositivo(int[][] matrizConfusao) {
        int vp = 0;
        for(int i = 0; i < RedeNeural.qntClasses; i++)
            vp += matrizConfusao[i][i];
        return vp;
    }

    private int[][] geraMatrizConfusao(int[] vetorSaidaReal, int[] vetorSaidaPrevista) {
        int[][] matrizConfusao = new int[RedeNeural.qntClasses][RedeNeural.qntClasses];
        for(int i = 0; i < tamanhoFold; i++)
            matrizConfusao[vetorSaidaPrevista[i]][vetorSaidaReal[i]]++;
        return matrizConfusao;
    }

    private int retornaSaida(double[] saida) {
        int numeroAtual = 0;
        double maiorPorcentagem = 0;
        for(int i = 0; i < saida.length; i++)
            if(saida[i] > maiorPorcentagem) {
                maiorPorcentagem = saida[i];
                numeroAtual = i;
            }
        return numeroAtual;
    }
}
