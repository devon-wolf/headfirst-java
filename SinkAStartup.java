import java.util.*;

class GameHelper {
	private static final String ALPHABET = "abcdefg";
	private static final int GRID_LENGTH = 7;
	private static final int GRID_SIZE = 49;
	private static final int MAX_ATTEMPTS = 200;
	static final int HORIZONTAL_INCREMENT = 1;
	static final int VERTICAL_INCREMENT = GRID_LENGTH;

	private final int[] grid = new int[GRID_SIZE];
	private final Random random = new Random();
	private int startupCount = 0;

	public String getUserInput(String prompt) {
		System.out.print(prompt + ": ");
		Scanner scanner = new Scanner(System.in);
		return scanner.nextLine().toLowerCase();
	}

	public ArrayList<String> placeStartup(int startupSize) {
		int[] startupCoords = new int[startupSize];
		int attempts = 0;
		boolean success = false;

		startupCount++;
		int increment = getIncrement();

		while (!success & attempts++ < MAX_ATTEMPTS) {
			int location = random.nextInt(GRID_SIZE);

			for (int i = 0; i < startupCoords.length; i++) {
				startupCoords[i] = location;
				location += increment;
			}

			if (startupFits(startupCoords, increment)) {
				success = coordsAvailable(startupCoords);
			}
		}
		savePositionToGrid(startupCoords);

		ArrayList<String> alphaCells = convertCoordsToAlphaFormat(startupCoords);
		return alphaCells;
	}

	private boolean startupFits(int[] startupCoords, int increment) {
		int finalLocation = startupCoords[startupCoords.length - 1];
		if (increment == HORIZONTAL_INCREMENT) {
			return calcRowFromIndex(startupCoords[0]) == calcRowFromIndex(finalLocation);
		} else {
			return finalLocation < GRID_SIZE;
		}
	}

	private boolean coordsAvailable(int[] startupCoords) {
		for (int coord : startupCoords) {
			if (grid[coord] != 0) {
				return false;
			}
		}
		return true;
	}

	private void savePositionToGrid(int[] startupCoords) {
		for (int index : startupCoords) {
			grid[index] = 1;
		}
	}

	private ArrayList<String> convertCoordsToAlphaFormat(int[] startupCoords) {
		ArrayList<String> alphaCells = new ArrayList<String>();
		for (int index : startupCoords) {
			String alphaCoords = getAlphaCoordsFromIndex(index);
			alphaCells.add(alphaCoords);
		}
		return alphaCells;
	}

	private String getAlphaCoordsFromIndex(int index) {
		int row = calcRowFromIndex(index);
		int column = index % GRID_LENGTH;
		String letter = ALPHABET.substring(column, column + 1);
		return letter + row;
	}

	private int calcRowFromIndex(int index) {
		return index / GRID_LENGTH;
	}

	private int getIncrement() {
		if (startupCount % 2 == 0) {
			return HORIZONTAL_INCREMENT;
		} else {
			return VERTICAL_INCREMENT;
		}
	}
}

class Game {
	private int guessCount = 0;
	private ArrayList<Startup> startups = new ArrayList<Startup>();
	private GameHelper helper = new GameHelper();

	void playGame() {
		while (!isGameOver()) {
			String guess = helper.getUserInput("Enter a cell");
			guessCount++;
			String result = "Miss";
			for (Startup startup:startups) {
				if (!startup.isSunk()) {
					result = startup.checkGuess(guess);

					if (result.equals("Hit")) {
						System.out.println(result);
						if (startup.isSunk()) {
							printSunkMessage(startup);
							startups.remove(startup);
						}
						break;
					}
				}
			}
			if (result.equals("Miss")) {
				System.out.println(result);
			}
		}

		System.out.println("You sunk everything!");
		printFinalGuessCount();
	}

	void printLocations() {
		for (Startup startup:startups) {
			System.out.println("The test location of " + startup.getName() + " is: ");
			for (String cell:startup.getLocation()) {
				System.out.print(cell + " ");
			}
			System.out.println();
		}
	}

	void printSunkMessage(Startup startup) {
		System.out.println("You sunk " + startup.getName() + "!");
	}

	void printFinalGuessCount() {
		System.out.println("The game took you " + guessCount + " guesses.");
	}

	void generateThreeStartups() {
		ArrayList<String> names = new ArrayList<String>(
			Arrays.asList(
				"blorbocorp", "mufflr", "shortmyeats", "cacophony", "pocketpal", "asdf", "waterclick"
				)
			);
		
		for (int i = 0; i < 3; i++) {
			Startup startup = new Startup();
			String randomName = names.get((int) (Math.random() * names.size()));
			startup.setName(randomName);
			names.remove(randomName);
			startup.setLocation(helper.placeStartup(3));
			startups.add(startup);
		}
	}

	boolean isGameOver() {
		return startups.isEmpty();
	}
}

class GameTest {
	public static void main(String[] args) {
		Game game = new Game();
		game.generateThreeStartups();
		game.printLocations();
		game.playGame();
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

	public void setLocation(ArrayList<String> newLocation) {
		location = newLocation;
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
		System.out.println(name);
		System.out.println("=====");

		Startup startup = new Startup();
		startup.setName("testup");

		ArrayList<String> locationCells = new ArrayList<String>(Arrays.asList("A0", "A1", "A2"));
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
		printTest("it should indicate that the startup is not sunk", !startup.isSunk());

		String hit2 = startup.checkGuess(guessHit2);
		printTest("it should return a hit on guessHit2", hit2.equals(hit));

		String dupeHit = startup.checkGuess(guessDupe);
		printTest("it should return a miss if a previously hit cell is guessed", dupeHit.equals(miss));

		String emptyMiss = startup.checkGuess(guessEmpty);
		printTest("it should return a miss when an empty string is guessed", emptyMiss.equals(miss));

		printTest("it should still show the startup as not yet sunk", !startup.isSunk());

		String hit3 = startup.checkGuess(guessHit3);
		printTest("it should return a hit on guessHit3", hit3.equals(hit));

		printTest("it should show the number of hits as 3", startup.getLocation().size() == 0);
		printTest("it should indicate that the startup is sunk after hitting all location cells", startup.isSunk());

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
		if (expectation) {
			return "PASS";
		}
		return "FAIL";
	}

	protected static void printTest(String description, boolean expectation) {
		System.out.println(getPassOrFail(expectation) + ": " + description);
	}
}