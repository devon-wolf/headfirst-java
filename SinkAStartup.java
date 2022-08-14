class SinkAStartup {

}

class SinkAStartupTest {
	public static void main (String[] args) {
		Cell cell = new Cell();
		cell.setCoordinates("0", "A");
		System.out.println("Here are the test cell coordinates: " + cell.getRow() + " " + cell.getCol());

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
		
		int i = 0;
		int row = 0;
		int col = 0;

		while (i < cells.length) {
			cells[i].setCoordinates(rows[row], cols[col]);
			row++;

			if (row == rows.length) {
				row = 0;
				col++;
			}

			i++;
		}
	}

	public Cell[] getGrid(String[] cols, String[] rows) {
		setGrid(cols, rows);
		return cells;
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

class Startup {
	private String name;
	private Cell[] location;
}