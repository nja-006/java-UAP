
public class Movie {
	String id;
	String title;
	String genre;
	int rating;
	int totalPrice;
	
	public Movie(String id, String title, String genre, int rating) {
		super();
		this.id = id;
		this.title = title;
		this.genre = genre;
		this.rating = rating;
		setTotalPrice();
	}
	
	void setTotalPrice(){
		this.totalPrice = title.length() * 500;
		if(this.genre.equals("Comedy"))
			this.totalPrice += 3000;
		else if(this.genre.equals("Action"))
			this.totalPrice += 4000;
		else if(this.genre.equals("Horror"))
			this.totalPrice += 5000;
	}
	
}
