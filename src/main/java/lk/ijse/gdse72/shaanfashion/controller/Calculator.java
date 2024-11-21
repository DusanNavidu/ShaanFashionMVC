package lk.ijse.gdse72.shaanfashion.controller;

import javafx.application.Application;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Calculator extends Application implements Initializable {

    private TextField display;
    private String currentOperation = "";
    private double firstOperand = 0;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Calculator");

        // Create a text field for the display
        display = new TextField();
        display.setEditable(false);
        display.setStyle("-fx-font-size: 20px; -fx-background-color: #ffffff; -fx-text-fill: #050A30; -fx-border-color: #050A30; -fx-border-width: 2px;");
        display.setAlignment(Pos.CENTER_RIGHT);

        // Create a grid pane for the buttons
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setStyle("-fx-background-color: #050A30");

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
            button.setStyle("-fx-font-size: 20px; -fx-background-color: #ff6600; -fx-text-fill: #ffffff;");
            button.setPrefWidth(60);
            button.setPrefHeight(60);
            button.setOnAction(e -> handleButtonPress(button.getText()));
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
        root.setStyle("-fx-background-color: #050A30");

        // Set up the scene and add key event listener
        Scene scene = new Scene(root, 300, 400);
        scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> handleKeyPress(event));

        primaryStage.setScene(scene);
        primaryStage.show();
    }

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

    private void handleKeyPress(KeyEvent event) {
        switch (event.getCode()) {
            case DIGIT0:
            case DIGIT1:
            case DIGIT2:
            case DIGIT3:
            case DIGIT4:
            case DIGIT5:
            case DIGIT6:
            case DIGIT7:
            case DIGIT8:
            case DIGIT9:
                handleButtonPress(String.valueOf(event.getText()));
                break;
            case PLUS: case ADD:
                handleButtonPress("+");
                break;
            case MINUS: case SUBTRACT:
                handleButtonPress("-");
                break;
            case MULTIPLY:
                handleButtonPress("*");
                break;
            case DIVIDE:
                handleButtonPress("/");
                break;
            case ENTER:
                handleButtonPress("=");
                break;
            case ESCAPE:
                handleButtonPress("C");
                break;
            default:
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialization logic if needed; currently unused
    }

    public static void main(String[] args) {
        launch(args);
    }
}