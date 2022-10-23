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

    public void intCounter() {
        counter = new int[graph.size - 1];
        for (int i = 0; i < graph.size - 1; i++) {
            counter[i] = i + 1;
        }
    }

    public void bruteForce() {
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
        while (findNextPermutation(counter));
        System.out.println("Waga ścieżki: " + bestStack);
        System.out.println("Scieżka: ");
        reverseDeque(); // do odwrocenia sciezki
        while (!bestPathStack.isEmpty()) {
            System.out.print(bestPathStack.pop());
            System.out.print(" ");
        }
    }

    public void swapPath() {
        bestPathStack.clear();
        while (!pathStack.isEmpty()) {
            bestPathStack.add(pathStack.pop());
        }
    }

    public void pushActualPath() {
        pathStack.add(startNode);
        for (int i = 0; i < counter.length; i++) {
            pathStack.add(counter[i]);
        }
        pathStack.add(startNode);
    }

    public void reverseDeque(){
        Deque<Integer> tempDeque = new ArrayDeque<>();
        while(!bestPathStack.isEmpty()) {
            tempDeque.add(bestPathStack.pop());
        }
        while (!tempDeque.isEmpty()) {
            bestPathStack.add(tempDeque.pop());
        }

    }

    //--------------------------------------------------------------
    public static int[] swap(int[] data, int left, int right) {
        int temp = data[left];
        data[left] = data[right];
        data[right] = temp;

        return data;
    }

    public static int[] makeReverse(int[] data, int left, int right) {
        while (left < right) {
            int temp = data[left];
            data[left++] = data[right];
            data[right--] = temp;
        }

        return data;
    }


    public static boolean findNextPermutation(int[] data) {
        if (data.length <= 1)
            return false;

        int last = data.length - 2;

        while (last >= 0) {
            if (data[last] < data[last + 1]) {
                break;
            }
            last--;
        }

        if (last < 0)
            return false;

        int nextGreater = data.length - 1;

        for (int i = data.length - 1; i > last; i--) {
            if (data[i] > data[last]) {
                nextGreater = i;
                break;
            }
        }

        swap(data, nextGreater, last);

        makeReverse(data, last + 1, data.length - 1);
        return true;
    }


}
