package GraphicalUserInterface;

import HelperClasses.DataAndTrainingHelper;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class View extends Application {
	private ComboBox<Integer> clumpThickness;
	private ComboBox<Integer> uniformityOfCellSize;
	private ComboBox<Integer> uniformityOfCellShape;
	private ComboBox<Integer> marginalAdhesion;
	private ComboBox<Integer> singleEpithelialCellSize;
	private ComboBox<Integer> bareNuclei;
	private ComboBox<Integer> blandChromatin;
	private ComboBox<Integer> normalNucleoli;
	private ComboBox<Integer> mitoses;

	private Text outputText;
	final Integer[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
	DataAndTrainingHelper d;

	public void start(Stage primaryStage) throws IOException {
		d = new DataAndTrainingHelper();

		// Create the combo boxes and add the numbers to them
		clumpThickness = new ComboBox<>(FXCollections.observableArrayList(numbers));
		uniformityOfCellSize = new ComboBox<>(FXCollections.observableArrayList(numbers));
		uniformityOfCellShape = new ComboBox<>(FXCollections.observableArrayList(numbers));
		marginalAdhesion = new ComboBox<>(FXCollections.observableArrayList(numbers));
		singleEpithelialCellSize = new ComboBox<>(FXCollections.observableArrayList(numbers));
		bareNuclei = new ComboBox<>(FXCollections.observableArrayList(numbers));
		blandChromatin = new ComboBox<>(FXCollections.observableArrayList(numbers));
		normalNucleoli = new ComboBox<>(FXCollections.observableArrayList(numbers));
		mitoses = new ComboBox<>(FXCollections.observableArrayList(numbers));

		// Initialize output text field
		outputText = new Text();
		outputText.setFont(Font.font("Arial", FontWeight.NORMAL, 14));

		// Create labels for combo boxes
		Label clumpThicknessLabel = new Label("Clump Thickness:");
		Label uniformityOfCellSizeLabel = new Label("Uniformity of Cell Size:");
		Label uniformityOfCellShapeLabel = new Label("Uniformity of Cell Shape:");
		Label marginalAdhesionLabel = new Label("Marginal Adhesion:");
		Label singleEpithelialCellSizeLabel = new Label("Single Epithelial Cell Size:");
		Label bareNucleiLabel = new Label("Bare Nuclei:");
		Label blandChromatinLabel = new Label("Bland Chromatin:");
		Label normalNucleoliLabel = new Label("Normal Nucleoli:");
		Label mitosesLabel = new Label("Mitoses:");

		// Create predict button
		Button predictButton = new Button("Predict");
		predictButton.setOnAction(e -> predictResult());

		// Set button style
		predictButton.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white; -fx-font-size: 14px;");

		// Create grid pane and add components
		GridPane gridPane = new GridPane();
		gridPane.setPadding(new Insets(20));
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		gridPane.addColumn(0, clumpThicknessLabel, uniformityOfCellSizeLabel, uniformityOfCellShapeLabel,
				marginalAdhesionLabel, singleEpithelialCellSizeLabel, bareNucleiLabel,
				blandChromatinLabel, normalNucleoliLabel, mitosesLabel);
		gridPane.addColumn(1, clumpThickness, uniformityOfCellSize, uniformityOfCellShape,
				marginalAdhesion, singleEpithelialCellSize, bareNuclei, blandChromatin,
				normalNucleoli, mitoses, predictButton);

		// Set grid pane style
		gridPane.setStyle("-fx-background-color: white; -fx-border-color: #E0E0E0; -fx-border-width: 1px; -fx-border-radius: 4px;");

		// Create vertical box and add components
		VBox vBox = new VBox(20, gridPane, outputText);
		vBox.setPadding(new Insets(20));

		// Set scene style
		Scene scene = new Scene(vBox, 400, 550);

		scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
		scene.setFill(Color.web("#F5F5F5"));

		// Set stage properties
		primaryStage.setTitle("Breast Cancer Prediction");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void predictResult() {
		double[] inputs = new double[9];
		List<Double> output;
		inputs[0] = clumpThickness.getValue();
		inputs[1] = uniformityOfCellSize.getValue();
		inputs[2] = uniformityOfCellShape.getValue();
		inputs[3] = marginalAdhesion.getValue();
		inputs[4] = singleEpithelialCellSize.getValue();
		inputs[5] = bareNuclei.getValue();
		inputs[6] = blandChromatin.getValue();
		inputs[7] = normalNucleoli.getValue();
		inputs[8] = mitoses.getValue();
		output = d.getNn().predict(inputs);
		outputText.setText(output.toString());
	}

	public static void main(String[] args) {
		launch(args);
	}
}
