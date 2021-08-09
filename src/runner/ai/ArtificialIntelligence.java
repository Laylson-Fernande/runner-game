package runner.ai;

import java.util.List;

import aplication.Rect;
import runner.ai.genetic.algorithm.GeneticAlgorithm;
import runner.aplication.level.Obstacle;

public class ArtificialIntelligence {
	
	public static int NEURAL_NETWORK_INPUT = 0;
	public static int NEURAL_NETWORK_HIDDEN = 2;
	public static int NEURAL_NETWORK_OUTPUT = 0;
	
	public static int POPULATION_SIZE = 1000;
	public static final double PERCENT_ELITE_CHROMOSOMES = 0.1;  // 0.5 = 50%
	public static final double PERCENT_TOURNAMENT_SELECTION = 0.4; // 0.5 = 50%
	public static final double MUTATION_RATE = 0.25; // 0.5 = 50%
	public final int MAX_GENERATION = 100;
	public int GENERATION = 0;
	public static int REMAINING_POPULATION;
	
	private Population population;
	private GeneticAlgorithm geneticAlgorithm;
	public Chromosome bestLastGeneration;
	
	public ArtificialIntelligence(int input, int output) {
		NEURAL_NETWORK_INPUT = input;
		NEURAL_NETWORK_OUTPUT = output;
		population = new Population(POPULATION_SIZE);
		geneticAlgorithm = new GeneticAlgorithm();
		
		REMAINING_POPULATION = POPULATION_SIZE;
	}
	
	public void evolve() {
		population.sortChromosomesByFitness();
		bestLastGeneration = population.getChromosomes()[0];
		System.out.println("============================= Best of Last Generation =============================");
		System.out.println("Fitness: "+bestLastGeneration.getFitness()+"  |  Genes: " + bestLastGeneration.toString());
		population = geneticAlgorithm.evolve(population);
		REMAINING_POPULATION = POPULATION_SIZE;
		GENERATION ++;
	}
	
	public List<Rect> predict(List<Obstacle> input){
		if(REMAINING_POPULATION <= 0) {
			this.evolve();
		}
		return population.predict(input);
	}
	
	public int getBestChromosome() {
		population.sortChromosomesByFitness();
		return population.getChromosomes()[0].getFitness();
	}
	
	public void checkCollision() {
		
	}

}
