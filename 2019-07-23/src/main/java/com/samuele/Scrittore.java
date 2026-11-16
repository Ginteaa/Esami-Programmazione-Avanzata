package com.samuele;

public class Scrittore extends Thread{
    Contenitore c;

    public Scrittore(String m, Contenitore c){
        super(m);
        this.c = c;
    }

    public void run(){

        try {
            for (int i = 0; i < 1000; i++){
                c.aggiungi((int)(Math.random() * 1000));
            }            
        } catch ( ContenitorePienoException | InterruptedException e) {
            System.out.println(getName() + ": eccezzione catturata");
        }
        


    }
}
