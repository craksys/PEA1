package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class Graph {
    int[][] matrix;
    Random rand = new Random();
    public int size;

    public void createGraph(int size){
        this.size = size;
        matrix = new int[size][size];
    }

    public void generateGraph(){
        for(int i = 0;i < size; i++) {
            for(int j = 0; j < size; j++){
                if (i != j) {
                    matrix[i][j] = rand.nextInt(50)+1;
                }else{
                    matrix[i][j] = -1;
                }
            }
        }
    }

    public void printAll(){
        for(int i = 0;i < size; i++){
            for(int j = 0; j<size; j++) {
                System.out.print(matrix[i][j]);
                if(matrix[i][j] < 10 && matrix[i][j] != -1){
                    System.out.print("   ");
                }else if(matrix[i][j] < 100 && matrix[i][j] != -1) {
                 System.out.print("  ");
                }else{
                    System.out.print(" ");
                }
            }
            System.out.println("");
        }
    }


    public void copyFromTXT() {
        try {
            File myObj = new File("src/com/company/tsp_10.txt");
            Scanner myReader = new Scanner(myObj);
            size = myReader.nextInt(); //odczytujemy liczbę miast
            matrix = new int[size][size];
            for(int i =0; i < size; i++){
                for(int j=0; j < size; j++){
                    matrix[i][j] = myReader.nextInt();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}