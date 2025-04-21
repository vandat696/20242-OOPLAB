package hust.soict.hedspi.aims.store;

import java.util.ArrayList;

import hust.soict.hedspi.disc.DigitalVideoDisc;

public class Store {
	private ArrayList<DigitalVideoDisc> itemsInStore = new ArrayList<>();
	
	public void addDVD(DigitalVideoDisc dvd) {
		itemsInStore.add(dvd);
		System.out.println("Add DVD: " + dvd.getTitle() + " success!");
	}
	
	public void removeDVD(DigitalVideoDisc dvd) {
		if(itemsInStore.remove(dvd)) {
			System.out.println("Remove: " + dvd.getTitle() + " success!");
		} else {
			System.out.println("Khong ton tai de xoa!");
		}
	}
	
}
