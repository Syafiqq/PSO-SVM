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
        Assert.assertEquals("Age must be 1", 1, this.parameter.getParameter(0), 0);
    }

    @Test public void testStrokeParameterCholesterol01()
    {
        Assert.assertEquals("Cholesterol must be 2.5", 2.5, this.parameter.getParameter(1), 0);
    }

    @Test public void testStrokeParameterHDL01()
    {
        Assert.assertEquals("HDL must be 3.5", 3.5, this.parameter.getParameter(2), 0);
    }

    @Test public void testStrokeParameterLDL01()
    {
        Assert.assertEquals("LDL must be 4.5", 4.5, this.parameter.getParameter(3), 0);
    }

    @Test public void testStrokeParameterTriglyceride01()
    {
        Assert.assertEquals("Triglyceride must be 5.5", 5.5, this.parameter.getParameter(4), 0);
    }

    @Test public void testObjectDisplay01()
    {
        Assert.assertEquals("Object Display must Be " +
                        "StrokeParameter{parameter=[1.0, 2.5, 3.5, 4.5, 5.5]}",
                "StrokeParameter{parameter=[1.0, 2.5, 3.5, 4.5, 5.5]}",
                this.parameter.toString());
    }
}