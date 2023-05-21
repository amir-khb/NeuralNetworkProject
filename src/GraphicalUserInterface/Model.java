package GraphicalUserInterface;

import HelperClasses.DataAndTrainingHelper;

import java.io.IOException;
import java.util.List;

public class Model {
	private DataAndTrainingHelper dataHelper;

	public Model() throws IOException {
		// Initialize the necessary helper classes and data structures
		dataHelper = new DataAndTrainingHelper();
	}

	public List<Double> predictResult(double[] inputs) {
		return dataHelper.getNn().predict(inputs);
	}
}
