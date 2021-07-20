package runner.ai.neural.network;

import java.util.Arrays;
import java.util.List;

public class NeuralNetwork {
	
	//The weights matrix for the input and hidden layer
	Matrix weights_ih;
	// The weights matrix for the hidden and output layer
	Matrix weights_ho;
	// The bias matrix for the hidden layer
	Matrix bias_h;
	// The bias matrix for the output layer
	Matrix bias_o;
	// The learning rate, a hyper-parameter used to control
	// the learning steps during optimization of weights
	double learningRate = 0.01;

	public NeuralNetwork(int input, int hidden, int output, boolean initialize) {
		this.weights_ih = new Matrix(hidden, input, initialize);
		this.weights_ho = new Matrix(output, hidden, initialize);

		this.bias_h = new Matrix(hidden, 1, initialize);
		this.bias_o = new Matrix(output, 1, initialize);
	}

	public List<Double> predict(double[] array) {
		Matrix input = Matrix.fromArray(array);
		Matrix hidden = Matrix.multiply(weights_ih, input);
		hidden.add(bias_h);
		hidden.sigmoid();

		Matrix output = Matrix.multiply(weights_ho, hidden);
		output.add(bias_o);
		output.sigmoid();

		return output.toArray();
	}

	public double[] getGenes() {
		int sizeGenes = weights_ih.rows * weights_ih.cols;
		sizeGenes += weights_ho.rows * weights_ho.cols;
		sizeGenes += bias_h.rows * bias_h.cols;
		sizeGenes += bias_o.rows * bias_o.cols;
		double[] genes = new double[sizeGenes];
		double[] tempArray = weights_ih.dataToArray();
		int genesIndex = 0;
		for(int i = 0, size = tempArray.length; i < size; i++, genesIndex++) {
			genes[genesIndex] = tempArray[i];
		}
		tempArray = weights_ho.dataToArray();
		for(int i = 0, size = tempArray.length; i < size; i++, genesIndex++) {
			genes[genesIndex] = tempArray[i];
		}
		tempArray = bias_h.dataToArray();
		for(int i = 0, size = tempArray.length; i < size; i++, genesIndex++) {
			genes[genesIndex] = tempArray[i];
		}
		tempArray = bias_o.dataToArray();
		for(int i = 0, size = tempArray.length; i < size; i++, genesIndex++) {
			genes[genesIndex] = tempArray[i];
		}
		return genes;
	}
	
	public void setGenes(double[] genes) {
		int genesIndex = 0;
		int size = weights_ih.rows * weights_ih.cols;
		double[] temp = Arrays.copyOfRange(genes, genesIndex, size);
		weights_ih.arrayToData(temp);
		
		genesIndex = size;
		size += weights_ho.rows * weights_ho.cols;
		temp = Arrays.copyOfRange(genes, genesIndex, size);
		weights_ho.arrayToData(temp);
		
		genesIndex = size;
		size += bias_h.rows * bias_h.cols;
		temp = Arrays.copyOfRange(genes, genesIndex, size);
		bias_h.arrayToData(temp);
		
		genesIndex = size;
		size += bias_o.rows * bias_o.cols;
		temp = Arrays.copyOfRange(genes, genesIndex, size);
		bias_o.arrayToData(temp);
	}
	
	public double[] addValueInArray (double[] array, int currentInit) {
		return array;
	}

}
