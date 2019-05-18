package rns;

import org.encog.engine.network.activation.ActivationTANH;
import org.encog.ml.data.MLDataSet;
import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.layers.BasicLayer;

public class RedeNeural {

    public void aprender(int entrada[][], int saida[][]){

        BasicNetwork network = new BasicNetwork();
        network.addLayer(new BasicLayer(null,true,2));
        network.addLayer(new BasicLayer(new ActivationTANH(),true,3));
        network.addLayer(new BasicLayer(new ActivationTANH(),false,1));
        network.getStructure().finalizeStructure();
        network.reset();
//        MLDataSet dadosTreinamento = new BasicMLDataSet()

    }


}
