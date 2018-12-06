public class Individual implements Comparable<Individual> {

	public int[] position = new int[2];
	public int score = 0;
	public int[] genoType = new int[243];

	public void copy(int[] newGenoType) {
		for (int i = 0; i < genoType.length; i++) {
			this.genoType[i] = newGenoType[i];
		}
	}

	public void randomGeno() {
		for (int i = 0; i < genoType.length; i++) {
			genoType[i] = (int) (Math.random() * 7);
		}
	}

	public void randomSite(int Row, int Column) {
		position[0] = (int) (1 + Math.random() * Row);
		position[1] = (int) (1 + Math.random() * Column);
	}



	public int getGeneByPosition(int position) {
		return genoType[position];
	}

	public void move(int[][] map) {

		int[] situation = new int[5];
		situation[0] = map[position[0]][position[1]]; // Current Position
		situation[1] = map[position[0]][position[1] - 1]; // Left
		situation[2] = map[position[0] + 1][position[1]]; // Bottom
		situation[3] = map[position[0]][position[1] + 1]; // Right
		situation[4] = map[position[0] - 1][position[1]]; // Top

		int genoPosition = (int) (Math.pow(3, 4) * situation[0] + Math.pow(3, 3) * situation[1] + Math.pow(3, 2) * situation[2]
				+ Math.pow(3, 1) * situation[3] + Math.pow(3, 0) * situation[4]);

		int gene = this.getGeneByPosition(genoPosition);

		switch (gene) {
		case 0:
			randomMove(map);
			break;
		case 1:
			left(map);
			break;
		case 2:
			down(map);
			break;
		case 3:
			right(map);
			break;
		case 4:
			up(map);
			break;
		case 5: // Do nothing
			break;
		case 6:
			collectTrash(map);
			break;
		}
	}

	public void up(int[][] map) {
		if (map[position[0] - 1][position[1]] == 2)
			score -= 5;
		else
			position[0]--;
	}

	public void down(int[][] map) {
		if (map[position[0] + 1][position[1]] == 2)
			score -= 5;
		else
			position[0]++;
	}

	public void left(int[][] map) {
		if (map[position[0]][position[1] - 1] == 2)
			score -= 5;
		else
			position[1]--;
	}

	public void right(int[][] map) {
		if (map[position[0]][position[1] + 1] == 2)
			score -= 5;
		else
			position[1]++;
	}

	public void randomMove(int[][] map) {
		Double move = Math.random();
		if (move < 0.25)
			up(map);
		else if (move < 0.5)
			down(map);
		else if (move < 0.75)
			left(map);
		else if (move < 1)
			right(map);
	}

	public void collectTrash(int[][] map) {
		if (map[position[0]][position[1]] == 1) {
			score += 10;
			map[position[0]][position[1]] = 0;
		} else
			score -= 3;

	}

//	@Override
	public int compareTo(Individual o) {
		return (new Double(this.score - o.score)).intValue();
	}
}
