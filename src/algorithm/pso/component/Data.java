package algorithm.pso.component;/*
 * This <PSO-SVM_001> project in package <algorithm.pso.component> created by : 
 * Name         : syafiq
 * Date / Time  : 26 August 2016, 6:20 AM.
 * Email        : syafiq.rezpector@gmail.com
 * Github       : syafiqq
 */

import algorithm.svm.component.Parameter;
import algorithm.svm.core.SVMLearner;

public class Data
{
    private final Position position;
    private       double   fitness;

    public Data(final SVMLearner svm)
    {
        final Parameter parameter = svm.getParameter();
        this.fitness = 0.0;
        this.position = new Position(parameter.getAugmentingFactor(), parameter.getLearningRate(), parameter.getConstantCost());
    }

    public Data(final Parameter svmParameter)
    {
        this.fitness = 0.0;
        this.position = new Position(svmParameter.getAugmentingFactor(), svmParameter.getLearningRate(), svmParameter.getConstantCost());
    }

    public static void replace(Data source, Data destination)
    {
        Position.replace(source.position, destination.position);
        destination.setFitness(source.getFitness());
    }

    public double getFitness()
    {
        return this.fitness;
    }

    public void setFitness(double fitness)
    {
        this.fitness = fitness;
    }

    public Position getPosition()
    {
        return this.position;
    }
}
