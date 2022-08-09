public class BottleSong {
	public static void main (String[] args) {
		int bottlesNum = 10;
		String word = "bottles";

		while (bottlesNum > 0) {
			System.out.println(bottlesNum + " " + word + " of beer on the wall,");
			System.out.println(bottlesNum + " " + word + " of beer,");
			System.out.println("If one of those bottles should happen to fall,");
			bottlesNum = bottlesNum - 1;
			if (bottlesNum == 1) {
				word = "bottle";
			}
			if (bottlesNum > 0) {
				System.out.println(bottlesNum + " " + word + " of beer on the wall!\n");
			} else {
				System.out.println("No more bottles of beer on the wall!");
				System.out.println("Thank you, good night!");
			}
		}
	}
}
