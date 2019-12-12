package com.company;
import java.util.ArrayList;
/**
 * @author mackr
 * @version 1.0
 * Class which method find root of function by bisection method
 */
public class BisectionSolver {
    private Function function;
    /**
     * Constructor
     * @param function
     */
    public BisectionSolver(Function function) {
        this.function = function;
    }
    /**
     * Main public method which find root of function and true value error and
     approximate error
     * @param xL lower border
     * @param xU upper border
     * @param ea value of approximate error: if error is lower than that method
    stops
     * @param maxIter max number of iteration: if number of iteration is higher
    than that method stops
     * @param xR true value of root
     * @return ArrayList of double[]. In double [] are: xL,xU,xR,number of
    iteration, approximate error, true value error
     */
    public ArrayList<double[]> solver(double xL, double xU, double ea, int
            maxIter, double xR) { //start solver
        ArrayList<double[]> solver = new ArrayList<>();
        int numberOfIteration = 1;
        double[] solve = new double[6];
        solve[0] = xL;
        solve[1] = xU;
        solve[2] = calculateXR(xL, xU);
        solve[3] = numberOfIteration;
        solve[4]=100;
        solve[5]=calculateTrueValueError(solve[2],xR);
        solver.add(solve);
        while (numberOfIteration < maxIter&&solver.get(solver.size()-1)[4]>ea) {
            solve=calculateBoundaries(solve);
            solve[2]=calculateXR(solve[0],solve[1]);
            numberOfIteration++;
            solve[3]=numberOfIteration;
            solve[4]=calculateaproximateError(solve[2],solver.get(solver.size()-
                    1)[2]);
            solve[5]=calculateTrueValueError(solve[2],xR);
            solver.add(solve);
        }
        return solver;
    } //end solver
    /**
     * Calculate xR
     * @param xL lower border
     * @param xU upper border
     * @return new xR
     */
    private double calculateXR(double xL, double xU) {//start calculateXR
        double xR = 0;
        if (function.function(xL) * function.function(xU) > 0) {
            System.out.println("error: f(xL)*f(xU)>0");
        } else {
            xR = (xL + xU) / 2;
        }
        return xR;
    } //end calculateXR
    /**
     * Set new borders of iteration depends of value of xR
     * @param solve
     * @return changed xL and xU
     */
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
    /**
     * calculate approximate error
     * @param presentApproximation
     * @param previousApproximation
     * @return approximate error
     */
    private double calculateaproximateError(double presentApproximation,double
            previousApproximation){
        return Math.abs((presentApproximation-previousApproximation)/presentApproximation*100);
    }
    /**
     * calculate true value error
     * @param presentApproximation
     * @param trueValue
     * @return true value error
     */
    private double calculateTrueValueError(double presentApproximation,double
            trueValue){
        return Math.abs((trueValue-presentApproximation)/trueValue*100);
    }
}