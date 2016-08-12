package database.sqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Muhammad Syafiq on 8/12/2016.
 * Email : Syafiq.rezpector@gmail.com
 * GitHub : Syafiqq
 */
public class DBComponent
{
    public Connection connection;

    public DBComponent()
    {
        this.connection = null;
    }

    public void activate()
    {
        DBProperties properties = DBProperties.getInstance();
        try
        {
            this.connection = DriverManager.getConnection(properties.url);
        }
        catch(SQLException ignored)
        {
            System.err.println("Activate Database");
            System.exit(-1);
        }
    }

    public void deactivate()
    {
        DBComponent.closeConnection(this.connection);
    }

    public static void closeConnection(final Connection connection)
    {
        try
        {
            connection.close();
        }
        catch(SQLException | NullPointerException ignored)
        {
        }
    }

    public static void closeStatement(final Statement statement)
    {
        try
        {
            statement.close();
        }
        catch(SQLException | NullPointerException ignored)
        {
        }
    }

    public static void closeResultSet(final ResultSet resultSet)
    {
        try
        {
            resultSet.close();
        }
        catch(SQLException | NullPointerException ignored)
        {
        }
    }
}
