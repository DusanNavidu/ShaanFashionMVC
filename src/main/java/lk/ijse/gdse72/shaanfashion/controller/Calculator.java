package lk.ijse.gdse72.shaanfashion.controller;

import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.awt.*;

public class Calculator implements Initializable {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Calculator");

        // Create a text field for the display
        display = new TextField();
        display.setEditable(false);
        display.setStyle("-fx-font-size: 20px;");
        display.setAlignment(Pos.CENTER_RIGHT);

        // Create a grid pane for the buttons
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        // Buttons for numbers and operations
        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", "C", "=", "+"
        };

        // Add buttons to the grid pane
        int row = 1, col = 0;
        for (String buttonLabel : buttons) {
            Button button = new Button(buttonLabel);
            button.setStyle("-fx-font-size: 20px;");
            button.setPrefWidth(60);
            button.setPrefHeight(60);
            button.setOnAction(e -> handleButtonPress(buttonLabel));
            gridPane.add(button, col, row);
            col++;
            if (col > 3) {
                col = 0;
                row++;
            }
        }

        // Combine display and grid into a vertical layout
        GridPane root = new GridPane();
        root.setPadding(new Insets(10));
        root.setVgap(10);
        root.add(display, 0, 0);
        root.add(gridPane, 0, 1);

        // Set the scene
        primaryStage.setScene(new Scene(root, 300, 400));
        primaryStage.show();
    }

    private String currentOperation = "";
    private double firstOperand = 0;

    private void handleButtonPress(String label) {
        switch (label) {
            case "C":
                display.clear();
                currentOperation = "";
                firstOperand = 0;
                break;
            case "=":
                if (!currentOperation.isEmpty()) {
                    double secondOperand = Double.parseDouble(display.getText());
                    double result = calculateResult(firstOperand, secondOperand, currentOperation);
                    display.setText(String.valueOf(result));
                    currentOperation = "";
                }
                break;
            case "+":
            case "-":
            case "*":
            case "/":
                currentOperation = label;
                firstOperand = Double.parseDouble(display.getText());
                display.clear();
                break;
            default:
                display.appendText(label);
                break;
        }
    }

    private double calculateResult(double operand1, double operand2, String operation) {
        return switch (operation) {
            case "+" -> operand1 + operand2;
            case "-" -> operand1 - operand2;
            case "*" -> operand1 * operand2;
            case "/" -> operand1 / operand2;
            default -> 0;
        };
    }
}
