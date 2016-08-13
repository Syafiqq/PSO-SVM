package algorithm.svm;

import dataset.component.stroke.StrokeData;
import dataset.component.stroke.StrokeParameter;

/**
 * Created by Muhammad Syafiq on 8/13/2016.
 * Email : Syafiq.rezpector@gmail.com
 * GitHub : Syafiqq
 */
public class SVM
{
    private final double augmentingFactor;
    private final double learningRate;
    private final double constantCost;
    private final StrokeData[] training;
    private final StrokeData[] testing;
    private final StrokeParameter variance;

    public SVM(double augmentingFactor, double learningRate, double constantCost, StrokeData[] training, StrokeData[] testing)
    {
        this.augmentingFactor = augmentingFactor;
        this.learningRate = learningRate;
        this.constantCost = constantCost;
        this.training = training;
        this.testing = testing;
        this.variance = new StrokeParameter(0, 0, 0, 0, 0);
    }

    public double getAugmentingFactor()
    {
        return augmentingFactor;
    }

    public double getLearningRate()
    {
        return learningRate;
    }

    public double getConstantCost()
    {
        return constantCost;
    }

    public StrokeData[] getTraining()
    {
        return training;
    }

    public StrokeData[] getTesting()
    {
        return testing;
    }
}
