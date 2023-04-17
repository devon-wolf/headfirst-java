public class TestBox {
	private Integer i;
	private int j;

	public static void main(String[] args) {
		TestBox t = new TestBox();
		t.go();
	}

	public void go() {
		// the problem here is that we are telling j, which was initialized to 0, to now equal i, which initializes to a null ref; what happens here is that the JVM tries to convert i to an int with Integer.intValue(i), but i is null, so it can't do it
		// j = i;

		// if we do i = j instead, we'll get both set to 0
		i = j;

		// in this case, it compiles, but it never gets to these logs because there's a NullPointerException from the attempted reassignment
		System.out.println(j);
		System.out.println(i);
	}
}
