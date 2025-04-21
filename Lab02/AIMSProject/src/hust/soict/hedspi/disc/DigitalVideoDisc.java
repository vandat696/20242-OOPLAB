package hust.soict.hedspi.disc;


public class DigitalVideoDisc {
	private static int nbDigitalVideoDiscs = 0;
	private int id;
	
	private String title;
	private String category;
	private String director;
	private int length;
	private float cost;
	
	public int getID() {
		return id;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getTitle() {
		return title;
	}
	public String getCategory() {
		return category;
	}
	public String getDirector() {
		return director;
	}
	public int getLength() {
		return length;
	}
	public float getCost() {
		return cost;
	}
	public DigitalVideoDisc(String title) {
		super();
		this.id = ++nbDigitalVideoDiscs;
		this.title = title;
	}
	public DigitalVideoDisc(String title, String category, float cost) {
		super();
		this.id = ++nbDigitalVideoDiscs;
		this.title = title;
		this.category = category;
		this.cost = cost;
	}
	public DigitalVideoDisc(String title, String category, String director, float cost) {
		super();
		this.id = ++nbDigitalVideoDiscs;
		this.title = title;
		this.category = category;
		this.director = director;
		this.cost = cost;
	}
	public DigitalVideoDisc(String title, String category, String director, int length, float cost) {
		super();
		this.id = ++nbDigitalVideoDiscs;
		this.title = title;
		this.category = category;
		this.director = director;
		this.length = length;
		this.cost = cost;
	}

	public static int getNbDigitalVideoDisc() {
		return nbDigitalVideoDiscs;
	}

	@Override
	public String toString() {
		return "DigitalVideoDisc [id=" + id + ", title=" + title + ", category=" + category + ", director=" + director
				+ ", length=" + length + ", cost=" + cost + "]";
	}
	
	public boolean isMatch(String title) {
	    if (this.title == null || title == null) {
	        return false;
	    }

	    String discTitleLower = this.title.toLowerCase();
	    String[] keywords = title.toLowerCase().split("\\s+");

	    for (String keyword : keywords) {
	        if (discTitleLower.contains(keyword)) {
	            return true;
	        }
	    }
	    return false;
	}
	
}
