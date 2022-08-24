public class Duck {
	int size;

	public Duck(int duckSize) {
		size = duckSize;
		System.out.println("Quack I am this big: " + size);	
	}
}

class DuckTest {
	public static void main(String[] args) {
		Duck d = new Duck(37);
	}
}