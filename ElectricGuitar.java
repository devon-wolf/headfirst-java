class ElectricGuitar {
	private String brand;
	private int numOfPickups;
	private boolean rockStarUsesIt;

	public String getBrand() {
		return brand;
	}

	public void setBrand(String newBrand) {
		brand = newBrand;
	}

	public int getNumOfPickups() {
		return numOfPickups;
	}

	public void setNumOfPickups(int num) {
		numOfPickups = num;
	}

	public boolean getRockStarUsesIt() {
		return rockStarUsesIt;
	}

	public void setRockStarUsesIt(boolean yesOrNo) {
		rockStarUsesIt = yesOrNo;
	}
}

class ElectricGuitarTest {
	public static void main (String[] args) {
		ElectricGuitar guitar = new ElectricGuitar();
		guitar.setNumOfPickups(4);
		int numOfPickups = guitar.getNumOfPickups();
		System.out.println("Number of pickups: " + numOfPickups);
	}
}