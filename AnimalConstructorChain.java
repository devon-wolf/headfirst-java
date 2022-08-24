class Animal {
	private String name;

	public Animal(String animalName) {
		System.out.println("Making an animal!");
		name = animalName;
	}

	public String getName() {
		return name;
	}
}

class Hippo extends Animal {
	public Hippo(String name) {
		super(name);
		System.out.println("Making a hippo!");
	}
}

class HippoTest {
	public static void main(String[] args) {
		Hippo hippo = new Hippo("Buffy");
		System.out.println("This hippo's name is " + hippo.getName());
	}
}