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


//	public void start(Stage primaryStage) throws IOException {
//		d = new DataAndTrainingHelper();
//
//
//		// Create the combo boxes and add the numbers to them
//		clumpThickness = new ComboBox<>(FXCollections.observableArrayList(numbers));
//		uniformityOfCellSize = new ComboBox<>(FXCollections.observableArrayList(numbers));
//		uniformityOfCellShape = new ComboBox<>(FXCollections.observableArrayList(numbers));
//		marginalAdhesion = new ComboBox<>(FXCollections.observableArrayList(numbers));
//		singleEpithelialCellSize = new ComboBox<>(FXCollections.observableArrayList(numbers));
//		bareNuclei = new ComboBox<>(FXCollections.observableArrayList(numbers));
//		blandChromatin = new ComboBox<>(FXCollections.observableArrayList(numbers));
//		normalNucleoli = new ComboBox<>(FXCollections.observableArrayList(numbers));
//		mitoses = new ComboBox<>(FXCollections.observableArrayList(numbers));
//
//
//		Button button1 = new Button("Predict");
//		button1.setOnAction(e -> predictResult());
//
//		// Initialize output text field
//		outputText = new Text();
//		outputText.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
//
//		// Create grid pane
//		GridPane gridPane = new GridPane();
//		gridPane.setPadding(new Insets(20, 20, 20, 20));
//		gridPane.setHgap(10);
//		gridPane.setVgap(10);
//
//		// Add components to grid pane
//		gridPane.add(new Label("Clump Thickness:"), 0, 0);
//		gridPane.add(clumpThickness, 1, 0);
//		gridPane.add(new Label("Uniformity of Cell Size:"), 0, 1);
//		gridPane.add(uniformityOfCellSize, 1, 1);
//		gridPane.add(new Label("Uniformity of Cell Shape:"), 0, 2);
//		gridPane.add(uniformityOfCellShape, 1, 2);
//		gridPane.add(new Label("Marginal Adhesion:"), 0, 3);
//		gridPane.add(marginalAdhesion, 1, 3);
//		gridPane.add(new Label("Single Epithelial Cell Size:"), 0, 4);
//		gridPane.add(singleEpithelialCellSize, 1, 4);
//		gridPane.add(new Label("Bare Nuclei:"), 0, 5);
//		gridPane.add(bareNuclei, 1, 5);
//		gridPane.add(new Label("Bland Chromatin:"), 0, 6);
//		gridPane.add(blandChromatin, 1, 6);
//		gridPane.add(new Label("Normal Nucleoli:"), 0, 7);
//		gridPane.add(normalNucleoli, 1, 7);
//		gridPane.add(new Label("Mitoses:"), 0, 8);
//		gridPane.add(mitoses, 1, 8);
//		gridPane.add(button1, 0, 9);
//
//		// Create vertical box
//		VBox vBox = new VBox();
//		vBox.setPadding(new Insets(20, 20, 20, 20));
//		vBox.setSpacing(10);
//
//		// Add components to vertical box
//		vBox.getChildren().addAll(gridPane, outputText);
//
//		// Create scene
//		Scene scene = new Scene(vBox, 400, 450);
//
//		// Set stage properties
//		primaryStage.setTitle("Brest Cancer Prediction");
//		primaryStage.setScene(scene);
//		primaryStage.show();
//	}


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
