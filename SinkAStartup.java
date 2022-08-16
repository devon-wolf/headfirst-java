import java.util.Scanner;
class GameHelper {
	public String getUserInput(String prompt) {
		System.out.print(prompt + ": ");
		Scanner scanner = new Scanner(System.in);
		return scanner.next();
	}
}

class Game {
	private static int guessCount = 0;

	public static void main(String[] args) {
		Startup startup = new Startup();
		startup.assignRandomName();
		startup.assignRandomLocation();

		printLocation(startup);

		while (!startup.isSunk()) {
			GameHelper helper = new GameHelper();

			// worth noting: hitting return on empty input makes a newline, not an empty string, and it keeps waiting for input
			String guess = helper.getUserInput("Enter a cell");
			guessCount++;

			String result = startup.checkGuess(guess);
			printResult(result);
		}

		printSunkMessage(startup);
		printFinalGuessCount();
	}

	static void printLocation(Startup startup) {
		System.out.println("The test location of the startup is: ");
		for (String cell:startup.getLocation()) {
			System.out.print(cell + " ");
		}
		System.out.println();
	}

	static void printResult(String result) {
		System.out.println(result);
	}

	static void printSunkMessage(Startup startup) {
		System.out.println("You sunk " + startup.getName() + "!");
	}

	static void printFinalGuessCount() {
		System.out.println("The game took you " + guessCount + " guesses.");
	}
}

class Startup {
	private String name;
	private String[] location;
	private int numOfHits = 0;

	public void setName(String newName) {
		name = newName;
	}

	public String getName() {
		return name;
	}

	public void assignRandomName() {
		String[] names = {"blorbocorp", "mufflr", "shortmyeats", "cacophony", "pocketpal"};
		name = names[(int) (Math.random() * names.length)];
	}

	public void setLocation(String[] newLocation) {
		location = newLocation;
	}

	public void assignRandomLocation() {
		String[] rows = { "A", "B", "C", "D", "E", "F", "G" };
		String[] cols = { "0", "1", "2", "3", "4", "5", "6" };

		int coinFlip = (int) (Math.random() * 2);
		int randomAxis = (int) (Math.random() * 7);
		int randomSpanStart = (int) (Math.random() * 7);

		if (coinFlip == 1) {
			String startingRow = rows[randomAxis];
			if (randomSpanStart <= 4) {
				String[] newLocation = { startingRow + cols[randomSpanStart], startingRow + cols[(randomSpanStart + 1)], startingRow + cols[(randomSpanStart + 2)] };
				location = newLocation;
			} else {
				String[] newLocation = { startingRow + cols[randomSpanStart], startingRow + cols[(randomSpanStart - 1)], startingRow + cols[(randomSpanStart - 2)] };
				location = newLocation;
			}
		} else {
			String startingCol = cols[randomAxis];
			if (randomSpanStart <= 4) {
				String[] newLocation = { rows[randomSpanStart] + startingCol, rows[(randomSpanStart + 1)] + startingCol, rows[(randomSpanStart + 2)] + startingCol };
				location = newLocation;
			} else {
				String[] newLocation = { rows[randomSpanStart] + startingCol, rows[(randomSpanStart - 1)] + startingCol, rows[(randomSpanStart - 2)] + startingCol };
				location = newLocation;
			}
		}
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

		Startup randomStartup = new Startup();
		randomStartup.assignRandomName();
		randomStartup.assignRandomLocation();

		printTest("it should have a name", randomStartup.getName() != null);
		System.out.println("\t(name: " + randomStartup.getName() + ")");

		
		printTest("it should have a location", randomStartup.getLocation() != null);
		System.out.print("\t(location: ");
		for (String cell:randomStartup.getLocation()) {
			System.out.print(cell + " ");
		}
		System.out.println(")");

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