import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.Properties;

import com.sun.tools.internal.xjc.Driver;

public class derbyLoad
{
    private String framework = "embedded";
    private String protocol = "jdbc:derby";

    public static void main(String [] args)
    {
        new derbyLoad().go(args);
        System.out.println("Finished loading");
    }

    void go(String [] args)
    {
        parseArguments(args);
        Connection conn = null;
        ArrayList<Statement> statements = new ArrayList<Statement>();
        PreparedStatement psInsert;
        PreparedStatement psUpdate;
        Statement s;
        ResultSet rs = null;
        try 
        {
            Properties props = new Properties();
            String dbName = "BusinessNames";
            conn = DriverManager.getConnection(protocol + dbName + ";create= true", props);

            System.out.println("Connected to and created database " + dbName);
            conn.setAutoCommit(false);

            s = conn.createStatement();
            statements.add(s);
            s.execute("create table BN(");
            
        } catch (Exception e) {
            //TODO: handle exception
        }
    }
}