package com.samuele;

import java.util.ArrayList;
import java.util.List;

public class Contenitore {
    int s;
    int q;
    int m;
    List<Integer> contenitore;
    boolean scrittura = false;
    boolean lettura = false;

    Object lock = new Object(); // bohhhh

    public Contenitore(int s, int q, int m ){
        this.s = s;
        this.q = q;
        this.m = m;

        contenitore = new ArrayList<>();

        for (int i = 0; i < q; i++){
            contenitore.add((int)(Math.random() * m +1));
            s--;
        }

    }


    void aggiungi(int v) throws ContenitorePienoException, InterruptedException{
        if (s == 0)
            throw new ContenitorePienoException();

        synchronized(this){
            
            while(lettura){
                this.wait();
            }

            scrittura = true;
            contenitore.add(v);
            s--;

            this.notifyAll();
            scrittura = false;

        }

    }

    boolean verifica(int v) throws InterruptedException{

        synchronized(this){
            while (scrittura) {
                this.wait();
            }
        }
        
        boolean trovata = contenitore.contains(v);
        
        synchronized(this){
            lettura = false;
            this.notifyAll();
        }
    
        return trovata;                

    }


}
