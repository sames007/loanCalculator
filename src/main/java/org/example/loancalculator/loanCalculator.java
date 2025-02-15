package org.example.loancalculator;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class loanCalculator extends Application {

    @Override
    public void start(Stage primaryStage) {

        // Create a GridPane to hold the components
        GridPane pane = new GridPane();

        // Create labels
        Label label_InterestRate = new Label("Annual Interest Rate:");
        Label label_Years        = new Label("Number of Years:");
        Label label_LoanAmount   = new Label("Loan Amount:");
        Label label_Monthly      = new Label("Monthly Payment:");
        Label label_Total        = new Label("Total Payment:");

        // Create text fields
        TextField textfield_InterestRate = new TextField();
        TextField textfield_Years        = new TextField();
        TextField textfield_LoanAmount   = new TextField();
        TextField textfield_Monthly      = new TextField();
        TextField textfield_Total        = new TextField();

        // Make monthly and total payment fields read-only
        textfield_Monthly.setEditable(false);
        textfield_Total.setEditable(false);

        // Create a button
        Button button_Calculate = new Button("Calculate");

        // Add components to the pane
        pane.addRow(0, label_InterestRate, textfield_InterestRate);
        pane.addRow(1, label_Years, textfield_Years);
        pane.addRow(2, label_LoanAmount, textfield_LoanAmount);
        pane.addRow(3, label_Monthly, textfield_Monthly);
        pane.addRow(4, label_Total, textfield_Total);
        pane.add(button_Calculate, 1, 5);

        // Set up the Calculate button's action (event-driven)
        button_Calculate.setOnAction(e -> {
            // 1. Get the user input from the text fields
            double annualInterestRate = Double.parseDouble(textfield_InterestRate.getText());
            int numberOfYears = Integer.parseInt(textfield_Years.getText());
            double loanAmount = Double.parseDouble(textfield_LoanAmount.getText());

            // 2. Calculate monthly interest rate
            double monthlyInterestRate = annualInterestRate / 1200;  // 12 months, so /1200

            // 3. Calculate monthly payment
            double monthlyPayment = loanAmount * monthlyInterestRate /
                    (1 - Math.pow(1 + monthlyInterestRate, -numberOfYears * 12));

            // 4. Calculate total payment
            double totalPayment = monthlyPayment * 12 * numberOfYears;

            // 5. Display results in the read-only text fields
            textfield_Monthly.setText(String.format("$%.2f", monthlyPayment));
            textfield_Total.setText(String.format("$%.2f", totalPayment));
        });

        // Create a scene, place the pane in it, and show the stage
        Scene scene = new Scene(pane, 300, 200);
        primaryStage.setTitle("Loan Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}