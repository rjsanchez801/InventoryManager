package databaseApplication;

public class SqlMake {
	public static String createTable() {
		return "CREATE TABLE CarMake ("
				+ "CarMakeID int not null primary key,"
				+ "CarMake varchar(255),"
				+ "CarCountry varchar(255))";
	}
	
	public static String fillTable() { 
		return "INSERT INTO CarMake (CarMakeID, CarMake, CarCountry) VALUES" 
			+ "(1, 'Honda', 'Japan'),"
			+ "(2,'Ford', 'USA'),"
			+ "(3,'Subaru', 'Japan'),"
			+ "(4, 'Toyota', 'Japan'),"
			+ "(5,'Dodge', 'USA')";
	}
	
	public static String dropTable() {
		return "DROP TABLE CarMake";
	}
	
	public static String getAllCarMake() { 
		return "SELECT * FROM CarMake";
	}
}
