package dataset.component.stroke;

/**
 * Created by Muhammad Syafiq on 8/13/2016.
 * Email : Syafiq.rezpector@gmail.com
 * GitHub : Syafiqq
 */
public class StrokeMetadata
{
    private final int status;

    public StrokeMetadata(int status)
    {
        this.status = status;
    }

    public int getStatus()
    {
        return status;
    }

    @Override public String toString()
    {
        return "StrokeMetadata{" +
                "status=" + status +
                '}';
    }
}
