import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CreateDB
{
	public static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
	public static final String JDBC_URL = "jdbc:derby:db;create=true";
	public static void main(String[] args) throws ClassNotFoundException, SQLException
	{
		Class.forName(DRIVER);
		Connection connection = DriverManager.getConnection(JDBC_URL);
		connection.createStatement().execute("DROP TABLE BN");
		connection.createStatement().execute("CREATE TABLE BN (REGISTER_NAME VARCHAR(30), BN_NAME VARCHAR(255), BN_STATUS VARCHAR(30), BN_REG_DT VARCHAR(30), BN_CANCEL_DT VARCHAR(30), BN_RENEW_DT VARCHAR(30), BN_STATE_NUM VARCHAR(30), BN_STATE_OF_REG VARCHAR(30), BN_ABN VARCHAR(30))");
		connection.createStatement().execute("CALL SYSCS_UTIL.SYSCS_IMPORT_TABLE (null, 'BN', '/home/ec2-user/bn1.csv',null,null,null,0)");
		System.out.println("import success");
	}

}
