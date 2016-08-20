package algorithm.svm.test;

import algorithm.svm.Parameter;
import org.junit.Test;

/**
 * Created by Muhammad Syafiq on 8/18/2016.
 * Email : Syafiq.rezpector@gmail.com
 * GitHub : Syafiqq
 */
public class ParameterTest
{

    @Test public void testAugmentingFactor()
    {
        final Parameter parameter = new Parameter(0.1, 0.2, 1000, -1, -1, 3);
        System.out.println(parameter.getAugmentingFactor());
    }

    @Test public void testLearingRate()
    {
        final Parameter parameter = new Parameter(0.1, 0.2, 1000, -1, -1, 3);
        System.out.println(parameter.getLearningRate());
    }

    @Test public void testConstantCost()
    {
        final Parameter parameter = new Parameter(0.1, 0.2, 1000, -1, -1, 3);
        System.out.println(parameter.getConstantCost());
    }
}