package dataset.component.stroke.exception;

/**
 * Created by Muhammad Syafiq on 8/25/2016.
 * Email : Syafiq.rezpector@gmail.com
 * GitHub : Syafiqq
 */
public class MethodNotSupportedException extends StrokeException
{
    public MethodNotSupportedException()
    {
        super();
    }

    public MethodNotSupportedException(String message)
    {
        super(message);
    }

    public MethodNotSupportedException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public MethodNotSupportedException(Throwable cause)
    {
        super(cause);
    }

    protected MethodNotSupportedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
