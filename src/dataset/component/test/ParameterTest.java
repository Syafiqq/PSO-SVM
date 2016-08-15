package dataset.component.test;

import dataset.component.Parameter;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Muhammad Syafiq on 8/15/2016.
 * Email : Syafiq.rezpector@gmail.com
 * GitHub : Syafiqq
 */
public class ParameterTest
{
    @Test public void testStatusCholesterol01()
    {
        final Parameter parameter = new Parameter("Cholesterol");
        Assert.assertEquals("Name must be Cholesterol", "Cholesterol", parameter.getName());
        Assert.assertEquals("Object Display must Be Parameter{name='Cholesterol'}", "Parameter{name='Cholesterol'}", parameter.toString());
    }
}