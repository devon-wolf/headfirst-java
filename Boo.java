public class Boo {
	public Boo(int i) {

	}

	public Boo(String s) {

	}

	public Boo(String s, int i) {

	}
}

class SonOfBoo extends Boo {
	public SonOfBoo() {
		super("boo");
	}

	public SonOfBoo(int i) {
		super("Fred");
	}

	public SonOfBoo(String s) {
		super(42);
	}

	public SonOfBoo(String a, String b, String c) {
		super(a, 42);
	}

	public SonOfBoo(int i, int x, int y) {
		super("star", i);
	}
}