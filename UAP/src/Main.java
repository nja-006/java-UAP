import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

public class Main {
	
	Scanner sc = new Scanner(System.in);
	Vector<Movie> vMovie = new Vector<>();
	Random r = new Random();
	
	void cls() {
		for (int i = 0; i < 30; i++) {
			System.out.println();
		}
	}
	
	int scanInt() {
		int choose;
		try {
			choose = sc.nextInt();
		} catch (Exception e) {
			choose = -1;
			System.out.println("Input Must be Numeric");
		}
		sc.nextLine();
		return choose;
	}
	
	void menu() {
		System.out.println("================");
		System.out.println(" MOVIE   RENTAL");
		System.out.println("================");
		System.out.println("1. Add new Movie");
		System.out.println("2. View Movie (Sort by Title Ascending)");
		System.out.println("3. Rent Movie");
		System.out.println("4. EXIT");
		System.out.print  (">> ");
	}

	//Penting!!!
	String generateId() {
		String id = "";
		//Generate A to Z
		for (int i = 0; i < 2; i++) {
			id += (char)(r.nextInt(26) + 65);
		}
		//Generate 0-9
		for (int i = 0; i < 3; i++) {
			id += (char)(r.nextInt(10) + 48);
		}
		
		return id;
	}
	
	//Penting!!!
	void addNewMovie() {
		String id;
		String title;
		String genre;
		int rating;
		
		do
		{
			System.out.print  ("Input Movie Title [Length Must be 3 - 20 chars] : ");
			title = sc.nextLine();
		}
		while(title.length() < 3 || title.length() > 20);

		do
		{
			System.out.print  ("Input Movie Genre[Horror | Comedy | Action] : ");
			genre = sc.nextLine();
		}
		while(!genre.equals("Horror") && !genre.equals("Comedy") && !genre.equals("Action"));
		
		do
		{
			System.out.print  ("Input Movie Rating[1-10] : ");
			rating = scanInt();
		}
		while(rating < 1 || rating > 10);
		
		id = generateId();
		System.out.println("Generated MovieID : " + id);
		
		Movie m = new Movie(id, title, genre, rating);
		vMovie.add(m);
		
		System.out.println("\nInsert Success!");
		sc.nextLine();
	}
	
	//Penting!!!
	void sortAscending() {
		//Bubble Sort
		for (int i = 0; i < vMovie.size(); i++) {
			for (int j = 0; j < vMovie.size() - i - 1; j++) {
				if(vMovie.get(j).title.compareToIgnoreCase(vMovie.get(j+1).title) > 0)
				{
					//Swap
					Movie t = vMovie.get(j);
					vMovie.set(j, vMovie.get(j+1));
					vMovie.set(j+1, t);
				}
			}
		}
	}
	
	//Penting!!!
	void viewAllMovie() {
		//Ascending by Title
		if(vMovie.isEmpty()) {
			System.out.println("No Movie Found...");
			return;
		}
		sortAscending();
		
		System.out.println("=======================================================================");
		System.out.printf("| %-4s | %-7s | %-20s | %-7s | %-6s | %-8s |\n","No","ID","Title","Genre","Rating","Price");
		System.out.println("=======================================================================");
		for (int i = 0; i < vMovie.size(); i++) {
			Movie m = vMovie.get(i);
			System.out.printf("| %-4d | %-7s | %-20s | %-7s | %-6d | %-8d |\n",(i+1),m.id,m.title,m.genre,m.rating,m.totalPrice);
		}
		System.out.println("=======================================================================");
		
	}

	//Penting!!!
	void rentMovie() {
		int choose;
		int money;
		
		viewAllMovie();
		
		if(vMovie.isEmpty())
		{
			sc.nextLine();
			return;
		}
		
		do
		{
			System.out.print  ("Choose Movie index [1-" + vMovie.size() + "] : ");
			choose = scanInt();
		}
		while(choose < 1 || choose > vMovie.size());

		do
		{
			System.out.print  ("Input Money to Rent [MIN " + vMovie.get(choose-1).totalPrice + "] : ");
			money = scanInt();
		}
		while(money < vMovie.get(choose-1).totalPrice);
		
		System.out.println("Pay Rent Successful with " + (money - vMovie.get(choose-1).totalPrice) + " Change");
		vMovie.remove(choose-1);
		sc.nextLine();
	}
	
	//Penting!!!
	public Main() {
		int choose;
		
		do
		{
			cls();
			menu();
			choose = scanInt();
			
			switch (choose) {
				case 1:
					cls();
					addNewMovie();
				break;
				
				case 2:
					cls();
					viewAllMovie();
					sc.nextLine();
				break;
				
				case 3:
					cls();
					rentMovie(); 
				break;
			}
		}
		while(choose != 4);
	}
	
	//Penting!!!
	public static void main(String[] args) {
		new Main();
	}
}
