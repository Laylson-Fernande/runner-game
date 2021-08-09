package runner.ai;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import aplication.Body;
import aplication.Rect;
import aplication.Vector2;
import runner.aplication.level.Obstacle;

public class Population {
	private Chromosome[] chromosomes;

	public Population(int length) {
		chromosomes = new Chromosome[length];
		this.initializePopulation();

	}

	public Population initializePopulation() {
		for (int i = 0, size = chromosomes.length; i < size; i++) {
			chromosomes[i] = new Chromosome(true);
		}
		return this;
	}

	public void updateChromosomesNeural() {
		for (Chromosome chromosome : chromosomes) {
			chromosome.updateNeural();
			chromosome.setAlive(true);
			chromosome.resetFitness();
		}
	}

	public List<Rect> predict(List<Obstacle> obstacles) {
		List<Rect> output = new ArrayList<Rect>();
		for (Chromosome chromosome : chromosomes) {
			if (chromosome.isAlive()) {
				boolean survived = predict(obstacles, chromosome);
				if(survived) {
					Rect rect = chromosome.getRect();
					output.add(rect);
				}
			}
		}
		return output;
	}

	public boolean predict(List<Obstacle> obstacles, Chromosome chromosome) {
		int max = 2;
		int size = obstacles.size();
		Body body = chromosome.body;
		double[] input = new double[12];
		int index = 0;

		double[] transform = body.getTransformArray();
		for (double value : transform) {
			input[index] = value;
			index++;
		}
		for (int i = 0; i < max; i++) {
			if (i < size) {
				Obstacle obstacle = obstacles.get(i);
				transform = obstacle.getTransformArray();
				for (double value : transform) {
					input[index] = value;
					index++;
				}
			}
		}

		boolean result = false;
		int predict = chromosome.predict(input);
		if (predict != -1) {
			body.move(predict);
			if (body.position.y < 0) {
				body.position.y = 0;
				chromosome.sumFitness(-10);
			} else if ((body.position.y + body.scale.y) > 200) {
				body.position.y = 200 - body.scale.y;
				chromosome.sumFitness(-1);
			}
			for (int i = 0; i < max; i++) {
				if (i < size) {
					Obstacle obstacle = obstacles.get(i);
					if (body.collision(obstacle.bounds())) {
						chromosome.setAlive(false);
						ArtificialIntelligence.REMAINING_POPULATION--;
					} else {
						if((obstacle.getPosition().x + obstacle.getScale().x) < body.position.x) {
							chromosome.sumFitness(100);
						}
						result = true;
					}
				}
			}
		} else {
			chromosome.setAlive(false);
			chromosome.sumFitness(-100);
			ArtificialIntelligence.REMAINING_POPULATION--;
		}
		return result;
	}

	public void checkCollision(List<Obstacle> obstacles) {
		int max = 3;
		if (obstacles.size() < 3) {
			max = obstacles.size();
		}

		for (Chromosome chromosome : chromosomes) {
			Body body = chromosome.body;
			for (int i = 0; i < max; i++) {
				Obstacle obstacle = obstacles.get(i);
				if (body.collision(obstacle.bounds())) {
					chromosome.setAlive(false);
				}
			}
		}
	}

	public void sortChromosomesByFitness() {
		Arrays.sort(chromosomes);
	}

	public Chromosome[] getChromosomes() {
		return chromosomes;
	}

}
