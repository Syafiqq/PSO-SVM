package dataset.component.test;

import dataset.component.Parameter;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Muhammad Syafiq on 8/15/2016.
 * Email : Syafiq.rezpector@gmail.com
 * GitHub : Syafiqq
 */
public class ParameterTest
{

    @Test public void testStatusName01()
    {
        final Parameter parameter = new Parameter("cholesterol", "Cholesterol");
        Assert.assertEquals("Name must be Cholesterol", "Cholesterol", parameter.getName());
    }

    @Test public void testStatusKey01()
    {
        final Parameter parameter = new Parameter("cholesterol", "Cholesterol");
        Assert.assertEquals("Name must be cholesterol", "cholesterol", parameter.getKey());
    }

    @Test public void testObjectDisplay01()
    {
        final Parameter parameter = new Parameter("cholesterol", "Cholesterol");
        Assert.assertEquals("Object Display must Be Parameter{key='cholesterol', name='Cholesterol'}", "Parameter{key='cholesterol', name='Cholesterol'}", parameter.toString());
    }
}