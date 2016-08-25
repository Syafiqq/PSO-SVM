package algorithm.svm;

import dataset.component.stroke.StrokeData;
import dataset.component.stroke.StrokeParameter;
import dataset.component.stroke.exception.NoSuchStrokeStatusException;
import dataset.component.stroke.exception.StrokeException;
import org.apache.commons.math3.stat.StatUtils;
import org.apache.commons.math3.util.FastMath;

import java.util.ArrayList;
import java.util.ListIterator;

/**
 * Created by Muhammad Syafiq on 8/13/2016.
 * Email : Syafiq.rezpector@gmail.com
 * GitHub : Syafiqq
 */
public class SVM
{
    private final Parameter parameter;
    private final StrokeData[] training;
    private final OneAgainstAll[] oneAgainstAlls;
    private final double[] variance;
    private final double[][] kernel;
    private final double[] kernelTesting;

    public SVM(final Parameter parameter, StrokeData[] training) throws StrokeException
    {
        this.parameter = parameter;
        this.sanitizeBunchOfStrokeData(training);
        this.training = training;
        this.variance = new double[this.parameter.getParameterSize()];
        this.kernel = new double[this.training.length][this.training.length];
        this.oneAgainstAlls = new OneAgainstAll[this.parameter.getClassTotal() - 1];
        this.kernelTesting = new double[this.training.length];
        this.initializeSVM();
    }

    private void initializeSVM()
    {
        this.calculateVariance();
        this.calculateKernelBetweenData();
        this.generateOneAgainstAll();
    }

    public void doSequentialLearning()
    {
        this.calculateMatrixD();
        this.learnMultiplier();
        this.calculateBias();
    }

    public int testStrokeData(final StrokeData strokeData) throws StrokeException
    {
        return this.doTestWithSanitizing(strokeData);
    }

    public double evaluateStrokeData(final StrokeData[] bunchOfStrokeData) throws StrokeException
    {
        return this.evaluateStrokeDataWithSanitizing(bunchOfStrokeData);
    }

    protected double evaluateStrokeDataWithSanitizing(StrokeData[] bunchOfStrokeData) throws StrokeException
    {
        this.sanitizeBunchOfStrokeData(bunchOfStrokeData);
        return this.evaluateStrokeDataWithoutSanitizing(bunchOfStrokeData);
    }

    protected double evaluateStrokeDataWithoutSanitizing(final StrokeData[] bunchOfStrokeData)
    {
        return this.doTestBunchOfStrokeDataWithoutSanitizing(bunchOfStrokeData) * 100.0 / bunchOfStrokeData.length;
    }

    protected void sanitizeBunchOfStrokeData(final StrokeData[] bunchOfStrokeData) throws StrokeException
    {
        for(final StrokeData strokeData : bunchOfStrokeData)
        {
            this.sanitizeStrokeData(strokeData);
        }
    }

    public void sanitizeStrokeData(StrokeData stroke) throws StrokeException
    {
        final int strokeStatus = stroke.getMetadata().getStatus();
        if((strokeStatus < -1) || (strokeStatus > this.parameter.getClassTotal()))
        {
            throw new NoSuchStrokeStatusException(String.format("Stroke Status [%d] is not registered", strokeStatus));
        }
    }

    protected int doTestBunchOfStrokeDataWithoutSanitizing(final StrokeData[] bunchOfStrokeData)
    {
        int total = 0;
        for(final StrokeData stroke : bunchOfStrokeData)
        {
            total += doTestWithoutSanitizing(stroke);
        }
        return total;
    }

    protected int doTestWithSanitizing(final StrokeData strokeData) throws StrokeException
    {
        this.sanitizeStrokeData(strokeData);
        return this.doTestWithoutSanitizing(strokeData);
    }

    protected int doTestWithoutSanitizing(final StrokeData strokeData)
    {
        return doClassify(strokeData.getParameterComponent()) == strokeData.getMetadata().getStatus() ? 1 : 0;
    }

    public int doClassify(final StrokeParameter data)
    {
        this.calculateKernelDataTesting(data);
        int clazz = 0;
        for(final OneAgainstAll oaa : this.oneAgainstAlls)
        {
            if(oaa.doClassify(this.kernelTesting) == 1)
            {
                break;
            }
            else
            {
                ++clazz;
            }
        }
        return clazz;
    }

    private void calculateKernelDataTesting(StrokeParameter data)
    {
        final double[] testing = data.getParameter();
        final double[] kernel = this.kernelTesting;
        final double[] variance = this.variance;
        for(int trainingIndex = -1, trainingSize = this.training.length, parameterSize = this.parameter.getParameterSize(); ++trainingIndex < trainingSize; )
        {
            final double[] training = this.training[trainingIndex].getParameterComponent().getParameter();
            double value = 1;
            for(int parameterIndex = -1; ++parameterIndex < parameterSize; )
            {
                value *= FastMath.exp(FastMath.pow(testing[parameterIndex] - training[parameterIndex], 2) / (-2 * variance[parameterIndex]));
            }
            kernel[trainingIndex] = value;
        }
    }

    private void calculateVariance()
    {
        final double[] variance = this.variance;
        final double[] currentCalculation = new double[this.training.length];
        for(int parameterIndex = -1, parameterSize = this.parameter.getParameterSize(), trainingSize = this.training.length; ++parameterIndex < parameterSize; )
        {
            for(int trainingIndex = -1; ++trainingIndex < trainingSize; )
            {
                currentCalculation[trainingIndex] = this.training[trainingIndex].getParameterComponent().getParameter(parameterIndex);
            }
            variance[parameterIndex] = StatUtils.variance(currentCalculation);
        }
    }

    private void calculateKernelBetweenData()
    {
        final double[] variance = this.variance;
        final double[][] kernel = this.kernel;
        for(int trainingIndexLv1 = -1, trainingSize = this.training.length, parameterSize = this.parameter.getParameterSize(); ++trainingIndexLv1 < trainingSize; )
        {
            final double[] trainingX = this.training[trainingIndexLv1].getParameterComponent().getParameter();
            for(int preTrainingIndexLv2 = -1; ++preTrainingIndexLv2 < trainingIndexLv1; )
            {
                kernel[trainingIndexLv1][preTrainingIndexLv2] = kernel[preTrainingIndexLv2][trainingIndexLv1];
            }

            kernel[trainingIndexLv1][trainingIndexLv1] = 1.0;

            for(int postTrainingIndexLv2 = trainingIndexLv1; ++postTrainingIndexLv2 < trainingSize; )
            {
                final double[] trainingY = this.training[postTrainingIndexLv2].getParameterComponent().getParameter();

                double value = 1;
                for(int parameterIndex = -1; ++parameterIndex < parameterSize; )
                {
                    value *= FastMath.exp(FastMath.pow(trainingX[parameterIndex] - trainingY[parameterIndex], 2) / (-2 * variance[parameterIndex]));
                }
                kernel[trainingIndexLv1][postTrainingIndexLv2] = value;
            }
        }
    }

    private void generateOneAgainstAll()
    {
        final StrokeData[] training = this.training;
        ArrayList<Integer> trainingNumber = new ArrayList<>(this.training.length);

        /*
        * Generate Training Index
        * */
        for(int trainingIndex = -1, trainingSize = training.length; ++trainingIndex < trainingSize; )
        {
            trainingNumber.add(trainingIndex);
        }

        /*
        * Begin generate OneAgainstAll
        * */
        for(int oaaLevel = -1, oaaSize = this.oneAgainstAlls.length; ++oaaLevel < oaaSize; )
        {
            this.oneAgainstAlls[oaaLevel] = new OneAgainstAll(this.training.length);
            this.oneAgainstAlls[oaaLevel].setAllowedData(trainingNumber.stream().mapToInt(Integer::valueOf).toArray());

            final int[] clazz = this.oneAgainstAlls[oaaLevel].getClassData();

            final ListIterator<Integer> dataNumberIt = trainingNumber.listIterator();
            while(dataNumberIt.hasNext())
            {
                final int dataIndex = dataNumberIt.next();
                if(training[dataIndex].getMetadata().getStatus() == oaaLevel)
                {
                    clazz[dataIndex] = 1;
                    dataNumberIt.remove();
                }
                else
                {
                    clazz[dataIndex] = -1;
                }
            }
        }
    }

    private void calculateMatrixD()
    {
        final double augmentingFactor = FastMath.pow(this.parameter.getAugmentingFactor(), 2);
        /*
        * Create temporary calculation
        * */
        final double[][] kernel = this.kernel;
        final double[][] tempCalculation = new double[this.training.length][this.training.length];
        for(int kernelIndexLv1 = -1, kernelSize = kernel.length; ++kernelIndexLv1 < kernelSize; )
        {
            for(int preKernelIndexLv2 = -1; ++preKernelIndexLv2 < kernelIndexLv1; )
            {
                tempCalculation[kernelIndexLv1][preKernelIndexLv2] = tempCalculation[preKernelIndexLv2][kernelIndexLv1];
            }

            for(int postKernelIndexLv2 = kernelIndexLv1 - 1; ++postKernelIndexLv2 < kernelSize; )
            {
                tempCalculation[kernelIndexLv1][postKernelIndexLv2] = (kernel[kernelIndexLv1][postKernelIndexLv2] + augmentingFactor);
            }
        }
        /*
        * Begin calculate matrixD
        * */
        for(final OneAgainstAll oaa : this.oneAgainstAlls)
        {
            oaa.calculateMatrixD(tempCalculation);
        }
    }

    private void learnMultiplier()
    {
        for(final OneAgainstAll oaa : this.oneAgainstAlls)
        {
            oaa.learnMultiplier(this.parameter.getMultiplierIteration(), this.parameter.getLearningRate(), this.parameter.getConstantCost());
        }
    }


    private void calculateBias()
    {
        for(final OneAgainstAll oaa : this.oneAgainstAlls)
        {
            int indexPlus = oaa.findGreatestMultiplierIndexByClassification(1);
            int indexMinus = oaa.findGreatestMultiplierIndexByClassification(-1);
            try
            {
                oaa.calculateBias(this.kernel[indexPlus], this.kernel[indexMinus]);
            }
            catch(ArrayIndexOutOfBoundsException ignored)
            {
                System.out.println(this.parameter);
                System.exit(1);
            }
        }
    }

    public double[][] getKernel()
    {
        return this.kernel;
    }

    public OneAgainstAll[] getOneAgainstAllLevel()
    {
        return this.oneAgainstAlls;
    }

    public StrokeData[] getTraining()
    {
        return training;
    }

    public double[] getVariance()
    {
        return variance;
    }
}
