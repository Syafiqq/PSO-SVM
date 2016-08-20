package dataset.component.stroke.test;

import dataset.component.stroke.StrokeData;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Muhammad Syafiq on 8/13/2016.
 * Email : Syafiq.rezpector@gmail.com
 * GitHub : Syafiqq
 */
public class StrokeDataTest
{
    private StrokeData strokeData;

    @Before public void initialize()
    {
        this.strokeData = new StrokeData(1, 2.5, 3.5, 4.5, 5.5, 1);
    }

    @Test public void testStrokeMetadataDisplay01()
    {
        Assert.assertEquals("StrokeMetadata Display must Be " +
                        "StrokeMetadata{status=1}",
                "StrokeMetadata{status=1}",
                this.strokeData.getMetadata().toString());
    }

    @Test public void testStrokeParameterDisplay01()
    {
        Assert.assertEquals("StrokeParameter Display must Be " +
                        "StrokeParameter{parameter=[1.0, 2.5, 3.5, 4.5, 5.5]}",
                "StrokeParameter{parameter=[1.0, 2.5, 3.5, 4.5, 5.5]}",
                this.strokeData.getParameterComponent().toString());
    }
}