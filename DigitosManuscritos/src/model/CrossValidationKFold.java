package model;

import org.encog.ml.MLMethod;
import org.encog.ml.data.folded.FoldedDataSet;
import org.encog.ml.train.MLTrain;
import org.encog.neural.flat.FlatNetwork;
import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.training.cross.CrossTraining;
import org.encog.neural.networks.training.cross.NetworkFold;
import org.encog.neural.networks.training.propagation.TrainingContinuation;

public class CrossValidationKFold extends CrossTraining {

    private final MLTrain train;
    private final NetworkFold[] networks;
    private final FlatNetwork flatNetwork;

    public CrossValidationKFold(final MLTrain train, final int k) {
        super(train.getMethod(), (FoldedDataSet) train.getTraining());
        this.train = train;
        getFolded().fold(k);

        this.flatNetwork = ((BasicNetwork)train.getMethod()).getStructure().getFlat();

        this.networks = new NetworkFold[k];
        for (int i = 0; i < networks.length; i++) {
            this.networks[i] = new NetworkFold(flatNetwork);
        }

    }

    @Override
    public void iteration() {

    }

    @Override
    public boolean canContinue() {
        return false;
    }

    @Override
    public TrainingContinuation pause() {
        return null;
    }

    @Override
    public void resume(TrainingContinuation trainingContinuation) {

    }
}
