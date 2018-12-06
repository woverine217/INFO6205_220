public class Population {
	public int population;
	public Individual[] generation;
	public int bestPosition;
	
	public Population(int population) {
		this.population = population;
		generation = new Individual[population];
		for (int i = 0; i < population; i++) {
			generation[i] = new Individual();
		}
	}
	
	public double getAverageScore() {
		double sum = 0;
		for (int i = 0; i < generation.length; i++) {
				sum += generation[i].score;
		}
		return sum / generation.length;
	}

	public int getBestScore() {
		int best = 0;
		for (bestPosition = 0; bestPosition < generation.length; bestPosition++) {
			if (generation[bestPosition].score > best)
				best = generation[bestPosition].score;
		}
		return best;
	}
}
