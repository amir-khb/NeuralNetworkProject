package GraphicalUserInterface;


import java.text.DecimalFormat;
import java.util.List;

public class Controller {
	private View view;
	private Model model;

	public Controller(View view, Model model) {
		this.view = view;
		this.model = model;
		view.setController(this);
	}

	public void predictResult() {
		double[] inputs = new double[9];
		List<Double> output;
		if (view.clumpThickness.getValue() == null || view.uniformityOfCellSize.getValue() == null ||
				view.uniformityOfCellShape.getValue() == null || view.marginalAdhesion.getValue() == null ||
				view.singleEpithelialCellSize.getValue() == null || view.bareNuclei.getValue() == null ||
				view.blandChromatin.getValue() == null || view.normalNucleoli.getValue() == null ||
				view.mitoses.getValue() == null) {
			view.outputText.setText("Please insert all the values!");
			return;
		}
		inputs[0] = view.clumpThickness.getValue();
		inputs[1] = view.uniformityOfCellSize.getValue();
		inputs[2] = view.uniformityOfCellShape.getValue();
		inputs[3] = view.marginalAdhesion.getValue();
		inputs[4] = view.singleEpithelialCellSize.getValue();
		inputs[5] = view.bareNuclei.getValue();
		inputs[6] = view.blandChromatin.getValue();
		inputs[7] = view.normalNucleoli.getValue();
		inputs[8] = view.mitoses.getValue();
		output = model.predictResult(inputs);
		printResult(output);
	}

	public void printResult(List<Double> output) {
		double accuracy = 0;
		DecimalFormat decimalFormat = new DecimalFormat("#.##");

		if (output.get(0) < 0.3) {
			accuracy = 100 - (output.get(0) * 100);
			view.outputText.setText("Tumor is Benign with " + decimalFormat.format(accuracy) + "% accuracy");
		} else if (output.get(0) > 0.7) {
			accuracy = output.get(0) * 100;
			view.outputText.setText("Tumor is Malignant with " + decimalFormat.format(accuracy) + "% accuracy");
		} else {
			view.outputText.setText("The network cannot predict the result of this tumor. \nThe predicted output is: " + decimalFormat.format(output.get(0)));
		}
	}
}
