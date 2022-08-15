class Game {

}

class GameTest {

}

class Startup {
	private String name;
	private String[] location;
	private int numOfHits;

	public void setName(String newName) {
		name = newName;
	}

	public String getName() {
		return name;
	}

	public void setLocation(String[] newLocation) {
		location = newLocation;
	}

	public String[] getLocation() {
		return location;
	}

	public int getNumOfHits() {
		return numOfHits;
	}

	public String checkGuess(String guess) {
		String result = "Miss";

		if (!guess.equals("")) {
			for (int i = 0; i < location.length; i++) {
				if (location[i].equals(guess)) {
					location[i] = "";
					numOfHits++;
					result = "Hit";
					break;
				}
			}
		}
		
		return result;
	}

	public boolean isSunk() {
		return numOfHits >= location.length;
	}

}

class StartupTest extends Test {
	private static String name = "Startup Class Tests";

	public static void main(String[] args) {
		System.out.println();
		System.out.println("Startup Class Tests");
		System.out.println("=====");

		Startup startup = new Startup();
		startup.setName("testup");

		String[] locationCells = {"A0", "A1", "A2"};
		startup.setLocation(locationCells);

		String guessHit1 = "A1";
		String guessHit2 = "A0";
		String guessHit3 = "A2";

		String guessWrong = "B2";
		String guessDupe = guessHit1;
		String guessEmpty = "";

		String hit = "Hit";
		String miss = "Miss";

		String resultHit = startup.checkGuess(guessHit1);
		printTest("it should return a hit for guessHit1", resultHit.equals(hit));

		String resultMiss = startup.checkGuess(guessWrong);
		printTest("it should return a miss for a wrong guess", resultMiss.equals(miss));

		printTest("it should reflect one hit on the startup", startup.getNumOfHits() == 1);
		printTest("it should indicate that the startup is not sunk", startup.isSunk() == false);

		String hit2 = startup.checkGuess(guessHit2);
		printTest("it should return a hit on guessHit2", hit2.equals(hit));

		String dupeHit = startup.checkGuess(guessDupe);
		printTest("it should return a miss if a previously hit cell is guessed", dupeHit.equals(miss));

		String emptyMiss = startup.checkGuess(guessEmpty);
		printTest("it should return a miss when an empty string is guessed", emptyMiss.equals(miss));

		printTest("it should still show the startup as not yet sunk", startup.isSunk() == false);

		String hit3 = startup.checkGuess(guessHit3);
		printTest("it should return a hit on guessHit3", hit3.equals(hit));

		printTest("it should show the number of hits as 3", startup.getNumOfHits() == 3);
		printTest("it should indicate that the startup is sunk after hitting all location cells", startup.isSunk() == true);

		System.out.println("=====");
	}
}

class Test {
	public static void main(String[] args) {
		boolean expectation = false;
		String description = "it is not a test";
		printTest(description, expectation);
	}

	private static String getPassOrFail(boolean expectation) {
		if (expectation == true) {
			return "PASS";
		}
		return "FAIL";
	}

	protected static void printTest(String description, boolean expectation) {
		System.out.println(getPassOrFail(expectation) + ": " + description);
	}
}