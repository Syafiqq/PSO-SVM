package dataset.component.stroke.exception;

/**
 * Created by Muhammad Syafiq on 8/25/2016.
 * Email : Syafiq.rezpector@gmail.com
 * GitHub : Syafiqq
 */
public class StrokeException extends Exception
{
    public StrokeException()
    {
        super();
    }

    public StrokeException(String message)
    {
        super(message);
    }

    public StrokeException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public StrokeException(Throwable cause)
    {
        super(cause);
    }

    protected StrokeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
