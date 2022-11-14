package com.company;

import java.util.ArrayDeque;
import java.util.Deque;

import static java.lang.Integer.MAX_VALUE;

public class BetterBruteForce {
    private final Graph graph;
    private final int startNode = 0;
    private final Deque<Integer> pathStack = new ArrayDeque<>();
    private final Deque<Integer> bestPathStack = new ArrayDeque<>();
    private int[] counter;
    private int weightStack = 0;
    private int bestStack = MAX_VALUE;

    public BetterBruteForce(Graph graph) {
        this.graph = graph;
    }

    private void intCounter() { //inicjalizacja licznika
        counter = new int[graph.size - 1];
        for (int i = 0; i < graph.size - 1; i++) {
            counter[i] = i + 1;
        }
    }

    private void pushActualPath() { //dodanie aktualnej ścieżki na stos
        pathStack.add(startNode);
        for (int j : counter) {
            pathStack.add(j);
        }
        pathStack.add(startNode);
    }

    private void swapPath() { //podmiana aktualnej ścieżki z najlepszą
        bestPathStack.clear();
        while (!pathStack.isEmpty()) {
            bestPathStack.add(pathStack.pop());
        }
    }

    public void solve() {
        intCounter();
        int tempNow, tempNext;
        do {
            tempNow = startNode;
            for (int j : counter) {
                tempNext = j;
                weightStack += graph.matrix[tempNow][tempNext];
                tempNow = tempNext;
                if (weightStack >= bestStack) {
                    break;
                }
            }
            weightStack += graph.matrix[tempNow][startNode];
            if (weightStack < bestStack) {
                //weightStack += graph.matrix[tempNow][startNode];
                bestStack = weightStack;
                pushActualPath();
                swapPath();
            }
            weightStack = 0;
            pathStack.clear();
        } while (findNextPermutation());
        System.out.println("Waga ścieżki: " + bestStack);
        System.out.println("Scieżka: ");
        reverseDeque(); // do odwrocenia sciezki
        while (!bestPathStack.isEmpty()) {
            System.out.print(bestPathStack.pop());
            System.out.print(" ");
        }
        System.out.println();
    }

    private boolean findNextPermutation() {
        if (counter.length <= 1) //warunek sprawdzający czy w tablicy jest więcej niż 1 element
            return false;

        int last = counter.length - 2;//wybranie 2 elementu od końca

        while (last >= 0) { //przejście do pierwszych od końca elementów nie rosnących + piwot
            if (counter[last] < counter[last + 1]) {
                break;
            }
            last--;
        }

        if (last < 0) /// jeżeli nie ma nierosonących elementów to tablica jest posortowana
            return false;

        int nextBigger = counter.length - 1;

        for (int i = counter.length - 1; i > last; i--) { //znajdowanie najdalszego większego elementu
            if (counter[i] > counter[last]) {
                nextBigger = i;
                break;
            }
        }

        swap(nextBigger, last);

        makeReverse(last + 1, counter.length - 1);
        return true;
    }

    private void reverseDeque() { //odwrócenie stosu o 180
        Deque<Integer> tempDeque = new ArrayDeque<>();
        while (!bestPathStack.isEmpty()) {
            tempDeque.add(bestPathStack.pop());
        }
        while (!tempDeque.isEmpty()) {
            bestPathStack.add(tempDeque.pop());
        }

    }

    private void swap(int left, int right) { //pomocnicza funkcja do permutacji zamieniająca pozycjami 2 wartości
        int temp = counter[left];
        counter[left] = counter[right];
        counter[right] = temp;
    }

    private void makeReverse(int left, int right) {//pomocnicza funkcja do permutacji odwracająca elementy
        while (left < right) {
            int temp = counter[left];
            counter[left++] = counter[right];
            counter[right--] = temp;
        }
    }

    public Graph getGraph() {
        return graph;
    }

    public int getStartNode() {
        return startNode;
    }

    public Deque<Integer> getPathStack() {
        return pathStack;
    }

    public Deque<Integer> getBestPathStack() {
        return bestPathStack;
    }

    public int[] getCounter() {
        return counter;
    }

    public int getWeightStack() {
        return weightStack;
    }

    public int getBestStack() {
        return bestStack;
    }
}
