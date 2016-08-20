package dataset.component.stroke;

import java.util.Arrays;

/**
 * Created by Muhammad Syafiq on 8/13/2016.
 * Email : Syafiq.rezpector@gmail.com
 * GitHub : Syafiqq
 */
public class StrokeParameter
{
    private final double[] parameter;

    public StrokeParameter(double age, double cholesterol, double hdl, double ldl, double triglyceride)
    {
        this.parameter = new double[]{age, cholesterol, hdl, ldl, triglyceride};
    }

    public StrokeParameter(final double[] parameter)
    {
        this.parameter = parameter;
    }

    public double[] getParameter()
    {
        return parameter;
    }

    public double getParameter(final int indexParams)
    {
        return this.parameter[indexParams];
    }

    @Override public String toString()
    {
        return "StrokeParameter{" +
                "parameter=" + Arrays.toString(parameter) +
                '}';
    }
}
