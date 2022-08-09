public class PhraseOMatic {
	public static void main (String[] args) {
		String[] wordListOne = {"helpful", "truthy", "beautiful", "yowling", "purple", "craven", "horse-like"};
		String[] wordListTwo = {"quick", "empty", "bullish", "abstract", "unearthly", "gray", "useless"};
		String[] wordListThree = {"house", "cloud", "garden", "desk", "book", "keyboard", "lamp", "dog collar"};

		int oneLength = wordListOne.length;
		int twoLength = wordListTwo.length;
		int threeLength = wordListThree.length;

		java.util.Random randomGenerator = new java.util.Random();
		int rand1 = randomGenerator.nextInt(oneLength);
		int rand2 = randomGenerator.nextInt(twoLength);
		int rand3 = randomGenerator.nextInt(threeLength);

		String phrase = String.format("%s %s %s", wordListOne[rand1], wordListTwo[rand2], wordListThree[rand3]);

		System.out.println("What we need is a " + phrase);
	}
}