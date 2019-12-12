package com.company;

import java.util.ArrayList;
import java.util.List;

public class EulerMethod {

    private Function function;

    public EulerMethod(Function function) {
        this.function = function;
    }

    public double[] solver(int numberOfIterations, double h, double X0) {
        double[] solver = new double[numberOfIterations];
        double t = 0;
        double x = X0;
        solver[0] = x;
        for (int i = 1; i < solver.length; i++) {
            solver[i] = solver[i - 1] + calculateX(t, h);
            t += h;

        }
        return solver;
    }

    private double calculateX(double t, double h) {
        return function.function(t) * h;
    }
}