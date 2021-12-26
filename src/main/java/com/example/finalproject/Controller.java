package com.example.finalproject;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller {


    @FXML
    private TextField quadraticEquation;

    @FXML
    private TextArea output;

    @FXML
    void solve(MouseEvent event) {
        List<Double> coefficients = findCoefficients(quadraticEquation.getText());

        if(coefficients != null){
            List<Double> solutions = solve(coefficients);

            if(solutions.size() == 1){
                output.setText("No solutions, d= " + solutions.get(0));
            } else if(solutions.size() == 2){
                output.setText("1 solution, d= " + solutions.get(1)
                        + "x = " + solutions.get(1));
            } else if(solutions.size() == 3){
                output.setText("2 solutions, d=" + solutions.get(1)
                        + ", x1=" + solutions.get(1) + " and x2=" + solutions.get(2));
            }
        } else {
            output.setText("Input not valid use the form: ax^2+bx+c");
            quadraticEquation.setText("ax^2+bx+c");
        }
    }


    private List<Double> findCoefficients(String equation){
        List<Double> matches = new ArrayList<>();
        String regex = "(-?\\d+)x\\^2([+-]\\d+)x([+-]\\d+)";

        Matcher m = Pattern.compile(regex).matcher(equation);
        if(m.matches()) {
            for (int i = 1; i < m.groupCount()+1; i++) {
                matches.add(Double.parseDouble(m.group(i)));
            }
            return matches;
        }
        return null;
    }

    private ArrayList<Double> solve(List<Double> coefficients){
        double a = coefficients.get(0);
        double b = coefficients.get(1);
        double c = coefficients.get(2);

        ArrayList<Double> solutions = new ArrayList<>();

        double d = Math.pow(b,2) - (4 * a * c);

        solutions.add((double) Math.round(d*100)/100);


        if(d == 0){
            double x1 = (-b) / (2 * a);
            solutions.add((double) Math.round(x1*100)/100);
        }else if (d > 0){
            double x1 = ((-b) + Math.sqrt(d)) / (2 * a);
            solutions.add((double) Math.round(x1*100)/100);

            double x2 = ((-b) - Math.sqrt(d)) / (2 * a);
            solutions.add((double) Math.round(x2*100)/100);
        }
        return solutions;
    }
}