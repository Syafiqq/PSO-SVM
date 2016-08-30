package algorithm.pso.component;/*
 * This <PSO-SVM_001> project in package <algorithm.pso.component> created by : 
 * Name         : syafiq
 * Date / Time  : 26 August 2016, 8:16 AM.
 * Email        : syafiq.rezpector@gmail.com
 * Github       : syafiqq
 */

public class Setting
{
    private static Setting ourInstance = new Setting();
    private double localConfidence;
    private double swarmConfidence;
    private double constrictionFactor;
    private int    swarmSize;
    private int    svmLearningCount;
    private int    psoIteration;

    private Setting()
    {
    }

    public static Setting getInstance()
    {
        return ourInstance;
    }

    public double getConstrictionFactor()
    {
        return constrictionFactor;
    }

    private void setConstrictionFactor(double constrictionFactor)
    {
        this.constrictionFactor = constrictionFactor;
    }

    private void calculateConstrictionFactor()
    {
        double constants = this.getLocalConfidence() + this.getSwarmConfidence();
        this.setConstrictionFactor(2 / Math.abs(2 - constants - Math.sqrt(Math.pow(constants, 2) - (4 * constants))));
    }

    public double getLocalConfidence()
    {
        return localConfidence;
    }

    public void setLocalConfidence(double localConfidence)
    {
        this.localConfidence = localConfidence;
        this.calculateConstrictionFactor();
    }

    public double getSwarmConfidence()
    {
        return swarmConfidence;
    }

    public void setSwarmConfidence(double swarmConfidence)
    {
        this.swarmConfidence = swarmConfidence;
        this.calculateConstrictionFactor();
    }

    public int getSwarmSize()
    {
        return this.swarmSize;
    }

    public void setSwarmSize(int swarmSize)
    {
        this.swarmSize = swarmSize;
    }

    public int getSVMLearningCount()
    {
        return this.svmLearningCount;
    }

    public void setSVMLearningCount(int svmLearningCount)
    {
        this.svmLearningCount = svmLearningCount;
    }

    public int getPSOIteration()
    {
        return psoIteration;
    }

    public void setPsoIteration(int psoIteration)
    {
        this.psoIteration = psoIteration;
    }
}
