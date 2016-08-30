package algorithm.pso.component;/*
 * This <PSO-SVM_001> project in package <algorithm.pso.component> created by : 
 * Name         : syafiq
 * Date / Time  : 26 August 2016, 6:43 AM.
 * Email        : syafiq.rezpector@gmail.com
 * Github       : syafiqq
 */

public class Position
{
    private double augmentingFactor;
    private double learningRate;
    private double constantCost;

    public Position(double augmentingFactor, double learningRate, double constantCost)
    {
        this.augmentingFactor = augmentingFactor;
        this.learningRate = learningRate;
        this.constantCost = constantCost;
    }

    public static void replace(Position source, Position destination)
    {
        destination.setAugmentingFactor(source.augmentingFactor);
        destination.setLearningRate(source.learningRate);
        destination.setConstantCost(source.constantCost);
    }

    public double getAugmentingFactor()
    {
        return augmentingFactor;
    }

    public void setAugmentingFactor(double augmentingFactor)
    {
        this.augmentingFactor = augmentingFactor;
    }

    public double getLearningRate()
    {
        return learningRate;
    }

    public void setLearningRate(double learningRate)
    {
        this.learningRate = learningRate;
    }

    public double getConstantCost()
    {
        return constantCost;
    }

    public void setConstantCost(double constantCost)
    {
        this.constantCost = constantCost;
    }

    public void updatePosition(final Velocity velocity)
    {
        this.setAugmentingFactor(this.getAugmentingFactor() + velocity.getAugmentingFactor());
        this.setLearningRate(this.getLearningRate() + velocity.getLearningRate());
        this.setConstantCost(this.getConstantCost() + velocity.getConstantCost());
    }

    @Override public String toString()
    {
        return "Position{" +
                "augmentingFactor=" + augmentingFactor +
                ", learningRate=" + learningRate +
                ", constantCost=" + constantCost +
                '}';
    }
}
