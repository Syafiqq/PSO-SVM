package database.sqlite;

/**
 * Created by Muhammad Syafiq on 8/12/2016.
 * Email : Syafiq.rezpector@gmail.com
 * GitHub : Syafiqq
 */
public class DBProperties
{
    private static DBProperties ourInstance = new DBProperties();
    public String url;

    private DBProperties()
    {
    }

    public static DBProperties getInstance()
    {
        return DBProperties.ourInstance;
    }
}
