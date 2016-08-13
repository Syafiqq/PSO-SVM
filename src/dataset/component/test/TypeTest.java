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
    @Test public void testStatusNormal01()
    {
        final Type type = new Type("Training");
        Assert.assertEquals("Name must be Training", "Training", type.getName());
        Assert.assertEquals("StrokeData object display must be Type{name='Training'}", "Type{name='Training'}", type.toString());
    }
}