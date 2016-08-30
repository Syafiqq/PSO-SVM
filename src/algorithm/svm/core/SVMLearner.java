package algorithm.svm.core;

import algorithm.svm.component.OneAgainstAll;
import algorithm.svm.component.Parameter;
import dataset.component.stroke.StrokeData;
import dataset.component.stroke.exception.MethodNotSupportedException;
import dataset.component.stroke.exception.StrokeException;

/**
 * Created by Muhammad Syafiq on 8/25/2016.
 * Email : Syafiq.rezpector@gmail.com
 * GitHub : Syafiqq
 */
public class SVMLearner extends SVM
{
    private final StrokeData[] testing;
    private       double[][]   kernelTesting;

    public SVMLearner(final Parameter parameter, final StrokeData[] training, final StrokeData[] testing) throws StrokeException
    {
        super(parameter, training);
        super.sanitizeStrokeData(testing);
        this.testing = testing;
        this.kernelTesting = new double[this.testing.length][];
        this.calculateKernelTesting();
    }

    private void calculateKernelTesting()
    {
        final StrokeData[] testing       = this.testing;
        final double[][]   kernelTesting = this.kernelTesting;
        for(int testingIndex = -1, testingSize = testing.length; ++testingIndex < testingSize; )
        {
            kernelTesting[testingIndex] = super.calculateKernelDataTesting(testing[testingIndex].getParameterComponent());
        }
    }

    @Override public double evaluateStrokeData(final StrokeData... strokeData) throws StrokeException
    {
        throw new MethodNotSupportedException("Method evaluateStrokeData(param) is not Supported in SVMLearner, call evaluateStrokeData() instead.");
    }

    public double evaluateStrokeData()
    {
        return this.doTestBunchOfStroke() * 100.0 / this.testing.length;
    }

    private double doTestBunchOfStroke()
    {
        final StrokeData[] testing = this.testing;
        int                total   = 0;
        for(int testingIndex = -1, testingSize = testing.length; ++testingIndex < testingSize; )
        {
            total += doClassify(testingIndex) == testing[testingIndex].getMetadata().getStatus() ? 1 : 0;
        }
        return total;
    }

    private int doClassify(final int testingIndex)
    {
        final double[] kernel = this.kernelTesting[testingIndex];
        int            clazz  = 0;
        for(final OneAgainstAll oaa : super.getOneAgainstAllLevel())
        {
            if(oaa.doClassify(kernel) == 1)
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
}
