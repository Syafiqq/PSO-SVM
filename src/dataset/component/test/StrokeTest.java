package dataset.component.test;

import dataset.component.Stroke;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Muhammad Syafiq on 8/12/2016.
 * Email : Syafiq.rezpector@gmail.com
 * GitHub : Syafiqq
 */
public class StrokeTest
{
    @Test public void testStrokeData01()
    {
        final Stroke stroke = new Stroke(10, 20.0, 30.0, 40.0, 50.0, 1);

        Assert.assertEquals("Age must be 10", 10, stroke.getAge());
        Assert.assertEquals("Cholesterol must be 20.0", 20.0, stroke.getCholesterole(), 0);
        Assert.assertEquals("HDL must be 30.0", 30.0, stroke.getHdl(), 0);
        Assert.assertEquals("LDL must be 40.0", 40.0, stroke.getLdl(), 0);
        Assert.assertEquals("Triglyceride must be 50.0", 50.0, stroke.getTriglyceride(), 0);
        Assert.assertEquals("Status must be 1", 1, stroke.getStatus());
        Assert.assertEquals("Stroke object display must be : Stroke{age=10, cholesterole=20.0, hdl=30.0, ldl=40.0, triglyceride=50.0, status=1}", "Stroke{age=10, cholesterole=20.0, hdl=30.0, ldl=40.0, triglyceride=50.0, status=1}", stroke.toString());
    }
}