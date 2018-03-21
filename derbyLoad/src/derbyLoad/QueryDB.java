import java.sql.Statement;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class QueryDB {
	public static final String SQL_STATEMENT = "select * from BN";
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = DriverManager.getConnection(CreateDB.JDBC_URL);
		//connection.createStatement().execute("CALL SYSCS_UTIL.SYSCS_IMPORT_TABLE (null, 'BN', '/Users/gerald/Downloads/bn1.csv',null,null,null,0)");
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(SQL_STATEMENT);
		ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
		int columnCount = resultSetMetaData.getColumnCount();
		for (int x = 1; x <= columnCount; x++)
		{
			System.out.format("%20s", resultSetMetaData.getColumnName(x) + " | ");
		}
		while (resultSet.next())
		{
			System.out.println("");
			for (int x = 1; x <= columnCount; x++)
			{
				System.out.format("%20s", resultSet.getString(x) + " | ");
			}
		}
		if (statement != null)
		{
			statement.close();
		}
		if (connection != null)
		{
			connection.close();
		}

	}

}
