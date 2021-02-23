package databaseApplication;

public class SqlModelMake {
	public static String createJoinTable() {
		return "CREATE TABLE ModelStyle (MakerID int, MakerJoinID int, StyleID varchar(255))";
	}
	
	public static String joinTable() {
		return "SELECT CarModel.ID, CarMake.CarMake, CarModel.CarModel, CarModel.CarStyle, CarModel.CarPrice "
				+ "FROM ModelStyle " 
				+ "INNER JOIN CarMake ON CarMake.CarMakeID = ModelStyle.MakerID "
				+ "INNER JOIN CarModel ON CarModel.CarMakeID = ModelStyle.MakerJoinID "
				+ "WHERE CarModel.CarStyle = ModelStyle.StyleID";
	}
	
	public static String dropJoinTable() {
		return "DROP TABLE ModelStyle";
	}
	
	public static String createCountryTable() {
		return "CREATE TABLE ModelCountry (MakerID int, CarCountryID int)";
	}	
	
	public static String joinCountryTable() {
		return "SELECT CarModel.ID, CarMake.CarCountry, CarMake.CarMake, CarModel.CarModel, CarModel.CarPrice "
				+ "FROM ModelCountry " 
				+ "INNER JOIN CarModel ON CarModel.CarMakeID = ModelCountry.MakerID "
				+ "INNER JOIN CarMake ON CarMake.CarMakeID = ModelCountry.CarCountryID";
	}
	
	public static String dropCountryTable() {
		return "DROP TABLE ModelCountry";
	}
}