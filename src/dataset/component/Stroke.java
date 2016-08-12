package dataset.component;

/**
 * Created by Muhammad Syafiq on 8/12/2016.
 * Email : Syafiq.rezpector@gmail.com
 * GitHub : Syafiqq
 */
public class Stroke
{
    private int age;
    private double cholesterole;
    private double hdl;
    private double ldl;
    private double triglyceride;
    private int status;

    public Stroke(int age, double cholesterole, double hdl, double ldl, double triglyceride, int status)
    {
        this.age = age;
        this.cholesterole = cholesterole;
        this.hdl = hdl;
        this.ldl = ldl;
        this.triglyceride = triglyceride;
        this.status = status;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public double getCholesterole()
    {
        return cholesterole;
    }

    public void setCholesterole(double cholesterole)
    {
        this.cholesterole = cholesterole;
    }

    public double getHdl()
    {
        return hdl;
    }

    public void setHdl(double hdl)
    {
        this.hdl = hdl;
    }

    public double getLdl()
    {
        return ldl;
    }

    public void setLdl(double ldl)
    {
        this.ldl = ldl;
    }

    public double getTriglyceride()
    {
        return triglyceride;
    }

    public void setTriglyceride(double triglyceride)
    {
        this.triglyceride = triglyceride;
    }

    public int getStatus()
    {
        return status;
    }

    public void setStatus(int status)
    {
        this.status = status;
    }

    @Override public String toString()
    {
        return "Stroke{" +
                "age=" + age +
                ", cholesterole=" + cholesterole +
                ", hdl=" + hdl +
                ", ldl=" + ldl +
                ", triglyceride=" + triglyceride +
                ", status=" + status +
                '}';
    }
}
