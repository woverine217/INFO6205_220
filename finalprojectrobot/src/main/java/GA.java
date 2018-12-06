import java.util.Arrays;

public class GA {
    int count=0;
	int population;
	Population pop ;
//	Individual best;

//	Individual[] best = new Individual[2500];
	
	public GA(int population){
		this.population=population;
		pop = new Population(population);
	}
	
	public GA regenerate(int num) {

		GA newGeneration = new GA(population);
		
		FitnessProportionateSelection(newGeneration, num);

		Reproduction(newGeneration);

		Mutation(newGeneration);

		return newGeneration;
	}
	
	public void FitnessProportionateSelection(GA newGeneration, int n) {
		double totalScore = 0;
		double[] selectionRate = new double[population];
		double[] wheelRate = new double[population];
		if (n < 40) {
			for (int i = 0; i < population; i++) {

				if (pop.generation[i].score > 0)
					totalScore += pop.generation[i].score;
				else
					totalScore += 0.01;
			}

			for (int i = 0; i < population; i++) {

				if (pop.generation[i].score > 0)
					selectionRate[i] = pop.generation[i].score / totalScore;
				else
					selectionRate[i] = 0.01 / totalScore;
			}
		} 
		else if (n < 100) {
			for (int i = 0; i < population; i++) {
				if (pop.generation[i].score > 0)
					totalScore += pop.generation[i].score;
				else
					totalScore += 0;
			}

			for (int i = 0; i < population; i++) {

				if (pop.generation[i].score > 0)
					selectionRate[i] = pop.generation[i].score / totalScore;
				else
					selectionRate[i] = 0;
			}
		} 
		else if (n < 150) {
			for (int i = 0; i < population; i++) {
				if (pop.generation[i].score > 10)
					totalScore += Math.pow(Math.E, pop.generation[i].score);
				else
					totalScore += 0;
			}

			for (int i = 0; i < population; i++) {

				if (pop.generation[i].score > 10)
					selectionRate[i] = Math.pow(Math.E, pop.generation[i].score) / totalScore;
				else
					selectionRate[i] = 0;
			}
		} 
		else if (n < 600) {
			for (int i = 0; i < population; i++) {
				if (pop.generation[i].score > 20)
					totalScore += pop.generation[i].score;
				else
					totalScore += 0;
			}

			for (int i = 0; i < population; i++) {

				if (pop.generation[i].score > 20)
					selectionRate[i] = pop.generation[i].score / totalScore;
				else
					selectionRate[i] = 0;
			}
		}
		else if (n < 1000) {
			for (int i = 0; i < population; i++) {
				if (pop.generation[i].score > 30)
					totalScore += pop.generation[i].score;
				else
					totalScore += 0;
			}

			for (int i = 0; i < population; i++) {

				if (pop.generation[i].score > 30)
					selectionRate[i] = pop.generation[i].score / totalScore;
				else
					selectionRate[i] = 0;
			}
		}
		else {
			for (int i = 0; i < population; i++) {
				if (pop.generation[i].score > 100)
					totalScore += pop.generation[i].score;
				else
					totalScore += 0;
			}

			for (int i = 0; i < population; i++) {

				if (pop.generation[i].score > 100)
					selectionRate[i] = pop.generation[i].score / totalScore;
				else
					selectionRate[i] = 0;
			}
		}
		for (int i = 0; i < population; i++) {
			if (i == 0)
				wheelRate[i] = selectionRate[i];
			else
				wheelRate[i] = wheelRate[i - 1] + selectionRate[i];
		}

		for (int i = 0; i < population; i++) {
			double a = Math.random();
			for (int j = 0; j < population - 1; j++) {
				if (a < wheelRate[0]) {
					newGeneration.pop.generation[i].copy(this.pop.generation[j].genoType);
					break;
				} else if (a >= wheelRate[j] && a <= wheelRate[j + 1]) {
					newGeneration.pop.generation[i].copy(this.pop.generation[j + 1].genoType);
					break;
				}
			}

		}
	}

	public void Reproduction(GA newGeneration) {
		GA duplicatGeneration = new GA(newGeneration.population);
		Individual[] gen = new Individual[newGeneration.population * 2];
//		Individual best;
		for (int i = 0; i < duplicatGeneration.population; i++) {
			duplicatGeneration.pop.generation[i].copy(newGeneration.pop.generation[i].genoType);
		}
		GA duplicatGeneration2 = new GA(newGeneration.population);
		for (int i = 0; i < duplicatGeneration2.population; i++) {
			duplicatGeneration2.pop.generation[i].copy(newGeneration.pop.generation[i].genoType);
		}
		for (int i = 0; i < newGeneration.population; i++) {
//			double a = Math.random();
			int crossWith = (int) (Math.random() * newGeneration.population);
//			while (newGeneration.pop.generation[crossWith] == newGeneration.pop.generation[i])
			while (crossWith == i)
			{
				crossWith = (int) (Math.random() * newGeneration.population);
			}
			Crossover(duplicatGeneration.pop.generation[crossWith], duplicatGeneration2.pop.generation[crossWith]);
		}
		for (int i = 0; i < population; i++) {
			gen[i] = duplicatGeneration.pop.generation[i];
			gen[i + population] = duplicatGeneration2.pop.generation[i];
		}
		Arrays.sort(gen);
		for (int i = 0; i < newGeneration.population; i++) {
			newGeneration.pop.generation[i] = gen[i];
		}
//		count++;
//
//		best=newGeneration.pop.generation[0];
	}

	public void Crossover(Individual r1, Individual r2) {
		for (int i = 1; i < r1.genoType.length / 2; i++) {
			r1.genoType[i] = r2.genoType[i];
		}
		for (int i = 1; i < r2.genoType.length / 2; i++) {
			r2.genoType[i] = r1.genoType[i];
		}
	}

	public void Mutation(GA newGeneration) {
		for (int i = 0; i < newGeneration.population; i++) {
			for (int j = 0; j < newGeneration.pop.generation[i].genoType.length; j++) {
				double random = Math.random();
				if (random < Main.MUTATIONRATE) {
					int mutateGene = (int) (Math.random() * 7);
					newGeneration.pop.generation[i].genoType[j] = mutateGene;
				}
			}
		}
	}
}
