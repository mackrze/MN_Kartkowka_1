package com.company;
import java.util.ArrayList;
public class NewtonRaphsonSolver {
    private Function function;
    public NewtonRaphsonSolver(Function function) {
        this.function = function;
    }
    public ArrayList<double[]> solver(double xL, double xU, double ea, int
            maxIter, double xR,double xi) { //start solver
        ArrayList<double[]> solver = new ArrayList<>();
        int numberOfIteration = 1;
        double[] solve = new double[6];
        solve[0] = xL;
        solve[1] = xU;
        solve[2] = calculateXR(xi);
        solve[3] = numberOfIteration;
        solve[4]=100;
        solve[5]=calculateTrueValueError(solve[2],xR);
        solver.add(solve);
        while (numberOfIteration < maxIter&&solver.get(solver.size()-1)[4]>ea) {
            solve=calculateBoundaries(solve);
            solve[2]=calculateXR(solver.get(solver.size()-1)[2]);
            numberOfIteration++;
            solve[3]=numberOfIteration;
            solve[4]=calculateaproximateError(solve[2],solver.get(solver.size()-
                    1)[2]);
            solve[5]=calculateTrueValueError(solve[2],xR);
            solver.add(solve);
        }
        return solver;
    } //end solver
    private double calculateXR(double xi) {//start calculateXR
        double xR = 0;
        double dX=0.001;
        xR = xi-(function.function(xi))/((function.function(xi+dX)-
                function.function(xi))/dX);
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
    private double calculateaproximateError(double presentApproximation,double
            previousApproximation){
        return Math.abs((presentApproximation-previousApproximation)/presentApproximation*100);
    }
    private double calculateTrueValueError(double presentApproximation,double
            trueValue){
        return Math.abs((trueValue-presentApproximation)/trueValue*100);
    }
}