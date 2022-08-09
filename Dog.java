class Dog {
	int size;
	String breed;
	String name;

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
		dogOne.size = 70;

		Dog dogTwo = new Dog();
		dogTwo.size = 8;

		Dog dogThree = new Dog();
		dogThree.size = 35;

		dogOne.bark(1);
		dogTwo.bark(2);
		dogThree.bark(3);
	}
}
