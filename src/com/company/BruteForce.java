package com.company;

import java.util.ArrayDeque;
import java.util.Deque;

import static java.lang.Integer.MAX_VALUE;

public class BruteForce {
    Graph graph;
    int[] counter;
    int startNode = 0;
    Deque<Integer> pathStack = new ArrayDeque<>();
    Deque<Integer> bestPathStack = new ArrayDeque<>();
    int weightStack = 0;
    int bestStack = MAX_VALUE;

    public BruteForce(Graph graph) {
        this.graph = graph;
    }

    public void intCounter() { //inicjalizacja licznika
        counter = new int[graph.size - 1];
        for (int i = 0; i < graph.size - 1; i++) {
            counter[i] = i + 1;
        }
    }

    public void solve() {
        intCounter();
        int tempNow, tempNext;
        do {
            pushActualPath();
            tempNow = startNode;
            for (int i = 0; i < counter.length; i++) {
                tempNext = counter[i];
                weightStack += graph.matrix[tempNow][tempNext];
                tempNow = tempNext;
            }
            weightStack += graph.matrix[tempNow][startNode];
            if (weightStack < bestStack) {
                bestStack = weightStack;
                swapPath();
            }
            weightStack = 0;
            pathStack.clear();
        }
        while (findNextPermutation());
        System.out.println("Waga ścieżki: " + bestStack);
        System.out.println("Scieżka: ");
        reverseDeque(); // do odwrocenia sciezki
       while (!bestPathStack.isEmpty()) {
            System.out.print(bestPathStack.pop());
            System.out.print(" ");
        }
        System.out.println("");
    }

    public void swapPath() { //podmiana aktualnej ścieżki z najlepszą
        bestPathStack.clear();
        while (!pathStack.isEmpty()) {
            bestPathStack.add(pathStack.pop());
        }
    }

    public void pushActualPath() { //dodanie aktualnej ścieżki na stos
        pathStack.add(startNode);
        for (int i = 0; i < counter.length; i++) {
            pathStack.add(counter[i]);
        }
        pathStack.add(startNode);
    }

    public void reverseDeque(){ //odwrócenie stosu o 180
        Deque<Integer> tempDeque = new ArrayDeque<>();
        while(!bestPathStack.isEmpty()) {
            tempDeque.add(bestPathStack.pop());
        }
        while (!tempDeque.isEmpty()) {
            bestPathStack.add(tempDeque.pop());
        }

    }

    public void swap(int left, int right) {
        int temp = counter[left];
        counter[left] = counter[right];
        counter[right] = temp;
    }

    public void makeReverse(int left, int right) {
        while (left < right) {
            int temp = counter[left];
            counter[left++] = counter[right];
            counter[right--] = temp;
        }
    }


    public boolean findNextPermutation() {
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

}
