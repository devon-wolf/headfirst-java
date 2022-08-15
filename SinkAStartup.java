class SinkAStartup {

}

class SinkAStartupTest {

}

class Grid {
	private Cell[] cells;

	private void populateCells(int len) {
		cells = new Cell[len];
		for (int i = 0; i  < cells.length; i++) {
			cells[i] = new Cell();
		}
	}

	private void setGrid(String[] cols, String[] rows) {
		populateCells(cols.length * rows.length);
		
		int row = 0;
		int col = 0;

		for (Cell cell:cells) {
			cell.setCoordinates(rows[row], cols[col]);
			row++;

			if (row == rows.length) {
				row = 0;
				col++;
			}
		}
	}

	public Cell[] getGrid(String[] cols, String[] rows) {
		setGrid(cols, rows);
		return cells;
	} 
}

class GridTest {
	public static void main (String[] args) {
		String[] cols = {"0", "1", "2", "3", "4", "5", "6"};
		String[] rows = {"A", "B", "C", "D", "E", "F", "G"};
		Grid grid = new Grid();
		Cell[] mappedGrid = grid.getGrid(cols, rows);
		System.out.println("The grid has " + mappedGrid.length + " cells");

		System.out.println("Here are all the cells in the test grid:");
		for (int i = 0; i < mappedGrid.length; i++) {
			System.out.println(i + " Coordinates " + mappedGrid[i].getRow() + " " + mappedGrid[i].getCol());
		}
	}
}

class Cell {
	private String row;
	private String col;

	private void setRow(String newRow) {
		row = newRow;
	}

	public String getRow() {
		return row;
	}

	private void setCol(String newCol) {
		col = newCol;
	}

	public String getCol() {
		return col;
	}

	public void setCoordinates(String newRow, String newCol) {
		setRow(newRow);
		setCol(newCol);
	}

	public String[] getCoordinates() {
		String[] coordinates = {row, col};
		return coordinates;
	}
}

class CellTest {
		public static void main (String[] args) {
		Cell cell = new Cell();
		cell.setCoordinates("0", "A");
		System.out.println("Here are the test cell coordinates: " + cell.getRow() + " " + cell.getCol());
	}
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
	public static void main(String[] args) {
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
		printTest("it should return a hit", resultHit.equals(hit));

		String resultMiss = startup.checkGuess(guessWrong);
		printTest("it should return a miss", resultMiss.equals(miss));

		printTest("it should reflect one hit on the startup", startup.getNumOfHits() == 1);
		printTest("it should indicate that the startup is not sunk", startup.isSunk() == false);

		String hit2 = startup.checkGuess(guessHit2);
		printTest("it should return a hit", hit2.equals(hit));

		String dupeHit = startup.checkGuess(guessDupe);
		printTest("it should return a miss if a previously hit cell is guessed", dupeHit.equals(miss));

		String emptyMiss = startup.checkGuess(guessEmpty);
		printTest("it should return a miss when an empty string is guessed", emptyMiss.equals(miss));

		printTest("it should still show the startup as not yet sunk", startup.isSunk() == false);

		String hit3 = startup.checkGuess(guessHit3);
		printTest("it should return a hit", hit3.equals(hit));

		printTest("it should show the number of hits as 3", startup.getNumOfHits() == 3);
		printTest("it should indicate that the startup is sunk after hitting all location cells", startup.isSunk() == true);
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
		System.out.println(description + ": " + getPassOrFail(expectation));
	}
}