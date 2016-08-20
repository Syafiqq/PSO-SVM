package dataset.component.test;

import dataset.component.Type;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Muhammad Syafiq on 8/12/2016.
 * Email : Syafiq.rezpector@gmail.com
 * GitHub : Syafiqq
 */
public class TypeTest
{
    @Test public void testStatusName01()
    {
        final Type type = new Type("training", "Training");
        Assert.assertEquals("Name must be Training", "Training", type.getName());
    }

    @Test public void testStatusKey01()
    {
        final Type type = new Type("training", "Training");
        Assert.assertEquals("Name must be training", "training", type.getKey());
    }

    @Test public void testObjectDisplay01()
    {
        final Type type = new Type("training", "Training");
        Assert.assertEquals("StrokeData object display must be Type{key='training', name='Training'}", "Type{key='training', name='Training'}", type.toString());
    }
}