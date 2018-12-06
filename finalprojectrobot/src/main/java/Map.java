public class Map {

	public int[][] map;
	public int Row;
	public int Column;
	public int CAN;

	public void init(int Row, int Column, double proportion) {
		this.Row = Row;
		this.Column = Column;
		map = new int[Row + 2][Column + 2]; // the reason to plus 2 is to set up the wall on the top,bottom,left,right

		for (int i = 0; i < Row + 2; i++) {
			for (int j = 0; j < Row + 2; j++) {
				map[i][j] = 0;
			}
		}

// set up for walls
		for (int i = 0; i < Row + 2; i++) {
			map[0][i] = 2; // the top wall
			map[i][0] = 2; // the left wall
			map[Row + 1][i] = 2; // the right wall
			map[i][Column + 1] = 2;
		}

// place trash
		for (int i = 1; i < Row + 1; i++) {
			for (int j = 1; j < Column + 1; j++) {
				if (Math.random() < proportion) {
					map[i][j] = 1;
					CAN++;
				}
			}
		}

	}

	public void show() {
		int i, j;
		for (i = 0; i < Row + 2; i++) {
			for (j = 0; j < Row + 2; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}

	public void draw() {
		int i, j;
		for (i = 0; i < Row + 2; i++) {
			for (j = 0; j < Row + 2; j++) {
				if (map[i][j] == 2)
					System.out.print("=");
				if (map[i][j] == 1)
					System.out.print("*");
				if (map[i][j] == 0)
					System.out.print(" ");
				System.out.print(" ");
			}
			System.out.println();
		}
	}
}
