package GraphicalUserInterface;


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
		view.outputText.setText(output.toString());
	}
}
