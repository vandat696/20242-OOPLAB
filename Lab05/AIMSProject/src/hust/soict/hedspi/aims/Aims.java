package hust.soict.hedspi.aims;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.exception.PlayerException;
import hust.soict.hedspi.aims.media.*;
import hust.soict.hedspi.aims.screen.manager.StoreManagerScreen;
import hust.soict.hedspi.aims.store.Store;

import java.util.ArrayList;	
import java.util.Scanner;

public class Aims {
	private static Store store = new Store();
    private static Cart cart = new Cart();
    private static StoreManagerScreen storeScreen;
    public static Store getStore() {
        return store;
	}    
    public static void openStoreScreen() {
    	storeScreen = new StoreManagerScreen(store);
        storeScreen.setVisible(true);
    }

    public static void closeStoreScreen() {
        if (storeScreen != null) {
            storeScreen.setVisible(false);
            storeScreen.dispose();  
            storeScreen = null;     
        }
    }
	public static void main(String[] args) throws PlayerException {
		storeSetup();
		openStoreScreen();
		Scanner keyboard = new Scanner(System.in);
		int choice;
        do {
            showMenu();
            choice = Integer.parseInt(keyboard.nextLine());
            switch (choice) {
                case 1:
                    handleViewStore();
                    break;
                case 2:
                    handleUpdateStore();
                    break;
                case 3:
                    handleCart();
                    break;
                case 0:
                    System.out.println("Exiting AIMS. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        } while (choice != 0);
        keyboard.close();
    }
	public static void handleViewStore() throws PlayerException {
		Scanner keyboard = new Scanner(System.in);
		int choice;
	    do {
	        store.displayItems();
	        storeMenu();
	        choice = Integer.parseInt(keyboard.nextLine());
	        switch (choice) {
	            case 1:
	                System.out.print("Enter the title of the media: ");
	                String title = keyboard.nextLine();
	                Media media = store.searchByTitle(title);
	                if (media != null) {
	                    System.out.println(media.toString());
	                    if (media instanceof DigitalVideoDisc || media instanceof CompactDisc) {
	                        mediaDetailsMenu();
	                        int subChoice = Integer.parseInt(keyboard.nextLine());
	                        if (subChoice == 1) {
	                            cart.addMedia(media);
	                        } else if (subChoice == 2 && media instanceof Playable) {
	                        	try {
	                        	    ((Playable) media).play();
	                        	} catch (PlayerException e) {
	                        	    System.err.println("Exception: " + e.getMessage());
	                        	    e.printStackTrace();
	                        	    javax.swing.JOptionPane.showMessageDialog(null, 
	                        	        e.getMessage(), 
	                        	        "Player Exception", 
	                        	        javax.swing.JOptionPane.ERROR_MESSAGE);
	                        	}
	                        }
	                    } else {
	                        mediaDetailsMenu();
	                        int subChoice = Integer.parseInt(keyboard.nextLine());
	                        if (subChoice == 1) {
	                            cart.addMedia(media);
	                        }
	                    }
	                } else {
	                    System.out.println("Media not found");
	                }
	                break;

	            case 2:
	                System.out.print("Enter the title of the media to add to cart: ");
	                String mediaTitle = keyboard.nextLine();
	                Media mediaToAdd = store.searchByTitle(mediaTitle);
	                if (mediaToAdd != null) {
	                    cart.addMedia(mediaToAdd);
	                    if (mediaToAdd instanceof DigitalVideoDisc) {
	                        int dvdCount = 0;
	                        for (Media m : cart.getItemsOrdered()) {
	                            if (m instanceof DigitalVideoDisc) {
	                                dvdCount++;
	                            }
	                        }
	                        System.out.println("There are currently " + dvdCount + " DVDs in the cart.");
	                    }
	                } else {
	                    System.out.println("Media not found");
	                }
	                break;

	            case 3:
	                System.out.print("Enter the title of the media to play: ");
	                String playTitle = keyboard.nextLine();
	                Media mediaToPlay = store.searchByTitle(playTitle);
	                if (mediaToPlay instanceof Playable) {
	                	try {
	                	    ((Playable) mediaToPlay).play();
	                	} catch (PlayerException e) {
	                	    System.err.println("Exception: " + e.getMessage());
	                	    e.printStackTrace();
	                	    javax.swing.JOptionPane.showMessageDialog(null, 
	                	        e.getMessage(), 
	                	        "Player Exception", 
	                	        javax.swing.JOptionPane.ERROR_MESSAGE);
	                	}
	                } else if (mediaToPlay != null) {
	                    System.out.println("This media cannot be played");
	                } else {
	                    System.out.println("Media not found");
	                }
	                break;

	            case 4:
	                cart.print();
	                break;

	            case 0:
	                System.out.println("Returning to main menu...");
	                break;

	            default:
	                System.out.println("Invalid option");
	        }
	    } while (choice != 0);
	    keyboard.close();
	}
	public static void handleUpdateStore() {
	    System.out.println("Update Store:");
	    System.out.println("1. Add media");
	    System.out.println("2. Remove media");
	    System.out.print("Choose an option: ");
	    Scanner keyboard = new Scanner(System.in);
		int choice = Integer.parseInt(keyboard.nextLine());
	    System.out.print("Enter media title: ");
	    String title = keyboard.nextLine();
	    Media media = store.searchByTitle(title);
	    switch (choice) {
	        case 1:
	            if (media == null) {
	                System.out.print("Enter media type (book/dvd/cd): ");
	                String type = keyboard.nextLine().toLowerCase();
	                System.out.print("Enter category: ");
	                String category = keyboard.nextLine();
	                System.out.print("Enter cost: ");
	                float cost = Float.parseFloat(keyboard.nextLine());
	                if (type.equals("book")) {
	                    Book book = new Book(title, category, cost);
	                    System.out.println("Enter the (List of) author(s) of the book (Press 0 to stop): ");
                      while (true) {
                          String bookAuthor = keyboard.nextLine();
                          if (bookAuthor.equals("0")) break;
                          book.addAuthor(bookAuthor);
                      }
	                    store.addMedia(book);
	                } else if (type.equals("dvd")) {
	                    System.out.print("Enter director: ");
	                    String director = keyboard.nextLine();
	                    System.out.print("Enter length: ");
	                    int length = Integer.parseInt(keyboard.nextLine());
	                    DigitalVideoDisc dvd = new DigitalVideoDisc(title, category, cost,length,director);
	                    store.addMedia(dvd);
	                } else if (type.equals("cd")) {
	                    System.out.print("Enter director: ");
	                    String director = keyboard.nextLine();
	                    System.out.print("Enter artist: ");
	                    String artist = keyboard.nextLine();
	                    CompactDisc cd = new CompactDisc(store.getqtyInStore() + 1, title, category, cost, director, artist);
	                    System.out.println("Do you want to add tracks to your CD?\n (1) Yes (0) No:");
                        int addTrack = Integer.parseInt(keyboard.nextLine());
                        if (addTrack == 1) {
                            System.out.println("Enter the number of tracks: ");
                            int numTracks = keyboard.nextInt();
                            keyboard.nextLine();
                            for (int i = 0; i < numTracks; i++) {
                                System.out.println("Enter the title of track " + (i + 1) + ": ");
                                String trackTitle = keyboard.nextLine();
                                System.out.println("Enter the length of track " + (i + 1) + ": ");
                                int trackLength = keyboard.nextInt();
                                keyboard.nextLine();
                                cd.addTrack(new Track(trackTitle, trackLength));
                            }
                        }
	                    store.addMedia(cd);
	                } else {
	                    System.out.println("Media type not supported");
	                }
	            } else {
	                System.out.println("Media already in the store");
	            }
	            break;

	        case 2:
	            if (media != null) {
	                store.removeMedia(media);
	            } else {
	                System.out.println("Media not in store");
	            }
	            break;

	        default:
	            System.out.println("Invalid option");
	    }
	    keyboard.close();
	}
	public static void handleCart() throws PlayerException {
		Scanner keyboard = new Scanner(System.in);
		int choice;
	    do {
	        cart.print();
	        cartMenu(); 
	        choice = Integer.parseInt(keyboard.nextLine());
	        switch (choice) {
	            case 1:
	                System.out.println("Filter by: 1. ID, 2. Title");
	                int filterChoice = keyboard.nextInt();
	                keyboard.nextLine();
	                if (filterChoice == 1) {
	                    System.out.print("Enter ID: ");
	                    int id = Integer.parseInt(keyboard.nextLine());
	                    cart.searchById(id);
	                } else if (filterChoice == 2) {
	                    System.out.print("Enter title: ");
	                    String title = keyboard.nextLine();
	                    cart.searchByTitle(title);
	                } else {
	                    System.out.println("Invalid option");
	                }
	                break;

	            case 2:
	                System.out.println("Sort by: 1. Title, 2. Cost");
	                int sortChoice = keyboard.nextInt();
	                keyboard.nextLine();
	                if (sortChoice == 1) {
	                    cart.sortMediaByTitleCost();
	                } else if (sortChoice == 2) {
	                    cart.sortMediaByCostTitle();
	                } else {
	                    System.out.println("Invalid option");
	                }
	                break;

	            case 3:
	                System.out.print("Enter the title of the media to remove: ");
	                String removeTitle = keyboard.nextLine();
	                Media toRemove = null;
	                for (Media m : new ArrayList<>(cart.getItemsOrdered())) {
	                    if (m.getTitle().equalsIgnoreCase(removeTitle)) {
	                        toRemove = m;
	                        break;
	                    }
	                }
	                if (toRemove != null) {
	                    cart.removeMedia(toRemove);
	                } else {
	                    System.out.println("Media not in cart");
	                }
	                break;

	            case 4:
	                System.out.print("Enter title to play: ");
	                String playTitle = keyboard.nextLine();
	                for (Media m : new ArrayList<>(cart.getItemsOrdered())) {
	                    if (m.getTitle().equalsIgnoreCase(playTitle) && m instanceof Playable) {
	                    	try {
	                    	    ((Playable) m).play();
	                    	} catch (PlayerException e) {
	                    	    System.err.println("Exception: " + e.getMessage());
	                    	    e.printStackTrace();
	                    	    javax.swing.JOptionPane.showMessageDialog(null, 
	                    	        e.getMessage(), 
	                    	        "Player Exception", 
	                    	        javax.swing.JOptionPane.ERROR_MESSAGE);
	                    	}
	                        break;
	                    }
	                }
	                break;

	            case 5:
	                System.out.println("Order id created");
	                cart = new Cart();  
	                break;
	            case 0:
	                System.out.println("Returning to main menu...");
	                break;

	            default:
	                System.out.println("");
	        }
	    } while (choice != 0);
	    keyboard.close();
	}



	public static void storeSetup(){
        // DVD
        DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King", "Animation", 19.95f, 87, "Roger Allers");
        DigitalVideoDisc dvd2 = new DigitalVideoDisc("Star Wars", "Science Fiction", 24.95f, 87, "George Lucas");
        DigitalVideoDisc dvd3 = new DigitalVideoDisc("Aladin", "Animation", 18.99f, 89, "Guy Ritchie");
        store.addMedia(dvd1); store.addMedia(dvd2); store.addMedia(dvd3);

        // CD + Track
        CompactDisc cd1 = new CompactDisc(0, "Thriller", "Pop", 9.99f, "Michael Jackson", "Hannah");
        Track track1 = new Track("Billie Jean", 236);
        Track track2 = new Track("Beat It", 252);
        Track track3 = new Track("Man in the Mirror", 331);
        cd1.addTrack(track1); cd1.addTrack(track2); cd1.addTrack(track3);

        CompactDisc cd2 = new CompactDisc(0, "Kind of Blue", "Jazz", 15.50f, "Miles Davis", null);
        Track track4 = new Track("So What", 431);
        Track track5 = new Track("Freddie Freeloader", 539);
        cd2.addTrack(track4); cd2.addTrack(track5);

        CompactDisc cd3 = new CompactDisc(0, "Bohemian Rhapsody", "Rock", 11.99f, "Queen", null);
        Track track6 = new Track("Bohemian Rhapsody", 355);
        Track track7 =  new Track("Killer Queen", 205);
        Track track8 = new Track("Somebody to Love", 296);
        cd3.addTrack(track6); cd3.addTrack(track7); cd3.addTrack(track8);

        store.addMedia(cd1); store.addMedia(cd2); store.addMedia(cd3);

        // Book
        Book book1 = new Book("Hannibal", "Crime Thriller", 8.99f);
        book1.addAuthor("Thomas Harris");
        Book book2 = new Book("Red Dragon", "Crime Thriller", 9.11f);
        book2.addAuthor("Thomas Harris");
        Book book3 = new Book("The Martian", "Science Fiction", 8.97f);
        book3.addAuthor("Andy Weir");
        store.addMedia(book1); store.addMedia(book2); store.addMedia(book3);
    }
	public static void showMenu() {
        System.out.println("AIMS: ");
        System.out.println("--------------------------------");
        System.out.println("1. View store");
        System.out.println("2. Update store");
        System.out.println("3. See current cart");
        System.out.println("0. Exit");
        System.out.println("--------------------------------");
        System.out.println("Please choose a number: 0-1-2-3");
    }

    public static void storeMenu() {
        System.out.println("Options: ");
        System.out.println("--------------------------------");
        System.out.println("1. See a media’s details");
        System.out.println("2. Add a media to cart");
        System.out.println("3. Play a media");
        System.out.println("4. See current cart");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.println("Please choose a number: 0-1-2-3-4");
    }

    public static void mediaDetailsMenu() {
        System.out.println("Options: ");
        System.out.println("--------------------------------");
        System.out.println("1. Add to cart");
        System.out.println("2. Play");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.println("Please choose a number: 0-1-2");
    }

    public static void cartMenu() {
        System.out.println("Options: ");
        System.out.println("--------------------------------");
        System.out.println("1. Filter media in cart");
        System.out.println("2. Sort media in cart");
        System.out.println("3. Remove media from cart");
        System.out.println("4. Play a media");
        System.out.println("5. Place order");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.println("Please choose a number: 0-1-2-3-4-5");
    }
    
}
	    
	    
	    
//		//Create a new cart
//	    Cart anOrder = new Cart();
//
//	    //Create new dvd objects and add them to the cart
//	    DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King",
//	    		"Animation", "Roger Allers", 87, 19.95f);
//	    //anOrder.addDigitalVideoDisc(dvd1);
//
//	    DigitalVideoDisc dvd2 = new DigitalVideoDisc("Star Wars",
//	            "Science Fiction", "George Lucas", 87, 24.95f);
//	    //anOrder.addDigitalVideoDisc(dvd2);
//
//	    DigitalVideoDisc dvd3 = new DigitalVideoDisc("Aladin",
//	            "Animation", 18.99f);
	    //anOrder.addDigitalVideoDisc(dvd3);
	    //anOrder.addDigitalVideoDisc(dvd1, dvd2, dvd3);
	    //anOrder.addDigitalVideoDisc(dvd1, dvd2);
	    //anOrder.displayCartItems();
	    //anOrder.removeDigitalVideoDisc(dvd2);
	    //anOrder.displayCartItems();
	    //System.out.println("DVD 1 ID: " + dvd1.getId());
	    //System.out.println("Total DVDs created: " + 
	    //DigitalVideoDisc.getNbDigitalVideoDiscs());
//	    List<Media> mediae = new ArrayList<Media>();
//
//	    mediae.add(cd);     
//	    mediae.add(dvd);    
//	    mediae.add(book);   
//
//	    for(Media m: mediae) {
//	        System.out.println(m.toString());
//	 }
//	}
//
//}
