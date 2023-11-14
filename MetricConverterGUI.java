package assignment_13;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MetricConverterGUI {

	private TextField valueField;
    private ComboBox<String> sourceUnitCombo;
    private ComboBox<String> targetUnitCombo;
    private Label resultLabel;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Metric Converter");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20, 20, 20, 20));
        grid.setVgap(10);
        grid.setHgap(10);

        // Value input
        Label valueLabel = new Label("Value:");
        valueField = new TextField();
        grid.add(valueLabel, 0, 0);
        grid.add(valueField, 1, 0);

        // Source unit selection
        Label sourceUnitLabel = new Label("Source Unit:");
        sourceUnitCombo = new ComboBox<>();
        sourceUnitCombo.getItems().addAll("kg", "gram", "km", "mm");
        sourceUnitCombo.setValue("kg");
        grid.add(sourceUnitLabel, 0, 1);
        grid.add(sourceUnitCombo, 1, 1);

        // Target unit selection
        Label targetUnitLabel = new Label("Target Unit:");
        targetUnitCombo = new ComboBox<>();
        targetUnitCombo.getItems().addAll("lb", "ounces", "mile", "inch");
        targetUnitCombo.setValue("lb");
        grid.add(targetUnitLabel, 0, 2);
        grid.add(targetUnitCombo, 1, 2);

        // Convert button
        Button convertButton = new Button("Convert");
        convertButton.setOnAction(e -> convert());
        grid.add(convertButton, 1, 3);

        // Result label
        resultLabel = new Label("");
        grid.add(resultLabel, 1, 4);

        Scene scene = new Scene(grid, 300, 200);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    private void convert() {
        try {
            double value = Double.parseDouble(valueField.getText());
            String sourceUnit = sourceUnitCombo.getValue();
            String targetUnit = targetUnitCombo.getValue();

            double result = convert(value, sourceUnit, targetUnit);

            resultLabel.setText(value + " " + sourceUnit + " = " + result + " " + targetUnit);
        } catch (NumberFormatException e) {
            resultLabel.setText("Invalid input. Please enter a valid number.");
        }
    }

    private double convert(double value, String sourceUnit, String targetUnit) {
        // Conversion logic goes here
        switch (sourceUnit) {
            case "kg":
                return kgToLb(value, targetUnit);
            case "gram":
                return gramToOunces(value, targetUnit);
            case "km":
                return kmToMile(value, targetUnit);
            case "mm":
                return mmToInch(value, targetUnit);
            default:
                return 0.0; // Invalid unit
        }
    }

    private double kgToLb(double kg, String targetUnit) {
        if (targetUnit.equals("lb")) {
            return kg * 2.20462;
        }
        return 0.0; // Invalid target unit
    }

    private double gramToOunces(double gram, String targetUnit) {
        if (targetUnit.equals("ounces")) {
            return gram * 0.03527396;
        }
        return 0.0; // Invalid target unit
    }

    private double kmToMile(double km, String targetUnit) {
        if (targetUnit.equals("mile")) {
            return km * 0.621371;
        }
        return 0.0; // Invalid target unit
    }

    private double mmToInch(double mm, String targetUnit) {
        if (targetUnit.equals("inch")) {
            return mm * 0.0393701;
        }
        return 0.0; // Invalid target unit
    }
}
