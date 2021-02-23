package databaseApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DatabaseLogic {

	public static String tableName = "Table Name";
	public static int colCount;
	public static int rowCount = 1;
	public static int rows;
	static ArrayList<String> colName = new ArrayList<String>();
	static ArrayList<String> rowData = new ArrayList<String>();

	/**
	 * 
	 */
	public static void databaseQueries() {
		// Create, Fill, Drop, Car Model Table
		DatabaseLogic.execute(SqlModel.dropTable());
		DatabaseLogic.execute(SqlModel.createTable());
		DatabaseLogic.execute(SqlModel.fillTable());

		// Display Car Model Table
		DatabaseLogic.executeQueries(SqlModel.getAllCarMake());

		// Create, Fill, Drop, Car Make Table
		DatabaseLogic.execute(SqlMake.dropTable());
		DatabaseLogic.execute(SqlMake.createTable());
		DatabaseLogic.execute(SqlMake.fillTable());

	}

	/**
	 * @param queries
	 */
	static void executeQueries(String... queries) {
		rowData = new ArrayList<String>();
		colName = new ArrayList<String>();
		try (Connection connection = DriverManager.getConnection("jdbc:derby:DatabaseLogic");
				Statement statement = connection.createStatement();) {

			for (String s : queries) {
				ResultSet results = statement.executeQuery(s);
				ResultSetMetaData metaData = results.getMetaData();

				tableName = metaData.getTableName(1);
				colCount = metaData.getColumnCount();

				for (int i = 1; i <= metaData.getColumnCount(); i++) {
					colName.add(metaData.getColumnName(i));
				}

				rowCount = 1;
				while (results.next()) {
					for (int i = 1; i <= metaData.getColumnCount(); i++) {
						rowData.add(results.getString(i));
					}
					rowCount++;
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param sqlStatement
	 */
	static void execute(String sqlStatement) {
		// Connect to database
		try (Connection connection = DriverManager.getConnection("jdbc:derby:DatabaseLogic;create=true");
				Statement statement = connection.createStatement();) {

			// Execute Statement
			statement.execute(sqlStatement);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
