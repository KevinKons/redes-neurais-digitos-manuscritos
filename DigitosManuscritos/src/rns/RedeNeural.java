package rns;

import model.Resultado;
import org.encog.Encog;
import org.encog.engine.network.activation.ActivationSigmoid;
import org.encog.ml.data.MLData;
import org.encog.ml.data.MLDataSet;
import org.encog.ml.data.basic.BasicMLData;
import org.encog.ml.data.basic.BasicMLDataSet;
import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.layers.BasicLayer;
import org.encog.neural.networks.training.propagation.back.Backpropagation;
import util.OperadorArquivo;

public class RedeNeural {

    public static final int qntClasses = 10;
    public static final int qntPixels = 256;
    public static final int qntExemplos = 1593;

    @SuppressWarnings("Duplicates")
    public double[][] aprender(double[][] entradasParaConstrucao, double[][] saidasParaConstrucao,
                               double[][] entradasParaTeste, double[][] saidasParaTeste,
                               int numeroModelo) {

        BasicNetwork network = new BasicNetwork();
        network.addLayer(new BasicLayer(null, true, qntPixels));
        network.addLayer(new BasicLayer(new ActivationSigmoid(), true, 60));
        network.addLayer(new BasicLayer(new ActivationSigmoid(), true, 50));
        network.addLayer(new BasicLayer(new ActivationSigmoid(), true, 50));
        network.addLayer(new BasicLayer(new ActivationSigmoid(), true, 80));
        network.addLayer(new BasicLayer(new ActivationSigmoid(), false, qntClasses));

        network.getStructure().finalizeStructure();
        network.reset();

        MLDataSet dadosTreinamento = new BasicMLDataSet(entradasParaConstrucao, saidasParaConstrucao);
        final Backpropagation treinamento = new Backpropagation(network, dadosTreinamento);
        System.out.println("Treinando a RN...");
        int contadorEpocas = 1;
        String epocaError = "";

        do {
            treinamento.iteration();
//            System.out.println("Época #" + contadorEpocas + " Erro:" + treinamento.getError());
            contadorEpocas++;
            epocaError = "Época #" + contadorEpocas + " Erro:" + treinamento.getError();
        } while (treinamento.getError() > 0.01);

        treinamento.finishTraining();

        String entradaSaida = "";
        System.out.println("Testando a RN com as entradas...");

        double[][] saidaDaRede = new double[saidasParaTeste.length][saidasParaTeste[0].length];
        for (int i = 0; i < entradasParaTeste.length; i++) {
            MLData caso1Entrada = new BasicMLData(entradasParaTeste[i]);
            MLData saidaCaso1 = network.compute(caso1Entrada);
//            System.out.println("Entrada caso" + i + ": " + caso1Entrada + " --> Saída: " + saidaCaso1);
            entradaSaida = "Entrada caso" + i + ": " + caso1Entrada + " --> Saída: " + saidaCaso1 + "\n";
            saidaDaRede[i] = saidaCaso1.getData();
        }
        Resultado.getInstance().setNetwork(network);
        Encog.getInstance().shutdown();
        OperadorArquivo.getInstance().escreverSaida(epocaError, entradaSaida, numeroModelo);
        return saidaDaRede;
    }

}
