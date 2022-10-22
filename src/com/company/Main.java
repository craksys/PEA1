package com.company;

public class Main {

    public static void main(String[] args) {
        Graph graph = new Graph();
        BruteForce bruteForce = new BruteForce(graph);
        //graph.createGraph(11);
        //graph.generateGraph();
        graph.copyFromTXT();
        graph.printAll();
        bruteForce.bruteForce();
    }
}
