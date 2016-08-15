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
        properties.url = "jdbc:sqlite:D:/Muhammad Syafiq/Documents/JetBrains/Idea/SVM_001/db/stroke.mcrypt";
    }

    private static void debugSetting()
    {
        final DBProperties properties = DBProperties.getInstance();
        properties.url = "jdbc:sqlite:D:/Muhammad Syafiq/Documents/JetBrains/Idea/SVM_001/db/stroke_test01.mcrypt";
    }


}
