package dataset.component;

/**
 * Created by Muhammad Syafiq on 8/12/2016.
 * Email : Syafiq.rezpector@gmail.com
 * GitHub : Syafiqq
 */
public class Parameter
{
    private String name;

    public Parameter(final String name)
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
        return "Parameter{" +
                "name='" + name + '\'' +
                '}';
    }
}
