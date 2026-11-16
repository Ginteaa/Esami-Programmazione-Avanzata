package com.samuele;

import java.util.Scanner;

public class Worker extends Thread {

    int start;
    int end;
    int [] arrayImage;

    public Worker(int start, int end, int [] arrayImage) {
        this.start = start;
        this.end = end;
        this.arrayImage = arrayImage;
    }

    @Override
    public void run() {
        
        for (int i = start ; i < end; i++){
            arrayImage[i] = 
        }
                
    }
    
    
    
    
    
    
}
