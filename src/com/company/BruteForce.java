package com.company;

import java.util.ArrayDeque;
import java.util.Deque;

public class BruteForce {
    Graph graph;
    int[] counter;
    int startNode = 0;
    Deque<Integer> pathStack = new ArrayDeque<>();
    Deque<Integer> weightStack = new ArrayDeque<>();
    public BruteForce(Graph graph) {
        this.graph = graph;
    }

    public void intCounter() {
        counter = new int[graph.size];
        for (int i = 0; i < graph.size; i++) {
            counter[i] = graph.size - 1;
        }
    }

    public void bruteForce() {
        intCounter();
        int tempNow, tempNext;
        while (counter[startNode] >= 0) {
            tempNow = startNode;
            pathStack.add(tempNow);
            while(pathStack.size() < graph.size + 1){
            tempNext = counter[tempNow];
            weightStack.add(graph.matrix[tempNow][tempNext]);
            pathStack.add(tempNext);
            tempNow = tempNext;
            }
            nextPermutation();
            //checkHamilton();
            //counter[graph.size-1]--;
            //while(checkCounter());
            //System.out.println("xd");
            if(counter[0] == 7){System.out.println("xd");}
        }
    }

    public void checkHamilton(){
        int tempTab[] = new int[graph.size];
        boolean check = false;
        while(!pathStack.isEmpty()){
            tempTab[pathStack.pop()]++;
        }
        //sprawdzanie
        if(tempTab[0] != 2){
            check = true;
        }
        if(check == false) {
            for (int i = 1; i < graph.size; i++) {
                if (tempTab[i] != 1) {
                    check = true;
                }
            }
        }
        if(check == false){
            //System.out.println("droga hamiltona");
        }
        pathStack.clear();
        weightStack.clear();
        if(counter[0] == 8){System.out.println("xd");}
    }

    boolean checkCounter(){ //zwraca true jeżeli funkcje trzeba powtórzyć
        for(int i =0; i< graph.size; i++){
            if(counter[0] == -1){
                return false;
            }else if(counter[i] < 0){
                counter[i] = graph.size - 1;
                counter[i-1]--;
                return true;
            }
        }
        return false;
    }

    void nextPermutation(){
        counter[graph.size-1]--;
        while(checkCounter());
        while(checkPermutation()){
            counter[graph.size-1]--;
            while(checkCounter());
        }
    }


}
