package NeuralNetworkPackage;

import HelperClasses.Matrix;

import java.util.List;

public class NeuralNetwork {
	/**
	 * weights_ih: The weights matrix for the input and hidden layer.
	 * weights_ho: The weights matrix for the hidden and output layer.
	 * bias_h: The bias matrix for the hidden layer.
	 * bias_o: The bias matrix for the output layer.
	 * l_rate: The learning rate, a hyper-parameter used to control the learning steps during optimization of weights.
	 */
	Matrix weights_ih, weights_ho, bias_h, bias_o;
	double l_rate = 0.01;


	/**
	 * CONSTRUCTOR
	 */
	public NeuralNetwork(int i, int h, int o) {
		weights_ih = new Matrix(h, i);
		weights_ho = new Matrix(o, h);

		bias_h = new Matrix(h, 1);
		bias_o = new Matrix(o, 1);
	}

	public List<Double> predict(double[] X) {
		Matrix input = Matrix.fromArray(X);
		Matrix hidden = Matrix.multiply(weights_ih, input);
		hidden.add(bias_h);
		hidden.sigmoid();

		Matrix output = Matrix.multiply(weights_ho, hidden);
		output.add(bias_o);
		output.sigmoid();

		return output.toArray();
	}

	public void train(double[] X, double[] Y) {

		//CALCULATE THE HIDDEN NEURONS

		Matrix input = Matrix.fromArray(X);
		Matrix hidden = Matrix.multiply(weights_ih, input);
		hidden.add(bias_h);
		hidden.sigmoid();

		//CALCULATE THE PREDICTED OUTPUT

		Matrix output = Matrix.multiply(weights_ho, hidden);
		output.add(bias_o);
		output.sigmoid();

		Matrix target = Matrix.fromArray(Y);

		//CALCULATE THE ERROR AND GRADIENT

		Matrix error = Matrix.subtract(target, output);
		Matrix gradient = output.dsigmoid();
		gradient.multiply(error);
		gradient.multiply(l_rate);

		//CALCULATE THE DELTA

		Matrix hidden_T = Matrix.transpose(hidden);
		Matrix who_delta = Matrix.multiply(gradient, hidden_T);

		//ADD THE GRADIENT AND DELTA TO WEIGHT AND BIAS OF HIDDEN/OUTPUT AND OUTPUT

		weights_ho.add(who_delta);
		bias_o.add(gradient);


		//CALCULATE GRADIENT AND DELTA FOR INPUT/HIDDEN LAYER

		Matrix who_T = Matrix.transpose(weights_ho);
		Matrix hidden_errors = Matrix.multiply(who_T, error);

		Matrix h_gradient = hidden.dsigmoid();
		h_gradient.multiply(hidden_errors);
		h_gradient.multiply(l_rate);

		Matrix i_T = Matrix.transpose(input);
		Matrix wih_delta = Matrix.multiply(h_gradient, i_T);

		weights_ih.add(wih_delta);
		bias_h.add(h_gradient);
	}

	public void fit(double[][] X, double[][] Y, int epochs) {
		for (int i = 0; i < epochs; i++) {
			int sampleN = (int) (Math.random() * X.length);
			this.train(X[sampleN], Y[sampleN]);
		}
	}
}