package com.company;

import java.util.Scanner;

public class Main {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";

    public static long millisActualTime;
    public static long executionTime;

    public static void main(String[] args) {
        Graph graph = new Graph();
        BruteForce bruteForce;
        Scanner in = new Scanner(System.in);
        graph.copyFromTXT("tsp_12.txt");
        int menu = -1;
        while (menu != 0) {
            printOptions();
            menu = in.nextInt();
            switch (menu) {
                case 1 -> {
                    graph = new Graph();
                    System.out.flush();
                    System.out.println("Podaj nazwę pliku: ");
                    String filename = in.next();
                    graph.copyFromTXT(filename);
                    System.out.println(ANSI_RED + "Wczytano!" + ANSI_RESET);
                }
                case 2 -> {
                    graph = new Graph();
                    System.out.flush();
                    System.out.println("Podaj ilość miast/wierzchołków: ");
                    int size = in.nextInt();
                    graph.createGraph(size);
                    graph.generateGraph();
                }
                case 3 -> {
                    System.out.flush();
                    graph.printAll();
                }
                case 4 -> {
                    bruteForce = new BruteForce(graph);
                    millisActualTime = System.currentTimeMillis();
                    bruteForce.solve();
                    executionTime = System.currentTimeMillis() - millisActualTime;
                    System.out.println("Czas wykonania BruteForce: " + executionTime + " ms");
                }
                case 5 -> {
                    BetterBruteForce betterBruteForce = new BetterBruteForce(graph);
                    millisActualTime = System.currentTimeMillis();
                    betterBruteForce.solve();
                    executionTime = System.currentTimeMillis() - millisActualTime;
                    System.out.println("Czas wykonania BruteForce v2: " + executionTime + " ms");
                }
                case 6 -> {
                    int rozmiargrafu = 10;
                    BranchAndBound branchAndBound;
                    for (int i = 0; i < 50; i++) {// pusty przebieg
                        graph = new Graph();
                        branchAndBound = new BranchAndBound(graph);
                        graph.createGraph(rozmiargrafu);
                        graph.generateGraph();
                        branchAndBound.solve();
                    }
                    for (int i = 0; i < 100; i++) {
                        graph = new Graph();
                        branchAndBound = new BranchAndBound(graph);
                        graph.createGraph(rozmiargrafu);
                        graph.generateGraph();

                        millisActualTime = System.currentTimeMillis();
                        branchAndBound.solve();
                        executionTime += System.currentTimeMillis() - millisActualTime;
                    }
                    System.out.println("Czas wykonania BruteForce: " + executionTime + " ms");
                    executionTime = 0;
                }
                case 7 -> {
                    BranchAndBound branchAndBound = new BranchAndBound(graph);
                    millisActualTime = System.currentTimeMillis();
                    branchAndBound.solve();
                    executionTime = System.currentTimeMillis() - millisActualTime;
                    System.out.println("Czas wykonania BNB: " + executionTime + " ms");
                }
                default -> {
                }
            }
        }
    }

    public static void printOptions() {
        System.out.println("Wybierz opcję programu: ");
        System.out.println("1. Wczytaj graf z pliku");
        System.out.println("2. Wygeneruj graf");
        System.out.println("3. Wyświetl aktualny graf");
        System.out.println("4. Rozwiąż: BruteForce");
        System.out.println("5. Rozwiąż: BruteForce zoptymalizowany (7x)");
        System.out.println("6. Oblicz czas w pętli");
        System.out.println("7. Rozwiąż: Branch and Bound");
        System.out.println("8. Wyjście");
    }
}


