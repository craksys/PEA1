package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static void main(String[] args) throws IOException {
        Graph graph = new Graph();
        BruteForce bruteForce;
        Scanner in = new Scanner(System.in);
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
                    bruteForce.bruteForce();
                }
                default -> {
                }
            }
        }
    }

     public static void printOptions(){
        System.out.println("Wybierz opcję programu: ");
        System.out.println("1. Wczytaj graf z plik");
        System.out.println("2. Wygeneruj graf");
        System.out.println("3. Wyświetl aktualny graf");
        System.out.println("4. Rozwiąż: BruteForce");
        System.out.println("5. Wyjście");
    }
}


