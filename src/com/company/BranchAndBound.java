package com.company;

import static java.lang.Integer.MAX_VALUE;

public class BranchAndBound {
    private Graph graph;
    public BranchAndBound(Graph graph){
        this.graph = graph;
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
            if(matrix[j][column] < vertical && matrix[column][j] != -1){
                vertical = matrix[j][column];
            }
        }
        if(vertical == MAX_VALUE){
            return -1;
        }else {
            return vertical;
        }
    }
}
