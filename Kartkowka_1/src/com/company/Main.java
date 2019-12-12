package com.company;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Function function = x -> Math.PI * Math.pow(x, 2) * ((3 * 3 - x) / 3) - 30; //definicja funkcji

        System.out.println("Rozwiązanie metodą biskecji ");
        BisectionSolver bisectionSolver = new BisectionSolver(function);
        ArrayList<double[]> bisectionSolve = bisectionSolver.solver(0, 6, 0.001, 30, 2);
        printArrayList(bisectionSolve);
        System.out.println("Rozwiązanie metodą falsi");
        FalsiSolver falsiSolver = new FalsiSolver(function);
        ArrayList<double[]> falsiSolve = falsiSolver.solver(0, 6, 0.001, 30, 2);
        printArrayList(falsiSolve);
        System.out.println("Rozwiązanie metodą Newtona-Raphsona");
        NewtonRaphsonSolver newtonRaphsonSolver = new NewtonRaphsonSolver(function);
        ArrayList<double[]> newtonRaphsonSolve = newtonRaphsonSolver.solver(0, 6, 0.001, 30, 2, 4);
        printArrayList(newtonRaphsonSolve);
        System.out.println("Rozwiązanie metodą siecznych");
        TransversalSolver transversalSolver = new TransversalSolver(function);
        ArrayList<double[]> transversalSolve = transversalSolver.solver(0, 6, 0.001, 30, 2, 4, 5);
        printArrayList(transversalSolve);
        System.out.println("Rozwiązanie 2 zadania, kolejne iteracje x dla kroku h=0.5");
        FunctionMultivariable functionMultivariable = (t, x) -> -2 * Math.pow(t, 3) + 12 * Math.pow(t, 2) - 20 * t + 8.5;
        HeunMethod heunMethod = new HeunMethod(functionMultivariable);
        double[] heunSolve = heunMethod.solver(8, 0.5, 1);
        printTable(heunSolve);


    } //end main

    public static void printTable(double[] doubles) {
        for (double d :
                doubles) {
            System.out.println(d);
        }
    }

    public static void printArrayList(ArrayList<double[]> doubles) {
        for (int i = 0; i < doubles.size(); i++) {
            System.out.print("xL " + doubles.get(i)[0] + " ");
            System.out.print("xU " + doubles.get(i)[1] + " ");
            System.out.print("xR " + doubles.get(i)[2] + " ");
            System.out.print("iteration " + doubles.get(i)[3] + " ");
            System.out.print("approximate error " + doubles.get(i)[4] + " ");
            System.out.print("true value error " + doubles.get(i)[5] + " ");
            System.out.println();
        }
    }
}
