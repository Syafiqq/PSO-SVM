package dataset.component.test;


import dataset.component.Status;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Muhammad Syafiq on 8/12/2016.
 * Email : Syafiq.rezpector@gmail.com
 * GitHub : Syafiqq
 */
public class StatusTest
{
    @Test public void testStatusNormal01()
    {
        final Status status = new Status("Normal");
        Assert.assertEquals("Name must be Normal", "Normal", status.getName());
        Assert.assertEquals("Object Display must Be Status{name='Normal'}", "Status{name='Normal'}", status.toString());
    }
}