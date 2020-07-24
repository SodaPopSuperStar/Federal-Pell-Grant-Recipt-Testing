import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class PellGrantTesting
{
    //Database Connection
    private static Connection c;
    private static String URL = "jdbc:sqlite:colleges.db";

    // Database Component
    @BeforeClass
    public static void connectDatabase() throws Exception
    {
        c = DriverManager.getConnection(URL);
    }

    @Test
    public void highestAmountComp() throws Exception
    {
        Statement st = c.createStatement();
        String sql = "SELECT * FROM colleges ORDER BY averageAmount DESC LIMIT 10";
        System.out.println(st.executeQuery(sql));
    }

    @Test
    public void lowestAmountComp() throws Exception
    {
        Statement st = c.createStatement();
        String sql = "SELECT * FROM colleges ORDER BY averageAmount ASC LIMIT 10";
        System.out.println(st.executeQuery(sql));
    }

    @Test
    public void mostReceipients() throws Exception
    {
        Statement st = c.createStatement();
        String sql = "SELECT * FROM colleges ORDER BY numStudents LIMIT 10";
        System.out.println(st.executeQuery(sql));
    }
}
