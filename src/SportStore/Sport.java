package SportStore;

import Stores.Stores;
import main.java.DbHelper;

// Create class 'Sport' which implements Stores method

public final class Sport implements Stores{
	
	private static Sport instance = null;
	private Sport(){};
	
	// Use pattern 'Singelton' for ensure that only one instance of a class is create
	
	public static synchronized Sport getInstance(){
		if (instance == null){
			instance = new Sport();
		}
		return instance;
	}
	
	public void addition() {
		String query;
		
		// add products
		
		query = "INSERT INTO sport_prod (Title, Price, Status, Category) VALUES ('Expander', '30', 'Available', 'summer stuff'), "
				+ "('Poise', '200', 'Absent', 'summer stuff'), ('Flippers', '310', 'Expected', 'winter stuff');";
		DbHelper.executeQuery(query);
	}
	
	public void change() {
		String query;
		
		// variable which stores half products in others (not 'winter clothes') categories
		
		int limit;
		
		// query for identify half products in others (not 'winter clothes') categories
		
		query = "SELECT COUNT(s_prod_id)/2 FROM sport_prod WHERE Category != 'summer stuff';";
		DbHelper.resultSet(query);
		
		limit = DbHelper.getLimit();
		
		// change Status of all products in Category 'summer stuff'
		
		query = "UPDATE sport_prod SET Status = 'Absent' WHERE Category = 'summer stuff';";
		DbHelper.executeQuery(query);
		
		// change Status of half products in other categories
		
		query = "UPDATE sport_prod sp1 INNER JOIN (SELECT s_prod_id AS id FROM sport_prod WHERE Category = 'winter stuff' "
				+ "LIMIT "+ limit +")sp2 SET Status = 'Expected' WHERE sp1.Category != 'summer stuff' AND sp1.s_prod_id = sp2.id;";
		DbHelper.executeQuery(query);
		
		// increase Price of product on the 20% where Status 'Available'
		
		query = "UPDATE sport_prod SET Price = Price*1.2 WHERE Status= 'Available';";
		DbHelper.executeQuery(query);
	}
}