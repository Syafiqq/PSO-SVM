package main;

import database.sqlite.DBProperties;

/**
 * Created by Muhammad Syafiq on 8/12/2016.
 * Email : Syafiq.rezpector@gmail.com
 * GitHub : Syafiqq
 */
public class Main
{
    public static void getMySqliteProperties()
    {
        Main.debugSetting();
    }

    private static void releaseSetting()
    {
        final DBProperties properties = DBProperties.getInstance();
        properties.url = "jdbc:sqlite:"+System.getProperty("user.dir")+"/db/stroke.mcrypt";
    }

    private static void debugSetting()
    {
        Main.debug_002();
    }

    private static void debug_001()
    {
        final DBProperties properties = DBProperties.getInstance();
        properties.url = "jdbc:sqlite:"+System.getProperty("user.dir")+"/db/stroke_test01.mcrypt";
    }

    private static void debug_002()
    {
        final DBProperties properties = DBProperties.getInstance();
        properties.url = "jdbc:sqlite:"+System.getProperty("user.dir")+"/db/stroke_test02.mcrypt";
    }
}
