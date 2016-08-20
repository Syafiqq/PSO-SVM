package dataset.component.stroke;

/**
 * Created by Muhammad Syafiq on 8/12/2016.
 * Email : Syafiq.rezpector@gmail.com
 * GitHub : Syafiqq
 */
public class StrokeData
{
    private final StrokeMetadata metadata;
    private final StrokeParameter parameter;

    public StrokeData(double age, double cholesterol, double hdl, double ldl, double triglyceride, int status)
    {
        this.metadata = new StrokeMetadata(status);
        this.parameter = new StrokeParameter(age, cholesterol, hdl, ldl, triglyceride);
    }

    public StrokeData(StrokeMetadata metadata, StrokeParameter parameter)
    {
        this.metadata = metadata;
        this.parameter = parameter;
    }

    public StrokeMetadata getMetadata()
    {
        return metadata;
    }

    public StrokeParameter getParameterComponent()
    {
        return this.parameter;
    }

    @Override public String toString()
    {
        return "StrokeData{" +
                "metadata=" + metadata +
                ", parameter=" + parameter +
                '}';
    }
}
