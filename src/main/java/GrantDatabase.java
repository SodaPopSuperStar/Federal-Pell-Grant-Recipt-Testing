import org.junit.Before;
import org.junit.BeforeClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class GrantDatabase {


    //Database Connection
    private static Connection c;
    private static String URL = "jdbc:sqlite:colleges.db";

    // Database Component
    @BeforeClass
    public static void connectDatabase() throws Exception
    {
        c = DriverManager.getConnection(URL);
    }

    //Adds Colleges to Database
    public void addCollegeToDatabase(Connection c, College col) {
        try {
            Statement s = c.createStatement();
            String sql = "INSERT INTO colleges VALUES ( "
                    + col.getEntryNum() + ", \n" + col.getName()  + ", \n" +
                    col.getCity() + ", \n" + col.getState() + ", \n"
                    + col.getType() + ", \n" + col.getTotalGrant() + ", \n" + col.getNumStudents()
                    + ", \n" + col.getAverageAmount() + ")";
            s.executeUpdate(sql);
            s.close();
        } catch(Exception e) {
            e.printStackTrace();
        }

    }


    public static void main(String[] args) {
        GrantDatabase d = new GrantDatabase();
    }


}
