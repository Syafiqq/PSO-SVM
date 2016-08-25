package dataset.component.stroke.exception;

/**
 * Created by Muhammad Syafiq on 8/25/2016.
 * Email : Syafiq.rezpector@gmail.com
 * GitHub : Syafiqq
 */
public class StrokeMetadataException extends StrokeException
{
    public StrokeMetadataException()
    {
        super();
    }

    public StrokeMetadataException(String message)
    {
        super(message);
    }

    public StrokeMetadataException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public StrokeMetadataException(Throwable cause)
    {
        super(cause);
    }

    protected StrokeMetadataException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
