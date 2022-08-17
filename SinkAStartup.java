import java.util.Scanner;
import java.util.ArrayList;

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

		String[] rows = { "A", "B", "C", "D", "E", "F", "G" };
		String[] cols = { "0", "1", "2", "3", "4", "5", "6" };
		startup.assignRandomLocation(rows, cols);

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
	private ArrayList<String> location = new ArrayList<String>();

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

	public void setLocation(ArrayList<String> newLocation) {
		location = newLocation;
	}

	public void assignRandomLocation(String[] rows, String[] cols) {
		boolean baseIsRow = Random.flipCoin();

		if (baseIsRow == true) {
			int randomBase = (int) (Math.random() * rows.length);
			int randomSpanStart = (int) (Math.random() * cols.length);
			String startingRow = rows[randomBase];

			setThreeLocationCells(rows[randomBase], cols, randomSpanStart, baseIsRow);
		} else {
			int randomBase = (int) (Math.random() * cols.length);
			int randomSpanStart = (int) (Math.random() * rows.length);
			String startingCol = cols[randomBase];

			setThreeLocationCells(cols[randomBase], rows, randomSpanStart, baseIsRow);
		}
	}

	private void setThreeLocationCells(String base, String[] span, int spanStart, boolean baseIsRow) {
		int spanIdx = spanStart;

		for (int i = 0; i < 3; i++) {
			String newCell = baseIsRow ? (base + span[spanIdx]) : (span[spanIdx] + base);
			location.add(newCell);
			spanIdx = (spanStart <= 4) ? spanIdx + 1 : spanIdx - 1;
		}
	}

	public ArrayList<String> getLocation() {
		return location;
	}

	public String checkGuess(String guess) {
		String result = "Miss";

		if (!guess.equals("")) {
			for (String cell:location) {
				if (cell.equals(guess)) {
					location.remove(cell);
					result = "Hit";
					break;
				}
			}
		}
		return result;
	}

	public boolean isSunk() {
		return location.isEmpty();
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

		ArrayList<String> locationCells = new ArrayList<String>();
		locationCells.add("A0");
		locationCells.add("A1");
		locationCells.add("A2");

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

		printTest("it should reflect one hit on the startup", startup.getLocation().size() == 2);
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

		printTest("it should show the number of hits as 3", startup.getLocation().size() == 0);
		printTest("it should indicate that the startup is sunk after hitting all location cells", startup.isSunk() == true);

		Startup randomStartup = new Startup();
		randomStartup.assignRandomName();

		String[] rows = { "A", "B", "C", "D", "E", "F", "G" };
		String[] cols = { "0", "1", "2", "3", "4", "5", "6" };
		randomStartup.assignRandomLocation(rows, cols);

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

class Random {
	public static boolean flipCoin() {
		int coinFlip = (int) (Math.random() * 2);
		if (coinFlip == 1) {
			return true;
		} else {
			return false;
		}
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