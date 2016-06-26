package SportStore;

import Stores.Stores;
import Stores.StoresFactory;

// Create class 'SportFactory' which implements StoresFactory methods

public class SportFactory implements StoresFactory{
	public Stores createStores() {
		return Sport.getInstance();
	}
}
