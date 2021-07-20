package runner.ai;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import aplication.RectShape;

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
		for(Chromosome chromosome : chromosomes) {
			chromosome.updateNeural();
			chromosome.setAlive(true);
			chromosome.resetFitness();
		}
	}
	
	public List<RectShape> predict(double[] input){
		List<RectShape> output = new ArrayList<RectShape>();
		for(Chromosome chromosom: chromosomes) {
			if(chromosom.isAlive()) {
				int predict = chromosom.predict(input);
				if(predict != -1) {
					if(input[predict] == 0.0) {
						RectShape rect = new RectShape(predict, chromosom.getColor());
						output.add(rect);
						chromosom.sumFitness(1);
					} else {
						chromosom.setAlive(false);
						ArtificialIntelligence.REMAINING_POPULATION--;
					}
				} else {
					chromosom.setAlive(false);
					chromosom.sumFitness(-1);
					ArtificialIntelligence.REMAINING_POPULATION--;
				}
			}
		}
		return output;
	}

	public void sortChromosomesByFitness() {
		Arrays.sort(chromosomes);
	}
	
	public Chromosome[] getChromosomes() {
		return chromosomes;
	}

}
