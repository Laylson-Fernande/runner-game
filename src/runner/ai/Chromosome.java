package runner.ai;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;

import runner.ai.neural.network.NeuralNetwork;

public class Chromosome implements Comparable<Chromosome>{
	public static int size;
	private int fitness = 0;
	private double[] genes;
	
	private NeuralNetwork neural;
	private boolean isAlive = true;
	private Color color;
	
	public Chromosome(boolean initialize) {
		this.neural = new NeuralNetwork(ArtificialIntelligence.NEURAL_NETWORK_INPUT, ArtificialIntelligence.NEURAL_NETWORK_HIDDEN, ArtificialIntelligence.NEURAL_NETWORK_OUTPUT, initialize);
		if(initialize) {
			this.genes = neural.getGenes();
			Chromosome.size = this.genes.length;
		} else {
			this.genes = new double[Chromosome.size];
		}
		
		int r = (int) (Math.random() * 256 - 0);
		int g = (int) (Math.random() * 256 - 0);
		int b = (int) (Math.random() * 256 - 0);
		this.setColor(new Color(r, g, b));
	}
	
	public int predict(double[] input){
		List<Double> result = neural.predict(input);
		int output = -1;
		int sum = 0;
		for(int i = 0, size = result.size(); i < size; i++) {
			int value = (int) Math.round(result.get(i));
			if(value > 0) {
				output = i;
				sum++;
			}
		}
		if(sum != 1) {
			output = -1;
		}
		return output;
	}
	
	public String toString() {
		return Arrays.toString(this.genes);
	}
	
	public double[] getGenes() {
		return genes;
	}
	
	public void updateNeural() {
		neural.setGenes(genes);
	}
	
	public int getFitness() {
		return fitness;
	}

	public void sumFitness(int sum) {
		this.fitness += sum;
	}
	
	public void resetFitness() {
		this.fitness = 0;
	}
	@Override
	public int compareTo(Chromosome arg) {
		return arg.getFitness() - this.getFitness();
	}

	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
}
