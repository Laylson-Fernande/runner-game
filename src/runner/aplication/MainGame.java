package runner.aplication;

import aplication.Frame;
import runner.ai.ArtificialIntelligence;
import runner.aplication.level.LevelManager;

public class MainGame {

	public static final int SLOT_SIZE = 100;
	public static final int BODY_SIZE = 50;

	private int FPS = 1000;

	public Frame windows;
	private LevelManager levelManager;

	private ArtificialIntelligence artificialIntelligence;
	private double inputs[];

	public void init() {
		windows = new Frame(1000, 200);
		levelManager = new LevelManager();
		artificialIntelligence = new ArtificialIntelligence(12, 2);
	}
	
	public void update() {
		long nextUpdate = System.currentTimeMillis();
		while (true) {
			if (nextUpdate < System.currentTimeMillis()) {
				nextUpdate = System.currentTimeMillis() + (1000 / FPS);
				windows.reset();
				if(ArtificialIntelligence.REMAINING_POPULATION <= 0) {
					levelManager.reset();
				}
				levelManager.update(windows);
				inputs = new double[12];
				inputs = levelManager.getInputs(inputs, 0);
				windows.bodyList = artificialIntelligence.predict(levelManager.getObstacles());

				if (artificialIntelligence.GENERATION >= artificialIntelligence.MAX_GENERATION) {
					//break;
				}
				
				int bestFitness = artificialIntelligence.getBestChromosome();
				String title = "Current generations: " + artificialIntelligence.GENERATION + " Size_population: " + ArtificialIntelligence.REMAINING_POPULATION;
				title += " Best Fitness: " + bestFitness;
				windows.setTitle(title);
				windows.repaint();
			}
		}
	}
}
