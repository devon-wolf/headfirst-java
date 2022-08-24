public class Duck {
	int size;

	public Duck() {
		// if no args are passed to the new Duck, set it to default size 25
		size = 25;
		System.out.println("Quack I am this big: " + size);
	}

	public Duck(int duckSize) {
		size = duckSize;
		System.out.println("Quack I am this big: " + size);
	}
}

class DuckTest {
	public static void main(String[] args) {
		Duck d = new Duck(37);
		Duck d2 = new Duck();
	}
}