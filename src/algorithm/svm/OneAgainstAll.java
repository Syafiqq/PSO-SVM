package algorithm.svm;

import com.sun.istack.internal.NotNull;
import org.apache.commons.math3.util.FastMath;

/**
 * Created by Muhammad Syafiq on 8/18/2016.
 * Email : Syafiq.rezpector@gmail.com
 * GitHub : Syafiqq
 */
public class OneAgainstAll
{
    private final int[] clazz;
    private final double[][] matrixD;
    private final double[] multiplier;
    private int[] allowedData;
    private double bias;

    public OneAgainstAll(int dataSize)
    {
        this.clazz = new int[dataSize];
        this.matrixD = new double[dataSize][dataSize];
        this.multiplier = new double[dataSize];
        this.allowedData = null;
        this.bias = 0.0;
    }

    public void calculateMatrixD(@NotNull final double[][] precalculatedKernel)
    {
        final int[] clazz = this.clazz;
        final double[][] matrixD = this.matrixD;
        for(final int kernelIndexLv1 : this.allowedData)
        {
            for(final int kernelIndexLv2 : this.allowedData)
            {
                matrixD[kernelIndexLv1][kernelIndexLv2] = kernelIndexLv2 < kernelIndexLv1 ? matrixD[kernelIndexLv2][kernelIndexLv1] : (clazz[kernelIndexLv1] * clazz[kernelIndexLv2] * precalculatedKernel[kernelIndexLv1][kernelIndexLv2]);
            }
        }
    }

    public int[] getClassData()
    {
        return this.clazz;
    }

    public double[][] getMatrixD()
    {
        return this.matrixD;
    }

    public int[] getAllowedData()
    {
        return allowedData;
    }

    public void setAllowedData(final int[] allowedData)
    {
        this.allowedData = allowedData;
    }

    public double getBias()
    {
        return bias;
    }

    public void setBias(double bias)
    {
        this.bias = bias;
    }

    public void learnMultiplier(int iterationMax, double learningRate, double constantCost)
    {
        for(int currentIteration = -1; ++currentIteration < iterationMax; )
        {
            for(final int dataIndex : this.allowedData)
            {
                final double multiplier = this.multiplier[dataIndex];
                double value = calculateError(multiplier, dataIndex);
                value = borderingMultiplier(value, multiplier, learningRate, constantCost);
                this.multiplier[dataIndex] += value;
            }
        }
    }

    private double calculateError(double multiplier, int multiplierIndex)
    {
        final double[] matrixD = this.matrixD[multiplierIndex];
        double value = 0;
        for(final int dataIndex : this.allowedData)
        {
            value += (multiplier * matrixD[dataIndex]);
        }
        return value;
    }

    private double borderingMultiplier(double error, double multiplier, double learningRate, double constantCost)
    {
        return FastMath.min(FastMath.max(learningRate * (1 - error), -multiplier), constantCost - multiplier);
    }

    public double[] getMultiplier()
    {
        return this.multiplier;
    }

    public int findGreatestMultiplierIndexByClassification(int classification)
    {
        final int[] clazz = this.clazz;
        final double[] multipliers = this.multiplier;
        double multiplier = Double.MIN_VALUE;
        int index = -1;
        for(int dataIndex : this.allowedData)
        {
            if((clazz[dataIndex] == classification) && (multipliers[dataIndex] > multiplier))
            {
                index = dataIndex;
                multiplier = multipliers[dataIndex];
            }
        }
        return index;
    }

    public void calculateBias(final double[] positiveSets, final double[] negativeSets)
    {
        final int[] clazz = this.clazz;
        final double[] multipliers = this.multiplier;

        double bias = 0.0;
        for(int dataIndex : this.allowedData)
        {
            bias += multipliers[dataIndex] * clazz[dataIndex] * (positiveSets[dataIndex] + negativeSets[dataIndex]);
        }
        this.bias = -0.5 * bias;
    }

    public int doClassify(final double[] kernelTesting)
    {
        final double[] multiplier = this.multiplier;
        final int[] clazz = this.clazz;
        double value = this.bias;
        for(final int dataIndex : this.allowedData)
        {
            value += (multiplier[dataIndex] * clazz[dataIndex] * kernelTesting[dataIndex]);
        }
        return (int) FastMath.signum(value);
    }
}
