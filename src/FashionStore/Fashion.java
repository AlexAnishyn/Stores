package FashionStore;

import Stores.Stores;
import main.java.DbHelper;

// Create class 'Fashion' which implements Stores method

public class Fashion implements Stores{
	
	private static Fashion instance = null;
	private Fashion(){};
	
	// Use pattern 'Singelton' for ensure that only one instance of a class is create
	
	public static synchronized Fashion getInstance(){
		if (instance == null){
			instance = new Fashion();
		}
		return instance;
	}
	
	public void addition() {
		String query;
		
		// add products in MySQL table 'fashion_prod'
		
		query = "INSERT INTO fashion_prod (Title, Price, Status, Category) VALUES ('Scarf', '20', 'Available', 'winter clothes'), "
				+ "('Pants', '104', 'Absent', 'summer clothes'), ('Underwear', '2005', 'Expected', 'summer clothes');";
		DbHelper.executeQuery(query);
	}
	
	public void change() {
		String query;
		
		// variable which stores half products in others (not 'winter clothes') categories
		
		int limit;
		
		// query for identify half products in others (not 'winter clothes') categories
		
		query = "SELECT COUNT(f_prod_id)/2 FROM fashion_prod WHERE Category != 'winter clothes';";
		DbHelper.resultSet(query);
		
		limit = DbHelper.getLimit();
		
		// change Status of all products in Category 'winter clothes'
		
		query = "UPDATE fashion_prod SET Status = 'Absent' WHERE Category = 'winter clothes';";
		DbHelper.executeQuery(query);
		
		// change Status of half products in other categories
		
		query = "UPDATE fashion_prod fp1 INNER JOIN (SELECT f_prod_id AS id FROM fashion_prod WHERE Category = 'summer clothes' "
				+ "LIMIT "+ limit +")fp2 SET Status = 'Expected' WHERE fp1.Category != 'winter clothes' AND fp1.f_prod_id = fp2.id;";
		DbHelper.executeQuery(query);
		
		// increase Price of product on the 20% where Status 'Available'
		
		query = "UPDATE fashion_prod SET Price = Price*1.2 WHERE Status = 'Available';";
		DbHelper.executeQuery(query);
	}
}