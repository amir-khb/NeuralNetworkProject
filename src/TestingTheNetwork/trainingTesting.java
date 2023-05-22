package TestingTheNetwork;

import HelperClasses.DataAndTrainingHelper;
import HelperClasses.FileReaderHelper;
import NeuralNetworkPackage.NeuralNetwork;

import java.io.IOException;
import java.util.List;

public class trainingTesting {
	public static void main(String[] args) throws IOException {
		final int dataSetNum = 525;
		final int attributeNum = 11;
		final String path = "src/DataSet/breast-cancer-wisconsin.data";
		double[][] input;
		double[][] output;
		double[][] dataSet;
		FileReaderHelper f;
		f = new FileReaderHelper(path, dataSetNum, attributeNum);
		dataSet = f.textToArray();
		input = new double[dataSetNum][9];
		for (int j = 0; j < dataSetNum; j++) {
			for (int h = 0; h < 9; h++) {
				input[j][h] = dataSet[h + 1][j];
			}
		}
		output = new double[dataSetNum][1];
		for (int i = 0; i < dataSetNum; i++) {
			if (dataSet[10][i] == 2) {
				output[i][0] = 0;
			} else if (dataSet[10][i] == 4) {
				output[i][0] = 1;
			}
		}

		DataAndTrainingHelper dt = new DataAndTrainingHelper();
		NeuralNetwork nn = dt.getNn();
		double[] result = new double[700];
		int counter = 0;
		for (double[] d : input) {
			List l = nn.predict(d);
			result[counter] = (double) l.get(0);
			counter++;
		}
		counter = 0;
		int incorrectRes = 0;
		int[] problemVals = new int[100];
		int actualBenign = 0;
		int actualMalignant = 0;
		int falseBenign = 0;
		int falseMalignant = 0;
		for (double[] d : output) {
			double temp = d[0];
			if (temp == 1) {
				actualMalignant++;
				double temp2 = result[counter] * 100;
				counter++;
				System.out.println(temp2 + "% accuracy on data number " + (counter) + " That this tumor is Malignant!");
				if (temp2 < 70) {
					problemVals[incorrectRes] = counter;
					incorrectRes++;
				}
				if (temp2 < 30) {
					falseBenign++;
				}
			}
			if (temp == 0) {
				actualBenign++;
				double temp2 = (1 - result[counter]) * 100;
				counter++;
				System.out.println(temp2 + "% accuracy on data number " + (counter) + " That this tumor is Benign!");
				if (temp2 < 70) {
					problemVals[incorrectRes] = counter;
					incorrectRes++;
				}
				if (temp2 < 30) {
					falseMalignant++;
				}
			}
		}
		for (int a : problemVals) {
			if (a != 0)
				System.out.println("Data " + a + " has less than 70% accuracy!");
		}
		System.out.println("Number of data with less than 70% accuracy: " + incorrectRes);
		System.out.println("Actual Benign: " + actualBenign + ", Actual Malignant: " + actualMalignant);
		System.out.println("False Benign: " + falseBenign + ", False Malignant: " + falseMalignant);

	}
}
