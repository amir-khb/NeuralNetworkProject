package NeuralNetworkPackage;

import HelperClasses.Matrix;
import java.util.List;
import java.util.Random;

public class NeuralNetwork {

	private Matrix weights_input_hidden, weights_hidden_output, bias_hidden, bias_output;
	private Matrix input, output, hidden, gradient, error;
	private final double learning_rate = 0.01;

	public NeuralNetwork(int i, int h, int o) {
		//Create the weight matrices
		weights_input_hidden = new Matrix(h, i);
		weights_hidden_output = new Matrix(o, h);

		//Create the bias matrices
		bias_hidden = new Matrix(h, 1);
		bias_output = new Matrix(o, 1);
	}

	public List<Double> predict(double[] inputs) {
		//Converts array to a matrix2
		Matrix input = Matrix.fromArray(inputs);
		//Feed Forward
		feedForward(input);
		return output.toArray();
	}

	public void feedForward(Matrix input){
		//CALCULATE THE HIDDEN NEURONS
		hidden = Matrix.multiply(weights_input_hidden, input);
		hidden.add(bias_hidden);
		hidden.sigmoid();

		//CALCULATE THE PREDICTED OUTPUT
		output = Matrix.multiply(weights_hidden_output, hidden);
		output.add(bias_output);
		output.sigmoid();
	}
	public void backpropagation(Matrix target){
		//CALCULATE THE ERROR AND GRADIENT
		error = Matrix.subtract(target, output);
		gradient = output.dsigmoid();
		gradient.multiply(error);
		gradient.multiply(learning_rate);

		//CALCULATE THE DELTA
		Matrix hidden_transpose = Matrix.transpose(hidden);
		Matrix who_delta = Matrix.multiply(gradient, hidden_transpose);

		//ADD THE GRADIENT AND DELTA TO WEIGHT AND BIAS OF HIDDEN/OUTPUT AND OUTPUT
		weights_hidden_output.add(who_delta);
		bias_output.add(gradient);


		//CALCULATE GRADIENT AND DELTA FOR INPUT/HIDDEN LAYER
		Matrix who_T = Matrix.transpose(weights_hidden_output);
		Matrix hidden_errors = Matrix.multiply(who_T, error);

		Matrix h_gradient = hidden.dsigmoid();
		h_gradient.multiply(hidden_errors);
		h_gradient.multiply(learning_rate);

		Matrix i_T = Matrix.transpose(input);
		Matrix wih_delta = Matrix.multiply(h_gradient, i_T);

		weights_input_hidden.add(wih_delta);
		bias_hidden.add(h_gradient);
	}

	public void train(double[] inputs, double[] outputs) {
		input = Matrix.fromArray(inputs);
		feedForward(input);
		Matrix target = Matrix.fromArray(outputs);
		backpropagation(target);
	}

	public void train_helper(double[][] inputs, double[][] outputs, int epochs) {
		for (int i = 0; i < epochs; i++) {
			Random r = new Random();
			int randomSample = (int) (r.nextDouble() * inputs.length);
			this.train(inputs[randomSample], outputs[randomSample]);
		}
	}
}