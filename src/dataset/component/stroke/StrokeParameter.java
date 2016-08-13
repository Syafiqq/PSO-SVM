package dataset.component.stroke;

/**
 * Created by Muhammad Syafiq on 8/13/2016.
 * Email : Syafiq.rezpector@gmail.com
 * GitHub : Syafiqq
 */
public class StrokeParameter
{
    private final double age;
    private final double cholesterol;
    private final double hdl;
    private final double ldl;
    private final double triglyceride;

    public StrokeParameter(double age, double cholesterol, double hdl, double ldl, double triglyceride)
    {
        this.age = age;
        this.cholesterol = cholesterol;
        this.hdl = hdl;
        this.ldl = ldl;
        this.triglyceride = triglyceride;
    }

    public double getAge()
    {
        return age;
    }

    public double getCholesterol()
    {
        return cholesterol;
    }

    public double getHdl()
    {
        return hdl;
    }

    public double getLdl()
    {
        return ldl;
    }

    public double getTriglyceride()
    {
        return triglyceride;
    }

    @Override public String toString()
    {
        return "StrokeParameter{" +
                "age=" + age +
                ", cholesterol=" + cholesterol +
                ", hdl=" + hdl +
                ", ldl=" + ldl +
                ", triglyceride=" + triglyceride +
                '}';
    }
}
