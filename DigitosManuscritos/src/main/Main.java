package main;

import model.CrossValidationKFold;
import org.encog.engine.network.activation.ActivationSigmoid;
import org.encog.ml.data.MLDataSet;
import org.encog.ml.data.basic.BasicMLDataSet;
import org.encog.ml.train.MLTrain;
import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.layers.BasicLayer;
import util.LeitorDataset;

public class Main {
    public static void main(String[] args) {

        BasicNetwork network = new BasicNetwork();
        network.addLayer(new BasicLayer(null, true, 10));
        network.addLayer(new BasicLayer(new ActivationSigmoid(), true, 10));
        network.addLayer(new BasicLayer(new ActivationSigmoid(), false, 10));
        network.getStructure().finalizeStructure();
        network.reset();

        double entradas[][] = new double[1593][256];
        double saidas[][] = new double[1593][10];
        new LeitorDataset().lerDataset(entradas, saidas, "../dataset-digitos-manuscritos/");
        MLDataSet dadosTrainamento = new BasicMLDataSet(entradas, saidas);

        //Instanciar a CossValidationFold
        CrossValidationKFold cvf = new CrossValidationKFold((MLTrain) dadosTrainamento, 5);

    }
}
