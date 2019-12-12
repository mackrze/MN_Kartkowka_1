package com.company;

public class HeunMethod {
    private FunctionMultivariable function;

    public HeunMethod(FunctionMultivariable function) {
        this.function = function;
    }
    public double[] solver(int numberOfIterations, double h, double X0) {
        double[] solver = new double[numberOfIterations];
        double t = 0;
        double x = X0;
        solver[0] = x;
        for (int i = 1; i < solver.length; i++) {
            solver[i] = calculateX(t,solver[i - 1], h);
            t += h;

        }
        return solver;
    }

    private double calculateX(double t,double x, double h) {
        double k1=function.function(t,x);
        double k2=function.function(t+h,x+h*k1);
        return x+((k1+k2)/2)*h;
    }
}
