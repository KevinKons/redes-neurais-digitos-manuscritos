package model;

import org.encog.neural.networks.BasicNetwork;
import rns.RedeNeural;

public class Resultado {

    private static Resultado instance;
    private double acuracia;
    private double precisaoMedia;
    private double sensibilidadeMedia;
    private double fScore;
    private int[] vetorSaida;
    private BasicNetwork network;


    private Resultado(){}

    public static Resultado getInstance(){
        if(instance == null)
            instance = new Resultado();

        return instance;
    }

    public double getAcuracia() {
        return acuracia;
    }

    public void setAcuracia(double acuracia) {
        this.acuracia = acuracia;
    }

    public static void setInstance(Resultado instance) {
        Resultado.instance = instance;
    }

    public double getPrecisaoMedia() {
        return precisaoMedia;
    }

    public void setPrecisaoMedia(double precisaoMedia) {
        this.precisaoMedia = precisaoMedia;
    }

    public double getSensibilidadeMedia() {
        return sensibilidadeMedia;
    }

    public void setSensibilidadeMedia(double sensibilidadeMedia) {
        this.sensibilidadeMedia = sensibilidadeMedia;
    }

    public double getfScore() {
        return fScore;
    }

    public void setfScore(double fScore) {
        this.fScore = fScore;
    }

    public int[] getVetorSaida() {
        return vetorSaida;
    }

    public void setVetorSaida(int[] vetorSaida) {
        this.vetorSaida = vetorSaida;
    }

    public BasicNetwork getNetwork() {
        return network;
    }

    public void setNetwork(BasicNetwork network) {
        this.network = network;
    }
}
