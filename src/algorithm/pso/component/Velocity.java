package algorithm.pso.component;/*
 * This <PSO-SVM_001> project in package <algorithm.pso.component> created by : 
 * Name         : syafiq
 * Date / Time  : 26 August 2016, 6:40 AM.
 * Email        : syafiq.rezpector@gmail.com
 * Github       : syafiqq
 */

public class Velocity
{
    private final Position position;

    public Velocity(double augmentingFactor, double learningRate, double constantCost)
    {
        this.position = new Position(augmentingFactor, learningRate, constantCost);
    }

    public double getAugmentingFactor()
    {
        return this.position.getAugmentingFactor();
    }

    public void setAugmentingFactor(double augmentingFactor)
    {
        this.position.setAugmentingFactor(augmentingFactor);
    }

    public double getLearningRate()
    {
        return this.position.getLearningRate();
    }

    public void setLearningRate(double learningRate)
    {
        this.position.setLearningRate(learningRate);
    }

    public double getConstantCost()
    {
        return this.position.getConstantCost();
    }

    public void setConstantCost(double constantCost)
    {
        this.position.setConstantCost(constantCost);
    }

    public void measureDistance(Position num, Position sub)
    {
        this.setAugmentingFactor(num.getAugmentingFactor() - sub.getAugmentingFactor());
        this.setLearningRate(num.getLearningRate() - sub.getLearningRate());
        this.setConstantCost(num.getConstantCost() - sub.getConstantCost());
    }

    public void multiplicate(double factor)
    {
        this.setAugmentingFactor(this.getAugmentingFactor() * factor);
        this.setLearningRate(this.getLearningRate() * factor);
        this.setConstantCost(this.getConstantCost() * factor);
    }

    public void addition(Velocity adder)
    {
        this.setAugmentingFactor(this.getAugmentingFactor() + adder.getAugmentingFactor());
        this.setLearningRate(this.getLearningRate() + adder.getLearningRate());
        this.setConstantCost(this.getConstantCost() + adder.getConstantCost());
    }
}
