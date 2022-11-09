package com.company;

import java.util.ArrayList;

import static java.lang.Integer.MAX_VALUE;

public class BranchAndBound {
    public Graph graph;
    private ArrayList<ArrayList<Integer>> nodes = new ArrayList<ArrayList<Integer>>();
    public BranchAndBound(Graph graph){
        this.graph = graph;
    }

    public void solve(){
        int firstReductionCost = reduceMatrix(graph.matrix); //wstępna redukcja macierzy i zapamiętanie jej kosztu
        int[][] tempMatrix, veryTempMatrix;
        int tempCost;
        int firstUnused;
        for(int i = 1; i < graph.matrix.length; i++){ //sprawdzenie pierwszych wierzcholków
            tempMatrix = copy(graph.matrix);
            tempCost = getCost(graph.matrix, 0,i);
            insertInfinity(tempMatrix,0,i);
            tempCost += reduceMatrix(tempMatrix);
            tempCost += firstReductionCost;
            ArrayList<Integer> tempArray = new ArrayList<>();
            tempArray.add(tempCost);
            tempArray.add(i);
            nodes.add(tempArray);
        }
        System.out.println(findSmallestFromArray(nodes));
        while(nodes.get(findSmallestFromArray(nodes)).size() < graph.size){
            int tempActual = findSmallestFromArray(nodes);
            boolean[] isUsed = new boolean[graph.size];
            markUsedNodes(isUsed, nodes.get(tempActual));


            tempMatrix = copy(graph.matrix);
            insertInfinity(tempMatrix,0,nodes.get(tempActual).get(1));
            for(int i = 1; i < nodes.get(tempActual).size() - 1; i++){ //obliczenie aktualnie wykonywanej macierzy
                insertInfinity(tempMatrix, i, i+1);
                reduceMatrix(tempMatrix);
            }
            System.out.println("xd");
            for(int temp = nextUnused(isUsed); temp != -1;){
                veryTempMatrix = copy(tempMatrix);
                tempCost = nodes.get(tempActual).get(0);
                tempCost += getCost(veryTempMatrix, nodes.get(tempActual).get(nodes.get(tempActual).size()-1), temp);
                insertInfinity(veryTempMatrix,nodes.get(tempActual).get(nodes.get(tempActual).size()-1), temp);
                tempCost += reduceMatrix(veryTempMatrix);
                ArrayList<Integer> tempArray = new ArrayList<>();
                tempArray.add(tempCost);
                for(int j = 1; j < nodes.get(tempActual).size(); j++){
                    tempArray.add(nodes.get(tempActual).get(j));
                }
                tempArray.add(temp);
                nodes.add(tempArray);
                temp = nextUnused(isUsed);
            }
            System.out.println("xd");
            nodes.remove(tempActual);
        }
        System.out.println("xd");
    }

    public int nextUnused(boolean[] isUsed){
        int firstUnused = -1;
        for(int i = 0; i < isUsed.length; i++){ //rozwinięcie pierwszego niewykorzystanego węzła
            if(isUsed[i] == false){
                firstUnused = i;
                isUsed[i] = true;
                return firstUnused;
            }
        }
        return firstUnused;
    }

    public void markUsedNodes(boolean[] isUsed, ArrayList<Integer> array ){
        isUsed[0] = true;
        for(int i = 1; i < array.size(); i++){
            isUsed[array.get(i)] = true;
        }
    }

    public int reduceMatrix(int[][] matrix){ //modyfikuje aktualną tablicę !!!!!!
        int totalHorizontal = 0, totalVertical = 0;
        int tempSmall;

        for (int i = 0; i < matrix.length; i++){//minimalizacja wierszy i zapisanie
            tempSmall = getSmallestHorizontal(matrix, i);
            if(tempSmall > 0){
                totalHorizontal += tempSmall;
                for(int j = 0; j < matrix.length; j++){
                    if(matrix[i][j] != -1) {
                        matrix[i][j] -= tempSmall;
                    }
                }
            }
        }

        for (int i = 0; i < matrix.length; i++){//minimalizacja kolumn i zapisanie
            tempSmall = getSmallestVertical(matrix, i);
            if(tempSmall > 0){
                totalVertical += tempSmall;
                for(int j = 0; j < matrix.length; j++){
                    if(matrix[j][i] != -1) {
                        matrix[j][i] -= tempSmall;
                    }
                }
            }
        }
        return totalHorizontal + totalVertical;
    }

    public void insertInfinity(int[][] matrix, int from, int to){ //insert -1 where nodes is used
        for(int i = 0; i < matrix.length; i++){
            matrix[from][i] = -1;
        }
        for(int i = 0; i < matrix.length; i++){
            matrix[i][to] = -1;
        }
        matrix[to][from] = -1;
    }

    public int getSmallestHorizontal(int[][] matrix, int line){ //z danego rzędu zwraca najmniejszą wartość rożną od -1
        int horizontal = MAX_VALUE;
        for(int j = 0; j < matrix.length; j++){
            if(matrix[line][j] < horizontal && matrix[line][j] != -1){
                horizontal = matrix[line][j];
            }
        }
        if(horizontal == MAX_VALUE){
            return -1;
        }else {
            return horizontal;
        }
    }
    public int getSmallestVertical(int[][] matrix, int column){
        int vertical = MAX_VALUE;
        for(int j = 0; j < matrix.length; j++){
            if(matrix[j][column] < vertical && matrix[j][column] != -1){
                vertical = matrix[j][column];
            }
        }
        if(vertical == MAX_VALUE){
            return -1;
        }else {
            return vertical;
        }
    }

    public int getCost(int[][] matrix, int from, int to){
       return matrix[from][to];
    }

    public int[][] copy(int[][] src) {
        if (src == null) {
            return null;
        }

        int[][] copy = new int[src.length][];
        for (int i = 0; i < src.length; i++) {
            copy[i] = src[i].clone();
        }

        return copy;
    }

    public int findSmallestFromArray(ArrayList<ArrayList<Integer>> array){
        int tempSmallest = MAX_VALUE;
        int indexOfSmallest = -1;
        for(int i = 0; i < array.size(); i++){
            if(array.get(i).get(0) < tempSmallest){
                tempSmallest = array.get(i).get(0);
                indexOfSmallest = i;
            }
        }
        return indexOfSmallest;
    }
}
