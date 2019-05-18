import org.encog.Encog;
import org.encog.engine.network.activation.ActivationTANH;
import org.encog.ml.data.MLData;
import org.encog.ml.data.MLDataSet;
import org.encog.ml.data.basic.BasicMLData;
import org.encog.ml.data.basic.BasicMLDataSet;
import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.layers.BasicLayer;
import org.encog.neural.networks.training.propagation.back.Backpropagation;


/**
 * Exemplo de RN para resolver o XOR.
 * Esta implementa��o foi adaptada do original em <http://www.heatonresearch.com/wiki/Hello_World>.
 *
 * O problema, os dados de entrada e sa�da est�o dispon�veis nos slides da disciplina.
 *
 * @author Fernando dos Santos (fernando.santos@udesc.br)
 */
public class ExemploXOR {

    /**
     * As entradas caso1, caso2, caso3, caso4.
     */
    private static double caso1[] = { -1 , -1 };
    private static double caso2[] = { -1 , +1 };
    private static double caso3[] = { +1 , -1 };
    private static double caso4[] = { +1 , +1 };
    private static double casos_todos[][] = { caso1, caso2, caso3, caso4 };

    /**
     * Os valores de sa�da d1, d2, d3, d4, para cada caso de entrada.
     */
    private static double d1[] = {-1};
    private static double d2[] = {+1};
    private static double d3[] = {+1};
    private static double d4[] = {-1};
    public static double d_todos[][] = { d1, d2, d3, d4 };

    /**
     * No m�todo main ser� criada e treinada a RN.
     */
    @SuppressWarnings("Duplicates")
    public static void main(final String args[]) {

        // Cria��o da RN Multilayer Perceptron (� do tipo BasicNetwork)
        BasicNetwork network = new BasicNetwork();

        /* Adi��o das camadas.
         * A 1a camada adicionada � a camada de entrada.
         * A �ltima camada adicionada � a de sa�da.
         * Todas as camadas adicionadas entre a 1a e a �ltima s�o camadas ocultas.
         *
         * A classe BasicLayer possui os seguintes par�metros
         * 	 fun��o de ativa��o
         * 		  -> tangente hiperb�lica = ActivationTAHN
         * 		  -> log�stica = ActivationSigmoid
         *   se a camada possui vi�s (bias), ou seja, a entrada/peso x0/w0
         *   a quantidade de neur�nios da camada
         */
        network.addLayer(new BasicLayer(null,true,2)); // camada de entrada

        network.addLayer(new BasicLayer(new ActivationTANH(),true,3)); // camada oculta

        network.addLayer(new BasicLayer(new ActivationTANH(),false,1)); // camada de sa�da

        network.getStructure().finalizeStructure(); // finaliza a estrutura da rede

        network.reset(); // reinicia a rede, para posterior treinamento

        /* Cria��o de um conjunto com os dados de treinamento -> MLDataSet.
         * Neste conjunto deve ter as entradas x e os valores desejados d.
         */
        MLDataSet dadosTreinamento = new BasicMLDataSet(casos_todos, d_todos);

        // Treinamento da RN
        // � necess�rio instanciar o algoritmo de treinamento backpropagation
        final Backpropagation treinamento = new Backpropagation(network, dadosTreinamento);

        // La�o para treinar a rede at� que o erro m�dio quadrado seja menor que um determinado valor.
        System.out.println("Treinando a RN...");
        int contadorEpocas = 1; // contador de �pocas.
        do {
            treinamento.iteration(); // faz 1 itera��o do backpropagation
            System.out.println("�poca #" + contadorEpocas + " Erro:" + treinamento.getError());
            contadorEpocas++;
        } while(treinamento.getError() > 0.01);

        treinamento.finishTraining(); // finaliza o treinamento

        // Teste da RN treinada usando as entradas
        System.out.println("Testando a RN com as entradas...");

        MLData caso1Entrada = new BasicMLData(caso1);
        MLData saidaCaso1 = network.compute(caso1Entrada);
        System.out.println("Entrada caso1: "+caso1Entrada+ " --> Sa�da x1: "+saidaCaso1);

        MLData caso2Entrada = new BasicMLData(caso2);
        MLData saidaCaso2 = network.compute(caso2Entrada);
        System.out.println("Entrada caso2: "+caso2Entrada+ " --> Sa�da x2: "+saidaCaso2);

        MLData caso3Entrada = new BasicMLData(caso3);
        MLData saidaCaso3 = network.compute(caso3Entrada);
        System.out.println("Entrada caso3: "+caso3Entrada+ " --> Sa�da x3: "+saidaCaso3);

        MLData caso4Entrada = new BasicMLData(caso4);
        MLData saidaCaso4 = network.compute(caso4Entrada);
        System.out.println("Entrada caso4: "+caso4Entrada+ " --> Sa�da x4: "+saidaCaso4);


        // Interrompe a API encog
        Encog.getInstance().shutdown();
    }
}