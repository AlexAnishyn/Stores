package main.java;

import java.util.ArrayList;
import java.util.List;

import FashionStore.FashionFactory;
import SportStore.SportFactory;
import Stores.Stores;

/*
 *  Create class 'SportThread' which implements the 
 *  Runnable interface for create a thread 
 */

class SportThread implements Runnable {
	
	public void run(){
		
		SportFactory sf = new SportFactory();
		List<Stores> sport = new ArrayList<Stores>();
		
		sport.add(sf.createStores());
		
		// Adding changed in data of sport tables
		
		for(Stores s:sport){
			DbHelper.connect();
			s.addition();
			s.change();
			DbHelper.close();
		}
	}
}

/*
 *  Create class 'FashionThread' which implements the 
 *  Runnable interface for create a thread 
 */

class FashionThread implements Runnable {
	
	public void run(){
		
		FashionFactory ff = new FashionFactory();
		List<Stores> fashion = new ArrayList<Stores>();
		
		fashion.add(ff.createStores());
		
		// Adding changed in data of fashion tables
		
		for(Stores f:fashion){
			DbHelper.connect();
			f.addition();
			f.change();
			DbHelper.close();
		}
	}
}
	
// Create class 'FactoryMethod'

public class FactoryMethod {
	
	public static void main(String[] args) throws InterruptedException{
		
		SportThread sport;
		FashionThread fashion;
		
		sport = new SportThread();
		fashion = new FashionThread();
		
		/* 
		 *  Putting objects 'sport' and 'fashion' into appropriate 
		 *  threads and run their work with difference of 10 second 
		 */
		
		Thread thread_sport = new Thread(sport);
		Thread thread_fashion = new Thread (fashion);
		
		thread_sport.start();
		
		Thread.sleep(10000);
		
		thread_fashion.start();
		
		System.out.println("Successful! Work is over.");
	}
}