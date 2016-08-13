package dataset.component.stroke.test;

import dataset.component.stroke.StrokeMetadata;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Muhammad Syafiq on 8/13/2016.
 * Email : Syafiq.rezpector@gmail.com
 * GitHub : Syafiqq
 */
public class StrokeMetadataTest
{
    private StrokeMetadata metadata;

    @Before public void initialize()
    {
        this.metadata = new StrokeMetadata(1);
    }

    @Test public void testStrokeMetadataStatus01()
    {
        Assert.assertEquals("Status must be 1", 1, this.metadata.getStatus());
    }

    @Test public void testObjectDisplay01()
    {
        Assert.assertEquals("Object Display must Be " +
                        "StrokeMetadata{status=1}",
                "StrokeMetadata{status=1}",
                this.metadata.toString());
    }
}