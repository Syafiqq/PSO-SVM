package algorithm.svm;

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
    private StrokeData[] testing;

    public SVMLearner(final Parameter parameter, final StrokeData[] training, final StrokeData[] testing) throws StrokeException
    {
        super(parameter, training);
        this.updateDataTesting(testing);
    }

    public void updateDataTesting(final StrokeData[] bunchOfStrokeData) throws StrokeException
    {
        super.sanitizeBunchOfStrokeData(bunchOfStrokeData);
        this.testing = bunchOfStrokeData;
    }

    @Override public double evaluateStrokeData(StrokeData[] bunchOfStrokeData) throws StrokeException
    {
        throw new MethodNotSupportedException("Method evaluateStrokeData(param) is not Supported in SVMLearner, call evaluateStrokeData() instead.");
    }

    public double evaluateStrokeData()
    {
        return super.evaluateStrokeDataWithoutSanitizing(this.testing);
    }
}
