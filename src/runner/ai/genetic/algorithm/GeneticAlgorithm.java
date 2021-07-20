package runner.ai.genetic.algorithm;

import runner.ai.ArtificialIntelligence;
import runner.ai.Chromosome;
import runner.ai.Population;

public class GeneticAlgorithm {
	private int NUMBER_ELITE_CHROMOSOMES;
	private int TOURNAMENT_SELECTION_SIZE;

	public GeneticAlgorithm() {
		this.NUMBER_ELITE_CHROMOSOMES = (int) ((int) ArtificialIntelligence.POPULATION_SIZE
				* ArtificialIntelligence.PERCENT_ELITE_CHROMOSOMES);
		this.TOURNAMENT_SELECTION_SIZE = (int) ((int) ArtificialIntelligence.POPULATION_SIZE
				* ArtificialIntelligence.PERCENT_TOURNAMENT_SELECTION);
	}

	public void setupGeneticAlgorithm(int populationSize, double percentElite, double percentTournament,
			double mutationRate) {

	}

	public Population evolve(Population population) {
		population = crossoverPopulation(population);
		population = mutatePopulation(population);
		population.updateChromosomesNeural();
		return population;
	}

	private Population crossoverPopulation(Population population) {
		Population crossoverPopulation = new Population(population.getChromosomes().length);
		for (int i = 0; i < NUMBER_ELITE_CHROMOSOMES; i++) {
			crossoverPopulation.getChromosomes()[i] = population.getChromosomes()[i];
		}
		for (int i = NUMBER_ELITE_CHROMOSOMES, size = population.getChromosomes().length; i < size; i++) {
			Chromosome a = selectTournamentPopulation(population).getChromosomes()[0];
			Chromosome b = selectTournamentPopulation(population).getChromosomes()[0];
			crossoverPopulation.getChromosomes()[i] = crossoverChromosome(a, b);

		}
		return crossoverPopulation;
	}

	private Population mutatePopulation(Population population) {
		Population mutatePopulation = new Population(population.getChromosomes().length);
		for (int i = 0; i < NUMBER_ELITE_CHROMOSOMES; i++) {
			mutatePopulation.getChromosomes()[i] = population.getChromosomes()[i];
		}

		for (int i = NUMBER_ELITE_CHROMOSOMES, size = population.getChromosomes().length; i < size; i++) {
			mutatePopulation.getChromosomes()[i] = mutateChromosome(population.getChromosomes()[i]);
		}
		return mutatePopulation;
	}

	private Chromosome crossoverChromosome(Chromosome a, Chromosome b) {
		Chromosome crossoverChromosome = new Chromosome(false);
		for (int i = 0, size = a.getGenes().length; i < size; i++) {
			if (Math.random() < 0.5) {
				crossoverChromosome.getGenes()[i] = a.getGenes()[i];
			} else {
				crossoverChromosome.getGenes()[i] = b.getGenes()[i];
			}
		}
		return crossoverChromosome;
	}

	private Chromosome mutateChromosome(Chromosome chromosome) {
		Chromosome mutateChromosome = new Chromosome(false);
		for (int i = 0, size = chromosome.getGenes().length; i < size; i++) {
			if (Math.random() < ArtificialIntelligence.MUTATION_RATE) {
				mutateChromosome.getGenes()[i] = (-1 + (Math.random() * 2 - 0));
			} else {
				mutateChromosome.getGenes()[i] = chromosome.getGenes()[i];
			}
		}
		return mutateChromosome;
	}

	private Population selectTournamentPopulation(Population population) {
		Population tournamentPopulation = new Population(TOURNAMENT_SELECTION_SIZE);
		int sizeChromosomes = population.getChromosomes().length;
		for (int i = 0; i < TOURNAMENT_SELECTION_SIZE; i++) {
			tournamentPopulation.getChromosomes()[i] = population
					.getChromosomes()[(int) (Math.random() * sizeChromosomes)];
		}
		tournamentPopulation.sortChromosomesByFitness();
		return tournamentPopulation;
	}

}
