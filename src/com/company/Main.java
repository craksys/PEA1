package com.company;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";

    public static long millisActualTime;
    public static long executionTime;

    public static void main(String[] args) throws IOException {
        Graph graph = new Graph();
        BruteForce bruteForce;
        Scanner in = new Scanner(System.in);
        graph.copyFromTXT("tsp_5_hindus.txt");
        int menu = -1;
        while(menu != 0) {
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
                    System.out.println("Czas wykonania BruteForce: "+ executionTime+" ms");
                    break;
                }
                case 5 -> {
                    BetterBruteForce bnb = new BetterBruteForce(graph);
                    millisActualTime = System.currentTimeMillis();
                    bnb.solve();
                    executionTime = System.currentTimeMillis() - millisActualTime;
                    System.out.println("Czas wykonania BNB: "+ executionTime+" ms");
                    break;
                }
                case 6 -> {
                    for(int i = 0; i<50; i++){// pusty przebieg
                        graph = new Graph();
                        bruteForce = new BruteForce(graph);
                        graph.createGraph(10);
                        graph.generateGraph();
                        bruteForce.solve();
                    }
                    for(int i = 0; i<100; i++){
                        graph = new Graph();
                        bruteForce = new BruteForce(graph);
                        graph.createGraph(10);
                        graph.generateGraph();

                        millisActualTime = System.currentTimeMillis();
                        bruteForce.solve();
                        executionTime += System.currentTimeMillis() - millisActualTime;
                    }
                    System.out.println("Czas wykonania BruteForce: "+ executionTime+" ms");
                    executionTime = 0;
                }
                case 7 -> {
                    BranchAndBound branchAndBound = new BranchAndBound(graph);
                    System.out.println(branchAndBound.reduceMatrix(graph.matrix));
                }
                default -> {
                }
            }
        }
    }

     public static void printOptions(){
        System.out.println("Wybierz opcję programu: ");
        System.out.println("1. Wczytaj graf z pliku");
        System.out.println("2. Wygeneruj graf");
        System.out.println("3. Wyświetl aktualny graf");
        System.out.println("4. Rozwiąż: BruteForce");
        System.out.println("5. Rozwiąż: BruteForce zoptymalizowany (7x)");
        System.out.println("6. Oblicz czas");
        System.out.println("7. Wyjście");
    }
}


