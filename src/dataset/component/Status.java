package dataset.component;

/**
 * Created by Muhammad Syafiq on 8/12/2016.
 * Email : Syafiq.rezpector@gmail.com
 * GitHub : Syafiqq
 */
public class Status
{
    private String name;

    public Status(final String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public void setName(final String name)
    {
        this.name = name;
    }

    @Override public String toString()
    {
        return "Status{" +
                "name='" + name + '\'' +
                '}';
    }
}
