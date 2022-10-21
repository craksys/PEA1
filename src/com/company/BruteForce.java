package com.company;

public class BruteForce {
    Graph graph;
    int[] counter;
    int startNode = 0;
    public BruteForce(Graph graph){
        this.graph = graph;
    }
    public void intCounter(){
        counter = new int[graph.size];
        for(int i = 0; i < graph.size; i++){
            counter[i] = graph.size - 1;
        }
    }
    public void bruteForce(){
        while(counter[startNode] >= 0){

        }
    }

}
