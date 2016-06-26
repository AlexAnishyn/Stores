package FashionStore;

import Stores.Stores;
import Stores.StoresFactory;

// Create class 'FashionFactory' which implements StoresFactory methods

public class FashionFactory implements StoresFactory{

	public Stores createStores() {
		
		return Fashion.getInstance();
	}
}
