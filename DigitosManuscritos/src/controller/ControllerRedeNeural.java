package controller;

import model.Resultado;
import org.encog.ml.data.MLData;
import org.encog.ml.data.basic.BasicMLData;
import rns.RedeNeural;
import util.OperacaoComMatriz;
import util.OperadorArquivo;

import java.util.ArrayList;
import java.util.List;

public class ControllerRedeNeural {

    private int tamanhoFold;

    private List<double[]> entradas0 = new ArrayList<>();
    private List<double[]> saidas0 = new ArrayList<>();
    private List<double[]> entradas1 = new ArrayList<>();
    private List<double[]> saidas1 = new ArrayList<>();
    private List<double[]> entradas2 = new ArrayList<>();
    private List<double[]> saidas2 = new ArrayList<>();
    private List<double[]> entradas3 = new ArrayList<>();
    private List<double[]> saidas3 = new ArrayList<>();
    private List<double[]> entradas4 = new ArrayList<>();
    private List<double[]> saidas4 = new ArrayList<>();
    private List<double[]> entradas5 = new ArrayList<>();
    private List<double[]> saidas5 = new ArrayList<>();
    private List<double[]> entradas6 = new ArrayList<>();
    private List<double[]> saidas6 = new ArrayList<>();
    private List<double[]> entradas7 = new ArrayList<>();
    private List<double[]> saidas7 = new ArrayList<>();
    private List<double[]> entradas8 = new ArrayList<>();
    private List<double[]> saidas8 = new ArrayList<>();
    private List<double[]> entradas9 = new ArrayList<>();
    private List<double[]> saidas9 = new ArrayList<>();

    private int qnt0 = 0;
    private int qnt1 = 0;
    private int qnt2 = 0;
    private int qnt3 = 0;
    private int qnt4 = 0;
    private int qnt5 = 0;
    private int qnt6 = 0;
    private int qnt7 = 0;
    private int qnt8 = 0;
    private int qnt9 = 0;

    @SuppressWarnings("Duplicates")
    public void montarEstruturaCrossValidation(int qntFolds) {
        double[][] entradas = new double[RedeNeural.qntExemplos][RedeNeural.qntPixels];
        double[][] saidas = new double[RedeNeural.qntExemplos][10];

        OperadorArquivo operadorArquivo = OperadorArquivo.getInstance();
        operadorArquivo.lerDataset(entradas, saidas, "semeion.data");

        for(int i = 0; i < RedeNeural.qntExemplos; i++) {
            int saidaEntrada = retornaSaida(saidas[i]);
            switch (saidaEntrada) {
                case 0:
                    entradas0.add(entradas[i]);
                    saidas0.add(saidas[i]);
                    qnt0++;
                    break;
                case 1:
                    entradas1.add(entradas[i]);
                    saidas1.add(saidas[i]);
                    qnt1++;
                    break;
                case 2:
                    entradas2.add(entradas[i]);
                    saidas2.add(saidas[i]);
                    qnt2++;
                    break;
                case 3:
                    entradas3.add(entradas[i]);
                    saidas3.add(saidas[i]);
                    qnt3++;
                    break;
                case 4:
                    entradas4.add(entradas[i]);
                    saidas4.add(saidas[i]);
                    qnt4++;
                    break;
                case 5:
                    entradas5.add(entradas[i]);
                    saidas5.add(saidas[i]);
                    qnt5++;
                    break;
                case 6:
                    entradas6.add(entradas[i]);
                    saidas6.add(saidas[i]);
                    qnt6++;
                    break;
                case 7:
                    entradas7.add(entradas[i]);
                    saidas7.add(saidas[i]);
                    qnt7++;
                    break;
                case 8:
                    entradas8.add(entradas[i]);
                    saidas8.add(saidas[i]);
                    qnt8++;
                    break;
                case 9:
                    entradas9.add(entradas[i]);
                    saidas9.add(saidas[i]);
                    qnt9++;
                    break;
            }
        }

        tamanhoFold = (qnt0 / qntFolds) + (qnt1 / qntFolds) +
                (qnt2 / qntFolds) + (qnt3 / qntFolds) +
                (qnt4 / qntFolds) + (qnt5 / qntFolds) +
                (qnt6 / qntFolds) + (qnt7 / qntFolds) +
                (qnt8 / qntFolds) + (qnt9 / qntFolds);
        List<double[][]> subModelosEntradas = new ArrayList<>();
        List<double[][]> subModelosSaidas = new ArrayList<>();
        for (int i = 0; i < qntFolds; i++) {
            double[][] entradasSubModelo = new double[tamanhoFold][RedeNeural.qntPixels];
            double[][] saidasSubModelo = new double[tamanhoFold][10];
            int primeiraPosVazia = 0;

            for(int k = 0; k < (qnt0 / qntFolds); k++) {
                entradasSubModelo[primeiraPosVazia] = entradas0.remove(0);
                saidasSubModelo[primeiraPosVazia] = saidas0.remove(0);
                primeiraPosVazia++;
            }
            for(int k = 0; k < (qnt1 / qntFolds); k++) {
                entradasSubModelo[primeiraPosVazia] = entradas1.remove(0);
                saidasSubModelo[primeiraPosVazia] = saidas1.remove(0);
                primeiraPosVazia++;
            }
            for(int k = 0; k < (qnt2 / qntFolds); k++) {
                entradasSubModelo[primeiraPosVazia] = entradas2.remove(0);
                saidasSubModelo[primeiraPosVazia] = saidas2.remove(0);
                primeiraPosVazia++;
            }
            for(int k = 0; k < (qnt3 / qntFolds); k++) {
                entradasSubModelo[primeiraPosVazia] = entradas3.remove(0);
                saidasSubModelo[primeiraPosVazia] = saidas3.remove(0);
                primeiraPosVazia++;
            }
            for(int k = 0; k < (qnt4 / qntFolds); k++) {
                entradasSubModelo[primeiraPosVazia] = entradas4.remove(0);
                saidasSubModelo[primeiraPosVazia] = saidas4.remove(0);
                primeiraPosVazia++;
            }
            for(int k = 0; k < (qnt5 / qntFolds); k++) {
                entradasSubModelo[primeiraPosVazia] = entradas5.remove(0);
                saidasSubModelo[primeiraPosVazia] = saidas5.remove(0);
                primeiraPosVazia++;
            }
            for(int k = 0; k < (qnt6 / qntFolds); k++) {
                entradasSubModelo[primeiraPosVazia] = entradas6.remove(0);
                saidasSubModelo[primeiraPosVazia] = saidas6.remove(0);
                primeiraPosVazia++;
            }
            for(int k = 0; k < (qnt7 / qntFolds); k++) {
                entradasSubModelo[primeiraPosVazia] = entradas7.remove(0);
                saidasSubModelo[primeiraPosVazia] = saidas7.remove(0);
                primeiraPosVazia++;
            }
            for(int k = 0; k < (qnt8 / qntFolds); k++) {
                entradasSubModelo[primeiraPosVazia] = entradas8.remove(0);
                saidasSubModelo[primeiraPosVazia] = saidas8.remove(0);
                primeiraPosVazia++;
            }
            for(int k = 0; k < (qnt9 / qntFolds); k++) {
                entradasSubModelo[primeiraPosVazia] = entradas9.remove(0);
                saidasSubModelo[primeiraPosVazia] = saidas9.remove(0);
                primeiraPosVazia++;
            }

            subModelosEntradas.add(entradasSubModelo);
            subModelosSaidas.add(saidasSubModelo);
        }

        double[][] entradasConstrucao;
        double[][] saidasConstrucao;
        double[][] entradasTeste;
        double[][] saidasTeste;
        for(int i = 0; i < qntFolds; i++) {
            entradasConstrucao = new double[tamanhoFold * (qntFolds - 1)][RedeNeural.qntPixels];
            saidasConstrucao = new double[tamanhoFold * (qntFolds - 1)][10];
            entradasTeste = subModelosEntradas.get(i);
            saidasTeste = subModelosSaidas.get(i);

            int primeiraPosVazia = 0;


            for(int j = 0; j < qntFolds; j++) {
                if(j != i) {
                    for(int k = 0; k < tamanhoFold; k++) {
                        entradasConstrucao[primeiraPosVazia] = subModelosEntradas.get(j)[k];
                        saidasConstrucao[primeiraPosVazia] = subModelosSaidas.get(j)[k];
                        primeiraPosVazia++;
                    }
                }
            }
            treinarApartirDeModelo(entradasConstrucao, saidasConstrucao, entradasTeste, saidasTeste, i);
        }
    }

    private void treinarApartirDeModelo(double[][] entradasConstrucao, double[][] saidasConstrucao,
                                        double[][] entradasTeste, double[][] saidasTeste, int numeroModelo) {
        RedeNeural rn = new RedeNeural();
        double[][] matrizSaida = rn.aprender(entradasConstrucao, saidasConstrucao, entradasTeste,
                saidasTeste, numeroModelo);

        int[] vetorSaidaPrevista = new int[matrizSaida.length];
        int[] vetorSaidaReal = new int[matrizSaida.length];
        for (int i = 0; i < matrizSaida.length; i++) {
            vetorSaidaPrevista[i] = retornaSaida(matrizSaida[i]);
            vetorSaidaReal[i] = retornaSaida(saidasTeste[i]);
        }

        int[][] matrizConfusao = geraMatrizConfusao(vetorSaidaReal, vetorSaidaPrevista);
        double acuracia = calculaAcuracia(matrizConfusao);
        double[] precisao = calculaPresicao(matrizConfusao);
        double[] sensibilidade = calculaSensibilidade(matrizConfusao);
        double precisaoMedia = calculaPrecisaoMedia(precisao);
        double sensibilidadeMedia = calculaSensibilidadeMedia(sensibilidade);

        System.out.println("Acuracia " + acuracia);
        System.out.println("Precisao Media: " + precisaoMedia);
        System.out.println("Precisao");
        for (int i = 0; i < RedeNeural.qntClasses; i++) {
            System.out.print(precisao[i] + "\t");
        }

        System.out.println("\n" + "Sensiblidade Media: " + sensibilidadeMedia);
        System.out.println("\n" + "Sensibilidades:");
        for (int i = 0; i < RedeNeural.qntClasses; i++) {
            System.out.print(sensibilidade[i] + "\t");
        }

        System.out.println("\n" + "F1-Score");
        double fScore = 2 * ((precisaoMedia * sensibilidadeMedia) / (precisaoMedia + sensibilidadeMedia));
        System.out.println(fScore);

        Resultado.getInstance().setAcuracia(acuracia);
        Resultado.getInstance().setPrecisaoMedia(precisaoMedia);
        Resultado.getInstance().setSensibilidadeMedia(sensibilidadeMedia);
        Resultado.getInstance().setfScore(fScore);
        Resultado.getInstance().setVetorSaida(vetorSaidaReal);

        OperadorArquivo.getInstance().escreverSaidas(vetorSaidaReal, vetorSaidaPrevista, acuracia, precisaoMedia, sensibilidadeMedia, fScore);
    }

    @SuppressWarnings("Duplicates")
    private double calculaPrecisaoMedia(double[] precisao) {
        double somaPrecisoes = 0;
        for (int i = 0; i < RedeNeural.qntClasses; i++)
            somaPrecisoes += precisao[i];
        return somaPrecisoes / RedeNeural.qntClasses;

    }

    @SuppressWarnings("Duplicates")
    private double calculaSensibilidadeMedia(double[] precisao) {
        double somaSensibilidade = 0;
        for (int i = 0; i < RedeNeural.qntClasses; i++)
            somaSensibilidade += precisao[i];
        return somaSensibilidade / RedeNeural.qntClasses;
    }

    @SuppressWarnings("Duplicates")
    private double[] calculaSensibilidade(int[][] matrizConfusao) {
        int[] vetorFalsoNegativo = new int[RedeNeural.qntClasses];
        double[] vetorVerdadeiroPositivo = new double[RedeNeural.qntClasses];
        for (int i = 0; i < RedeNeural.qntClasses; i++)
            for (int j = 0; j < RedeNeural.qntClasses; j++)
                if (i == j)
                    vetorVerdadeiroPositivo[i] = matrizConfusao[i][j];
                else
                    vetorFalsoNegativo[i] += matrizConfusao[i][j];

        double[] sensibilidades = new double[RedeNeural.qntClasses];
        for (int i = 0; i < RedeNeural.qntClasses; i++)
            sensibilidades[i] = vetorVerdadeiroPositivo[i] / (vetorVerdadeiroPositivo[i] + vetorFalsoNegativo[i]);
        return sensibilidades;
    }

    @SuppressWarnings("Duplicates")
    private double[] calculaPresicao(int[][] matrizConfusao) {
        matrizConfusao = OperacaoComMatriz.calculaMatrizTransposta(matrizConfusao);
        int[] vetorFalsoPositivo = new int[RedeNeural.qntClasses];
        double[] vetorVerdadeiroPositivo = new double[RedeNeural.qntClasses];
        for (int i = 0; i < RedeNeural.qntClasses; i++)
            for (int j = 0; j < RedeNeural.qntClasses; j++)
                if (i == j)
                    vetorVerdadeiroPositivo[i] = matrizConfusao[i][j];
                else
                    vetorFalsoPositivo[i] += matrizConfusao[i][j];

        double[] precisoes = new double[RedeNeural.qntClasses];
        for (int i = 0; i < RedeNeural.qntClasses; i++)
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
        for (int i = 0; i < RedeNeural.qntClasses; i++)
            vp += matrizConfusao[i][i];
        return vp;
    }

    private int[][] geraMatrizConfusao(int[] vetorSaidaReal, int[] vetorSaidaPrevista) {
        int[][] matrizConfusao = new int[RedeNeural.qntClasses][RedeNeural.qntClasses];
        for (int i = 0; i < tamanhoFold; i++)
            matrizConfusao[vetorSaidaPrevista[i]][vetorSaidaReal[i]]++;
        return matrizConfusao;
    }

    private int retornaSaida(double[] saida) {
        int numeroAtual = 0;
        double maiorPorcentagem = 0;
        for (int i = 0; i < saida.length; i++)
            if (saida[i] > maiorPorcentagem) {
                maiorPorcentagem = saida[i];
                numeroAtual = i;
            }
        return numeroAtual;
    }

    public double[] converterBooleanToDouble(boolean[][] matriz) {
        double[] arrayConvertido = new double[256];

        int cont = 0;
        for (int i = 0; i < matriz.length; i++) {
            for (int k = 0; k < matriz[0].length; k++) {
                if (!matriz[i][k])
                    arrayConvertido[cont] = 0.0;
                else
                    arrayConvertido[cont] = 1.0;
                cont++;
            }
        }
        return arrayConvertido;
    }

    public String calcSaida(boolean[][] matriz) {
        double[] entrada = converterBooleanToDouble(matriz);
        int saida = 0;
        MLData caso1Entrada = new BasicMLData(entrada);
        MLData saidaCaso1 = Resultado.getInstance().getNetwork().compute(caso1Entrada);
        saida = retornaSaida(saidaCaso1.getData());
        return String.valueOf(saida);
    }

}
