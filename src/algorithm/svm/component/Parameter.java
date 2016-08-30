package algorithm.svm.component;

/**
 * Created by Muhammad Syafiq on 8/18/2016.
 * Email : Syafiq.rezpector@gmail.com
 * GitHub : Syafiqq
 */
public class Parameter
{
    private final int    parameterSize;
    private final int    classTotal;
    private final int    multiplierIteration;
    private       double augmentingFactor;
    private       double learningRate;
    private       double constantCost;

    public Parameter(double augmentingFactor, double learningRate, double constantCost, int parameterSize, int classTotal, int multiplierIteration)
    {
        this.updateParameter(augmentingFactor, learningRate, constantCost);
        this.parameterSize = parameterSize;
        this.classTotal = classTotal;
        this.multiplierIteration = multiplierIteration;
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

    public int getParameterSize()
    {
        return parameterSize;
    }

    public int getClassTotal()
    {
        return classTotal;
    }

    public int getMultiplierIteration()
    {
        return this.multiplierIteration;
    }

    public void updateParameter(double augmentingFactor, double learningRate, double constantCost)
    {
        this.augmentingFactor = augmentingFactor;
        this.learningRate = learningRate;
        this.constantCost = constantCost;
    }

    @Override public String toString()
    {
        return "Parameter{" +
                "augmentingFactor=" + augmentingFactor +
                ", learningRate=" + learningRate +
                ", constantCost=" + constantCost +
                ", parameterSize=" + parameterSize +
                ", classTotal=" + classTotal +
                '}';
    }
}
