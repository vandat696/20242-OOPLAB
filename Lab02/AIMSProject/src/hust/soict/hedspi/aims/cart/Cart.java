package hust.soict.hedspi.aims.cart;

import hust.soict.hedspi.disc.DigitalVideoDisc;

public class Cart {

	public static final int MAX_NUMBERS_ORDERED = 20;
	private DigitalVideoDisc itemsOrdered[] = new DigitalVideoDisc[MAX_NUMBERS_ORDERED];
	
	private int qtyOrdered = 0;
	
	public void addDigitalVideoDisc(DigitalVideoDisc disc) {
		if (qtyOrdered < MAX_NUMBERS_ORDERED) {
			itemsOrdered[qtyOrdered] = disc;
			qtyOrdered++;
			System.out.println("The disc has been added!");
			
		} else {
			System.out.println("The cart is almost full!");
		}
	}
	
	
	public DigitalVideoDisc[] getItemsOrdered() {
		return itemsOrdered;
	}


	public void setItemsOrdered(DigitalVideoDisc[] itemsOrdered) {
		this.itemsOrdered = itemsOrdered;
	}


	//	// different types of parameters. 
//	public void addDigitalVideoDisc(DigitalVideoDisc[] dvdList) {
//		for (DigitalVideoDisc dvd : dvdList) {
//			addDigitalVideoDisc(dvd);
//		}
//	}
	// arbitrary number of arguments
	public void addDigitalVideoDisc(DigitalVideoDisc... dvds) {
		for (DigitalVideoDisc dvd : dvds)
		{
			addDigitalVideoDisc(dvd);
		}
	}
	// differing the number of parameters
	public void addDigitalVideoDisc(DigitalVideoDisc dvd1, DigitalVideoDisc dvd2) {
		addDigitalVideoDisc(dvd1);
		addDigitalVideoDisc(dvd2);
	}
	// remove disc
	public void removeDigitalVideoDisc(DigitalVideoDisc disc) {
		boolean found = false;
		for (int i = 0; i < qtyOrdered; i++) {
			if (itemsOrdered[i] == disc) {
				found = true;
				for (int j = i; j < qtyOrdered - 1; j++) {
					itemsOrdered[j] = itemsOrdered[j+1];
				}
				itemsOrdered[qtyOrdered - 1] = null;
				qtyOrdered--;
				System.out.println("The disc has been remove!");
				break;
			}
		}
		if (!found) {
			System.out.println("The disc was not found!");
		}
	}
	
	public float totalCost() {
		float total = 0;
		for (int i = 0; i < qtyOrdered; i++)
		{
			total += itemsOrdered[i].getCost();
		}
		return total;
	}
	
	public void print() {
		System.out.println("***********************CART***********************\n"
							+ "Ordered Items:");
		for (int i = 0; i < qtyOrdered; i++ ) {
			if (itemsOrdered[i] == null) {break;}
			DigitalVideoDisc disc = itemsOrdered[i];
			System.out.println((i + 1) + ". DVD - "+
								disc.getTitle() + " - " +
								disc.getCategory() + " - " +
								disc.getDirector() + " - " +
								disc.getLength() + ": " + disc.getCost()
								+ " $");
		}
		System.out.println("Total cost: " + totalCost() + " $");
		System.out.println("***************************************************\n");
	}
	public void searchByID(int id) {
		boolean found = false;
		for (int i = 0; i < qtyOrdered; i++)
		{
			if (itemsOrdered[i].getID() == id)
			{
				found = true;
				DigitalVideoDisc disc = itemsOrdered[i];
				System.out.println("Found DVD:");
	            System.out.println("ID: " + disc.getID());
	            System.out.println("Title: " + disc.getTitle());
	            System.out.println("Category: " + disc.getCategory());
	            System.out.println("Director: " + disc.getDirector());
	            System.out.println("Length: " + disc.getLength());
	            System.out.println("Cost: " + disc.getCost() + "$");
	            break;
			}	
		}
		if (!found) {
	        System.out.println("Khong tim thay DVD!: " + id);
	    }
	}
	
	public void searchByTitle(String keywords) {
		boolean found = false;
		System.out.println("=====> Search DVD by ID <=====");
		for (int i = 0; i < qtyOrdered; i++) {
				if (itemsOrdered[i].isMatch(keywords)) {
					DigitalVideoDisc disc = itemsOrdered[i];
					System.out.println("--------------------------------");
					System.out.println("ID: " + disc.getID());
					System.out.println("Title: " + disc.getTitle());
					System.out.println("Category: " + disc.getCategory());
					System.out.println("Director: " + disc.getDirector());
					System.out.println("Length: " + disc.getLength());
					System.out.println("Cost: " + disc.getCost() + "$");
					found = true;
				}
		}
		if (found == false) {
			System.out.println("Khong tim thay DVD!");
		}
	}
}
