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
	private Cell[] location;
	private int hitCount;

	public void setName(String newName) {
		name = newName;
	}

	public String getName() {
		return name;
	}

	public void setLocation(Cell[] newLocation) {
		location = newLocation;
	}

	public Cell[] getLocation() {
		return location;
	}

	public void incrementHits() {
		hitCount++;
	}

	public int getHitCount() {
		return hitCount;
	}

	public boolean checkGuess(Cell guess) {
		boolean result = false;
		for (Cell cell:location) {
			if (guess.getRow().equals(cell.getRow()) && guess.getCol().equals(cell.getCol())) {
				hitCount++;
				cell.setCoordinates(null, null);
				result = true;
			}
		}
		return result;
	}

}

class StartupTest {
	public static void main (String[] args) {
		Startup startup = new Startup();
		startup.setName("teststart");

		Cell[] location = new Cell[3];
		for (int i = 0; i < location.length; i++) {
			location[i] = new Cell();
		}
		location[0].setCoordinates("A", "0");
		location[1].setCoordinates("A", "1");
		location[2].setCoordinates("A", "2");

		startup.setLocation(location);
		System.out.println("Here is the location of this startup:");
		for (int j = 0; j < location.length; j++) {
			System.out.println(location[j].getRow() + " " + location[j].getCol());
		}

		Cell guessHit = new Cell();
		guessHit.setCoordinates("A", "0");

		Cell guessMiss = new Cell();
		guessMiss.setCoordinates("B", "1");

		boolean shouldHit = startup.checkGuess(guessHit);
		System.out.println("This should hit (and say true): " + shouldHit);

		boolean shouldMiss = startup.checkGuess(guessMiss);
		System.out.println("This should miss (and say false): " + shouldMiss);

		System.out.println("Hits should now be 1: " + startup.getHitCount());
		System.out.println("And the hit cell should now have null coordinates: " + startup.getLocation()[0].getRow() + " " + startup.getLocation()[0].getCol());

	}
}