package rns;

import org.encog.engine.network.activation.ActivationTANH;
import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.layers.BasicLayer;

public class RedeNeural {

    public void aprender(){

    BasicNetwork network = new BasicNetwork();
    network.addLayer(new BasicLayer(null,true,2));

    network.addLayer(new BasicLayer(new ActivationTANH(),true,3)); // camada oculta

    network.addLayer(new BasicLayer(new ActivationTANH(),false,1)); // camada de sa�da

    network.getStructure().finalizeStructure(); // finaliza a estrutura da rede

    }


}
