import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;

class Main {

	public static int generationNumber = 2500;
	public static int ROW = 10;
	public static int COL = 10;
	public static double CAN = 0.5;
	public static int POPULATION = 100000;
	public static int operation = 100;
	public static double MUTATIONRATE = 0.005;
	public static int[] topScore = new int[generationNumber];

	public static void main(String[] args) {
		XYSeries series = new XYSeries("my result");
		// Initialize map
		Map a = new Map();
		a.init(ROW, COL, CAN);

		// Initialize first generation
		GA originalGeneration = new GA(POPULATION);
		for (int i = 0; i < POPULATION; i++) {
			originalGeneration.pop.generation[i].randomSite(ROW, COL);
			originalGeneration.pop.generation[i].randomGeno();
		}

		for (int counter = 0; counter < generationNumber; counter++) {// each Generation
			a.CAN = 0;
			for (int population = 0; population < POPULATION; population++) { // each Robot
				originalGeneration.pop.generation[population].randomSite(ROW, COL);
				a.init(ROW, COL, CAN);
				for (int move = 0; move < operation; move++) {
					originalGeneration.pop.generation[population].move(a.map);
				}
			}
			topScore[counter] = originalGeneration.pop.getBestScore();
			System.out.println("Generation: " + counter + " Top Score: " + originalGeneration.pop.getBestScore()
					+ " Average Score: " + originalGeneration.pop.getAverageScore());
			for(int x=0;x<counter;x++)
			series.add( counter,originalGeneration.pop.getBestScore());
			originalGeneration = originalGeneration.regenerate(counter);
		}
		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(series);
		JFreeChart chart = ChartFactory.createXYLineChart(
				"Evolution Graph", // chart title
				"Generation", // x axis label
				"Best Score", // y axis label
				dataset, // data
				PlotOrientation.VERTICAL,
				true, // include legend
				false, // tooltips
				false // urls
		);

		ChartFrame frame = new ChartFrame("my picture", chart);
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
//		System.out.println(originalGeneration.best);
//		System.out.println(originalGeneration.pop.generation[originalGeneration.pop.bestPosition]);
		for(int i :originalGeneration.pop.generation[originalGeneration.pop.bestPosition].genoType){
			System.out.print( i );
		}
	}

}
