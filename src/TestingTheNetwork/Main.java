package TestingTheNetwork;

import HelperClasses.DataAndTrainingHelper;
import HelperClasses.FileReaderHelper;
import NeuralNetworkPackage.NeuralNetwork;

import java.io.IOException;
import java.util.List;

public class Main {
	public static void main(String[] args) throws IOException {
		final int dataSetNum = 699;
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
		double[][] newInput = new double[dataSetNum - 525][9];
		for (int i = 525; i < dataSetNum; i++) {
			for (int j = 0; j < 9; j++) {
				newInput[i - 525][j] = input[i][j];
			}
		}
		double[][] newOutput = new double[dataSetNum - 525][1];
		for (int i = 525; i < dataSetNum; i++) {
			newOutput[i - 525][0] = output[i][0];
		}

		DataAndTrainingHelper dt = new DataAndTrainingHelper();
		NeuralNetwork nn = dt.getNn();
		double[] result = new double[200];
		int counter = 0;
		for (double[] d : newInput) {
			List l = nn.predict(d);
			//System.out.println(l.get(0));
			result[counter] = (double) l.get(0);
			counter++;
		}
		counter = 0;
		for (double[] d : newOutput) {
			double temp = d[0];
			if (temp == 1) {
				double temp2 = result[counter] * 100;
				counter++;
				System.out.println(temp2 + "% accuracy on data number " + (counter + 525)+" That this tumor is Malignant!");
			}
			if (temp == 0) {
				double temp2 = 1 - result[counter];
				counter++;
				System.out.println(temp2 * 100 + "% accuracy on data number " + (counter + 525)+ " That this tumor is Benign!");
			}
		}
	}
}
