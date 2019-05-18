package controller;

import rns.RedeNeural;
import util.LeitorDataset;

public class Main {

    public static void main(String[] args) {
        ControllerRedeNeural controllerRedeNeural = new ControllerRedeNeural();
        controllerRedeNeural.montarEstruturaCrossValidation(5);
    }
}
