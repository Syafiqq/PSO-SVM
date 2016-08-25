package dataset.component.stroke.exception;

/**
 * Created by Muhammad Syafiq on 8/25/2016.
 * Email : Syafiq.rezpector@gmail.com
 * GitHub : Syafiqq
 */
public class NoSuchStrokeStatusException extends StrokeMetadataException
{
    public NoSuchStrokeStatusException()
    {
        super();
    }

    public NoSuchStrokeStatusException(String message)
    {
        super(message);
    }

    public NoSuchStrokeStatusException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public NoSuchStrokeStatusException(Throwable cause)
    {
        super(cause);
    }

    protected NoSuchStrokeStatusException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
