package com.samuele;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

public class ImageProcessor {
    
    int row;
    int column;
    int nTotRow;
    Image image;
    int [] arrayImage;
    Scanner s;
    int Workers;

    public ImageProcessor(String f){
        
        
        try (this.s = new Scanner(new File(f))){
            
            row = s.nextInt();
            column = s.nextInt();
            
            nTotRow = row * 3;
            arrayImage = new int[nTotRow*column];

            System.out.println("row: " + row + ", column: " + column);
            //image = new Image(row, row, red, blue, green);
            
            
        } catch (FileNotFoundException e){
            System.err.println("File non trovato");
        }
        
    }

    public synchronized void process(int n) throws IllegalArgumentException{
        
        if (n > (nTotRow))
            throw new IllegalArgumentException();
            
        
        
        int resto = nTotRow % n;
        int [] ass = new int[n];
        if ( resto != 0){
            
            for (int i = 0; i < n; i++){
                ass[i] = (nTotRow - resto) / n;
            }                    
            for (int i = 0; i < resto; i++){
                ass[i]++;
            }
            
        } else {
            for (int i = 0; i < n; i++){
                ass[i] = nTotRow / n;
            }
        }
        
        //Worker w = new Worker(start, end, columnImage, rowToDo);
        int start = 0;
        int end = 0;
        int daFare = 0;
        for (int i = 0; i < n; i++){
            
            start = start + daFare;
            daFare = ass[i] * column;
            end = start + daFare;
            
            new Worker(start, end, arrayImage).start();
            
            System.out.println("Worker" + i + ": righe da fare: " + ass[i] + ", start: " + start + ", end:" + end);
            
            
        }
        
        
        
        
    }

}
