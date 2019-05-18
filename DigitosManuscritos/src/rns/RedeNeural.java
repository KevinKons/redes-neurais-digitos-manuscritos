package rns;

import org.encog.Encog;
import org.encog.engine.network.activation.ActivationSigmoid;
import org.encog.engine.network.activation.ActivationTANH;
import org.encog.ml.data.MLData;
import org.encog.ml.data.MLDataSet;
import org.encog.ml.data.basic.BasicMLData;
import org.encog.ml.data.basic.BasicMLDataSet;
import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.layers.BasicLayer;
import org.encog.neural.networks.training.propagation.back.Backpropagation;

public class RedeNeural {

    @SuppressWarnings("Duplicates")
    public void aprender(double entrada[][], double saida[][]) {

        BasicNetwork network = new BasicNetwork();

        network.addLayer(new BasicLayer(null, true, 256));
        network.addLayer(new BasicLayer(new ActivationSigmoid(), true, 50));
        network.addLayer(new BasicLayer(new ActivationSigmoid(), true, 50));
        network.addLayer(new BasicLayer(new ActivationSigmoid(), true, 25));
        network.addLayer(new BasicLayer(new ActivationSigmoid(), false, 10));

        network.getStructure().finalizeStructure();
        network.reset();

        MLDataSet dadosTreinamento = new BasicMLDataSet(entrada, saida);
        final Backpropagation treinamento = new Backpropagation(network, dadosTreinamento);
        System.out.println("Treinando a RN...");
        int contadorEpocas = 1;

        do {
            treinamento.iteration();
            System.out.println("Época #" + contadorEpocas + " Erro:" + treinamento.getError());
            contadorEpocas++;
        } while (treinamento.getError() > 0.01);

        treinamento.finishTraining();
        System.out.println("Testando a RN com as entradas...");

        for (int i = 0; i < entrada[0].length; i++) {
            MLData caso1Entrada = new BasicMLData(entrada[i]);
            MLData saidaCaso1 = network.compute(caso1Entrada);
            System.out.println("Entrada caso" + i + ": " + caso1Entrada + " --> Saída: " + saidaCaso1);
        }

        Encog.getInstance().shutdown();

    }


}
