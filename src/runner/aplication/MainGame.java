package runner.aplication;

import java.awt.Color;
import java.util.Random;

import aplication.Frame;
import aplication.LineShape;
import aplication.RectShape;
import runner.ai.ArtificialIntelligence;

public class MainGame {

	public static final int HORIZONTAL_SLOTS = 10;
	public static final int VERTICAL_SLOTS = 3;
	public static final int SLOT_SIZE = 100;
	public static final int BODY_SIZE = 50;
	private int FPS = 15;

	public Frame windows;

	private int obstacle[][];
	private double inputs[];

	private ArtificialIntelligence artificialIntelligence;

	public void init() {
		windows = new Frame(SLOT_SIZE * HORIZONTAL_SLOTS, SLOT_SIZE * VERTICAL_SLOTS);

		for (int i = 0; i < HORIZONTAL_SLOTS; i++) {
			int x = SLOT_SIZE * i;
			int y2 = SLOT_SIZE * VERTICAL_SLOTS;
			LineShape line = new LineShape(x, 0, x, y2, Color.BLACK);
			windows.lineList.add(line);
		}
		for (int i = 0; i < VERTICAL_SLOTS; i++) {
			int y = SLOT_SIZE * i;
			int x2 = SLOT_SIZE * HORIZONTAL_SLOTS;
			LineShape line = new LineShape(0, y, x2, y, Color.BLACK);
			windows.lineList.add(line);
		}

		artificialIntelligence = new ArtificialIntelligence(VERTICAL_SLOTS, VERTICAL_SLOTS);
		obstacle = new int[HORIZONTAL_SLOTS + 2][VERTICAL_SLOTS];
		inputs = new double[VERTICAL_SLOTS];
		update();
	}

	private void update() {
		long nextUpdate = System.currentTimeMillis();
		while (true) {
			if (nextUpdate < System.currentTimeMillis()) {
				nextUpdate = System.currentTimeMillis() + (1000 / FPS);
				windows.rectList.clear();
				//windows.bodyList.clear();
				updateObstacle();
				windows.bodyList = artificialIntelligence.predict(inputs);
				
				if(artificialIntelligence.GENERATION >= artificialIntelligence.MAX_GENERATION) {
					break;
				}
				
				int bestFitness = artificialIntelligence.getBestChromosome();
				String title = "Current generations: " + artificialIntelligence.GENERATION + " Size_population: " + ArtificialIntelligence.REMAINING_POPULATION;
				title += " Best Fitness: " + bestFitness;
				if(bestFitness > 200) {
					FPS = 2;
				}
				windows.setTitle(title);
				windows.repaint();
			}
		}
	}

	public void updateObstacle() {
		Random random = new Random();
		for (int i = 1; i < HORIZONTAL_SLOTS + 2; i++) {
			for (int j = 0; j < VERTICAL_SLOTS; j++) {
				obstacle[i - 1][j] = obstacle[i][j];
			}
		}

		int soma = 0;
		for (int i = 0; i < VERTICAL_SLOTS; i++) {
			soma += obstacle[HORIZONTAL_SLOTS - 2][i];
		}

		if (soma == 0) {
			int index = random.nextInt(VERTICAL_SLOTS);
			obstacle[HORIZONTAL_SLOTS - 1][index] = 1;
		}

		for (int i = 1; i < HORIZONTAL_SLOTS + 2; i++) {
			for (int j = 0; j < VERTICAL_SLOTS; j++) {
				int current_obstacle = obstacle[i][j];
				if (current_obstacle == 1) {
					int x = (i - 1) * SLOT_SIZE;
					int y = j * SLOT_SIZE;
					RectShape rect = new RectShape(x, y, SLOT_SIZE, SLOT_SIZE, Color.black);
					windows.rectList.add(rect);
				}
			}
		}
		for (int i = 0; i < VERTICAL_SLOTS; i++) {
			inputs[i] = obstacle[1][i];
			// inputsDouble[i] = (double) obstacle[2][i];
		}
	}

}
