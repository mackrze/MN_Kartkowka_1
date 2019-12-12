package com.company;
import java.util.ArrayList;
public class TransversalSolver {
    private Function function;
    public TransversalSolver(Function function) {
        this.function = function;
    }
    public ArrayList<double[]> solver(double xL, double xU, double ea, int
            maxIter, double xR, double xi, double xi_1) { //start solver
        ArrayList<double[]> solver = new ArrayList<>();
        int numberOfIteration = 1;
        double[] solve = new double[6];
        solve[0] = xL;
        solve[1] = xU;
        solve[2] = calculateXR(xi,xi_1);
        solve[3] = numberOfIteration;
        solve[4] = 100;
        solve[5] = calculateTrueValueError(solve[2], xR);
        solver.add(solve);
        while (numberOfIteration < maxIter && solver.get(solver.size() - 1)[4] >
                ea) {
            solve = calculateBoundaries(solve);
            if (solver.size() > 2)
                solve[2] = calculateXR(solver.get(solver.size() - 1)[2],
                        solver.get(solver.size() - 2)[2]);
            else
                solve[2] = calculateXR(solver.get(solver.size() - 1)[2], xi);
            numberOfIteration++;
            solve[3] = numberOfIteration;
            solve[4] = calculateaproximateError(solve[2], solver.get(solver.size()
                    - 1)[2]);
            solve[5] = calculateTrueValueError(solve[2], xR);
            solver.add(solve);
        }
        return solver;
    } //end solver
    private double calculateXR(double xi, double xi_1) {//start calculateXR
        double xR = 0;
        xR = xi - (function.function(xi) * (xi_1 - xi)) / (function.function(xi_1)
                - function.function(xi));
        return xR;
    } //end calculateXR
    private double[] calculateBoundaries(double[] solve) { //start calculateBoundaries
        if (function.function(solve[0]) * function.function(solve[2]) < 0) {
            double countBoundaries[] = new double[solve.length];
            countBoundaries[0] = solve[0];
            countBoundaries[1] = solve[2];
            return countBoundaries;
        } else {
            double countBoundaries[] = new double[solve.length];
            countBoundaries[0] = solve[2];
            countBoundaries[1] = solve[1];
            return countBoundaries;
        }
    } //end calculateBoundaries
    private double calculateaproximateError(double presentApproximation, double
            previousApproximation) {
        return Math.abs((presentApproximation - previousApproximation) /
                presentApproximation * 100);
    }
    private double calculateTrueValueError(double presentApproximation, double
            trueValue) {
        return Math.abs((trueValue - presentApproximation) / trueValue * 100);
    }
}