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
    @Test public void testStatusName01()
    {
        final Status status = new Status("normal", "Normal");
        Assert.assertEquals("Name must be Normal", "Normal", status.getName());
    }

    @Test public void testStatusKey01()
    {
        final Status status = new Status("normal", "Normal");
        Assert.assertEquals("Key must be normal", "normal", status.getKey());
    }

    @Test public void testObjectDisplay01()
    {
        final Status status = new Status("normal", "Normal");
        Assert.assertEquals("Object Display must Be Status{key='normal', name='Normal'}", "Status{key='normal', name='Normal'}", status.toString());
    }
}