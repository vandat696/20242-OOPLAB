package hust.soict.hedspi.aims.store;

import hust.soict.hedspi.disc.DigitalVideoDisc;

public class StoreTest {

	public static void main(String[] args) {
		Store store = new Store();
		
		DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 87, 19.95f);
        DigitalVideoDisc dvd2 = new DigitalVideoDisc("Avengers: Endgame");
        
        store.addDVD(dvd1);
        store.addDVD(dvd2);

        store.removeDVD(dvd1);
	}

}
