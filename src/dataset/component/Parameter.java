package dataset.component;

/**
 * Created by Muhammad Syafiq on 8/12/2016.
 * Email : Syafiq.rezpector@gmail.com
 * GitHub : Syafiqq
 */
public class Parameter
{
    private String key;
    private String name;

    public Parameter(final String key, final String name)
    {
        this.key = key;
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

    public String getKey()
    {
        return key;
    }

    public void setKey(final String key)
    {
        this.key = key;
    }

    @Override public String toString()
    {
        return "Parameter{" +
                "key='" + key + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
