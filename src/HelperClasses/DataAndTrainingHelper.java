package HelperClasses;

import NeuralNetworkPackage.NeuralNetwork;

import java.io.IOException;

public class DataAndTrainingHelper {
	//Train the network with 75% of the dataset, 700 * 0.75 = 525
	final int dataSetNum = 525;
	final int attributeNum = 11;
	final String path = "src/DataSet/breast-cancer-wisconsin.data";
	final int numInput = 9;
	final int numOutput = 1;
	final int numHiddenLayer = 5;
	final int numTrainingLoop = 500000;
	double[][] input;
	double[][] output;
	double[][] dataSet;
	NeuralNetwork nn;
	FileReaderHelper f;

	public DataAndTrainingHelper() throws IOException {
		f = new FileReaderHelper(path, dataSetNum, attributeNum);
		dataSet = f.textToArray();
		nn = new NeuralNetwork(numInput, numHiddenLayer, numOutput);
		trainNetwork();
	}

	public double[][] getInput() {
		input = new double[dataSetNum][9];
		for (int j = 0; j < dataSetNum; j++) {
			for (int h = 0; h < 9; h++) {
				input[j][h] = dataSet[h + 1][j];
			}
		}
		return input;
	}

	public double[][] getOutput() {
		output = new double[dataSetNum][1];
		for (int i = 0; i < dataSetNum; i++) {
			if (dataSet[10][i] == 2) {
				output[i][0] = 0;
			} else if (dataSet[10][i] == 4) {
				output[i][0] = 1;
			}
		}
		return output;
	}

	public void trainNetwork() {
		nn.train_helper(getInput(), getOutput(), numTrainingLoop);
	}

	public NeuralNetwork getNn() {
		return nn;
	}
}
