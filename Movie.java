class Movie {
	String title;
	String genre;
	int rating;

	void playIt() {
		System.out.println("Playing the movie!");
	}
}

class MovieTest {
	public static void main (String[] args) {
		Movie one = new Movie();
		one.title = "The First Movie";
		one.genre = "Tragic";
		one.rating = -2;

		Movie two = new Movie();
		two.title = "The Second Movie";
		two.genre = "Comedy";
		two.rating = 5;

		two.playIt();

		Movie three = new Movie();
		three.title = "The Third Movie";
		three.genre = "Tragicomedy";
		three.rating = 127;
	}
}
