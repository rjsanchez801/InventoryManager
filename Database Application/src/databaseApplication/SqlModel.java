package databaseApplication;

public class SqlModel {
	public static String createTable() {
		return "CREATE TABLE CarModel ("
				+ "ID int not null primary key"
				+ " GENERATED ALWAYS AS IDENTITY"
				+ " (START WITH 1, INCREMENT BY 1),"
				+ "CarModel varchar(255),"
				+ "CarStyle varchar(255),"
				+ "CarYear int,"
				+ "CarPrice int,"
				+ "CarMakeID int)";
	}

	public static String fillTable() { 
		return "INSERT INTO CarModel (CarModel, CarStyle, CarYear, CarPrice, CarMakeID) VALUES" 
			+ "('CR-V', 'SUV', 2019, 25400, 1),"
			+ "('Accord', 'Sedan', 2017, 19300, 1),"			
			+ "('Ridgeline', 'Truck', 2020, 29300, 1),"
			+ "('Civic', 'Coupe', 2013, 13500, 1),"
			+ "('Fit', 'Sedan', 2015, 10200, 1),"
			+ "('Clarity', 'Hybrid', 2017, 20100, 1),"
			+ "('Accord', 'Sedan', 2013, 13300, 1),"
			+ "('Civic', 'Coupe', 2017, 17400, 1),"
			+ "('Civic', 'Coupe', 2020, 20200, 1),"
			+ "('Odyssey', 'Van', 2019, 29300, 1),"	
			+ "('Pilot', 'SUV', 2020, 32100, 1),"
			+ "('Fit', 'Sedan', 2010, 6200, 1),"
			+ "('Fusion', 'Sedan', 2017, 20400, 2),"	
			+ "('Explorer', 'SUV', 2016, 25100, 2),"
			+ "('F-150', 'Truck', 2019, 26000, 2),"	
			+ "('F-350', 'Truck', 2020, 46500, 2),"
			+ "('Mustang', 'Coupe', 2020, 27300, 2),"
			+ "('Edge', 'SUV', 2014, 21000, 2),"	
			+ "('Expedition', 'SUV', 2019, 48800, 2),"
			+ "('Mustang', 'Coupe', 2013, 19300, 2),"
			+ "('Fiesta', 'Sedan', 2020, 14200, 2),"	
			+ "('Escape', 'SUV', 2018, 25100, 2),"
			+ "('Fusion', 'Sedan', 2017, 20000, 2),"
			+ "('Expedition', 'SUV', 2015, 40000, 2),"
			+ "('Forester', 'SUV', 2015, 15200, 3),"
			+ "('Outback', 'Sedan', 2010, 10500, 3),"
			+ "('Impreza', 'Sedan', 2019, 24200, 3),"
			+ "('CrossTrek', 'SUV', 2018, 25500, 3),"
			+ "('CrossTrek', 'SUV', 2017, 22600, 3),"
			+ "('Legacy', 'Sedan', 2020, 31400, 3),"
			+ "('Outback', 'Sedan', 2018, 25500, 3),"	
			+ "('Ascent', 'SUV', 2020, 32200, 3),"
			+ "('Forester', 'SUV', 2013, 11200, 3),"
			+ "('WRX', 'Sedan', 2019, 27200, 3),"	
			+ "('CrossTrek', 'SUV', 2020, 25600, 3),"
			+ "('4Runner', 'SUV', 2019, 39200, 4),"
			+ "('Corolla', 'Sedan', 2015, 15200, 4),"
			+ "('Rav4', 'SUV', 2010, 14600, 4),"
			+ "('Prius', 'Hybrid', 2019, 24200, 4),"
			+ "('Prius', 'Hybrid', 2016, 19300, 4),"
			+ "('Sienna', 'Van', 2020, 31400, 4),"
			+ "('Tacoma', 'Truck', 2018, 25500, 4),"	
			+ "('4Runner', 'SUV', 2014, 32200, 4),"
			+ "('Camry', 'Sedan', 2011, 14700, 4),"	
			+ "('Camry', 'Sedan', 2019, 24200, 4),"	
			+ "('Rav4', 'SUV', 2020, 25600, 4),"
			+ "('Yaris', 'Sedan', 2020, 15600, 4),"
			+ "('Ram 1500', 'Truck', 2016, 34200, 5),"
			+ "('Ram 3500', 'Truck', 2019, 36200, 5),"
			+ "('Challenger', 'Sedan', 2014, 17500, 5),"
			+ "('Grand Caravan', 'Van', 2015, 15200, 5),"
			+ "('Durango', 'SUV', 2019, 24200, 5),"
			+ "('Charger', 'Sedan', 2016, 28900, 5),"
			+ "('Ram 1500', 'Truck', 2020, 38200, 5),"
			+ "('Charger', 'Sedan', 2020, 31400, 5),"
			+ "('Challenger', 'Sedan', 2018, 25500, 5),"	
			+ "('Ram 1500', 'Truck', 2014, 32200, 5)";
			
	}
	
	public static String dropTable() {
		return "DROP TABLE CarModel";
	}
	
	public static String getAllCarMake() { 
		return "SELECT ID, CarModel, CarStyle, CarYear, CarPrice FROM CarModel";
	}
	
	public static String getAllHonda() { 
		return "SELECT * FROM CarModel WHERE CarMakeID = 1";
	}
	
	public static String queryCarWagon() { 
		return  "SELECT CarModel, CarStyle, CarYear, CarPrice FROM CarModel WHERE CarStyle = 'Wagon'";
	}
}
