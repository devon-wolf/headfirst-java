class Dog {
	private int size;
	private String breed;
	private String name;

	public int getSize() {
		return size;
	}

	public void setSize(int newSize) {
		size = newSize;
	}

	void bark(int numberOfBarks) {
		while (numberOfBarks > 0) {
			if (size > 60) {
				System.out.println("Woof!");
			} else if (size > 14) {
				System.out.println("Ruff!");
			} else {
				System.out.println("Yip!");
			}
			numberOfBarks = numberOfBarks - 1;
		}
		
	}
}

class DogTest {
	public static void main (String[] args) {
		Dog dogOne = new Dog();
		dogOne.setSize(70);

		Dog dogTwo = new Dog();
		dogTwo.setSize(8);

		Dog dogThree = new Dog();
		dogThree.setSize(35);

		System.out.println("Dog 1: " + dogOne.getSize());
		System.out.println("Dog 2: " + dogTwo.getSize());
		System.out.println("Dog 3: " + dogThree.getSize());

		dogOne.bark(1);
		dogTwo.bark(2);
		dogThree.bark(3);
	}
}
