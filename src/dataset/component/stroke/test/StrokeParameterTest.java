package dataset.component.stroke.test;

import dataset.component.stroke.StrokeParameter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Muhammad Syafiq on 8/13/2016.
 * Email : Syafiq.rezpector@gmail.com
 * GitHub : Syafiqq
 */
public class StrokeParameterTest
{
    private StrokeParameter parameter;

    @Before public void initialize()
    {
        this.parameter = new StrokeParameter(1, 2.5, 3.5, 4.5, 5.5);
    }

    @Test public void testStrokeParameterAges01()
    {
        Assert.assertEquals("Age must be 1", 1, this.parameter.getAge(), 0);
    }

    @Test public void testStrokeParameterCholesterol01()
    {
        Assert.assertEquals("Cholesterol must be 2.5", 2.5, this.parameter.getCholesterol(), 0);
    }

    @Test public void testStrokeParameterHDL01()
    {
        Assert.assertEquals("HDL must be 3.5", 3.5, this.parameter.getHdl(), 0);
    }

    @Test public void testStrokeParameterLDL01()
    {
        Assert.assertEquals("LDL must be 4.5", 4.5, this.parameter.getLdl(), 0);
    }

    @Test public void testStrokeParameterTriglyceride01()
    {
        Assert.assertEquals("Triglyceride must be 5.5", 5.5, this.parameter.getTriglyceride(), 0);
    }

    @Test public void testObjectDisplay01()
    {
        Assert.assertEquals("Object Display must Be " +
                        "StrokeParameter{age=1.0, cholesterol=2.5, hdl=3.5, ldl=4.5, triglyceride=5.5}",
                "StrokeParameter{age=1.0, cholesterol=2.5, hdl=3.5, ldl=4.5, triglyceride=5.5}",
                this.parameter.toString());
    }
}