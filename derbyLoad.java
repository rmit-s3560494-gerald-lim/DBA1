import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
import com.sun.tools.internal.xjc.Driver;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class derbyLoad
{
    private String framework = "embedded";
    private String protocol = "jdbc:derby";

    public static void main(String [] args)
    {
        new derbyLoad().go(args);
        System.out.println("Finished loading");

        String csvFile = "/home/ec2-user/BUSINESS_NAMES_201803.csv";
        BufferedReader br = null;
        String line = "";
        String csvSplitBy = "\t";

        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

                // use tab as separator
                String[] REGISTER_NAME = line.split(csvSplitBy);
                String[] BN_NAME = line.split(csvSplitBy);
                String[] BN_STATUS = line.split(csvSplitBy);
                String[] BN_REG_DT = line.split(csvSplitBy);
                String[] BN_CANCEL_DT = line.split(csvSplitBy);
                String[] BN_RENEW_DT = line.split(csvSplitBy);
                String[] BN_STATE_NUM = line.split(csvSplitBy);
                String[] BN_STATE_OF_REG = line.split(csvSplitBy);
                int[] BN_ABN = line.split(csvSplitBy);
                // System.out.println("Country [code= " + country[4] + " , name=" + country[5] + "]");

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

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
            s.execute("create table BN(REGISTER_NAME varchar(30), BN_NAME varchar(50), BN_STATUS varchar(30), BN_REG_DT varchar(30), BN_CANCEL_DT varchar(30), BN_RENEW_DT varchar(30), BN_STATE_NUM varchar(30), BN_STATE_OF_REG varchar(5), BN_ABN int)");
            System.out.println("Created table location");

            psInsert = conn.prepareStatement("insert into location values (?, ?)");
            statements.add(psInsert);

            psUpdate.executeUpdate();

            conn.commit();

            if(framework.equals("embedded"))
            {
                try
                {
                    DriverManager.getConnection("jdbc:derby:;shutdown=true");
                }
                catch (SQLException se)
                {
                    if (( (se.getErrorCode() == 50000) && ("XJ015".equals(se.getSQLState() ))) )
                    {
                        System.out.println("Derby shut down normally");
                    }
                    else
                    {
                        System.err.println("Derby did not shut down normally");
                        printSQLException(se);
                    }
                }
            }
            
        } catch (SQLException se) 
        {
            //TODO: handle exception
            printSQLException(sqle);
        }
        finally
        {
            try{
                if(conn != null)
                {
                    conn.close();
                    conn = null;
                }
            }
            catch(SQLException sqle)
            {
                printSQLException(sqle);
            }
        }
    }

    private void reportFailure(String message)
    {
        System.err.println("\nData verification failed");
        System.err.println('\t' + message);
    }

    public static void printSQLException(SQLException e)
    {
        while (e != null)
        {
            System.err.println("\n----- SQLException -----");
            System.err.println("  SQL State:  " + e.getSQLState());
            System.err.println("  Error Code: " + e.getErrorCode());
            System.err.println("  Message:    " + e.getMessage());
            // for stack traces, refer to derby.log or uncomment this:
            //e.printStackTrace(System.err);
            e = e.getNextException();
        }
    }

    private void parseArguments(String[] args)
    {
        if (args.length > 0)
        {
            if(args[0].equalsIgnoreCase("derbyclient"))
            {
                framework = "derbyclient";
                protocol = "jdbc:derby://localhost:1527/";
            }
        }
    }
}