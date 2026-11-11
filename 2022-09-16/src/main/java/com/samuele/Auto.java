package com.samuele;

public class Auto extends Thread {
    Parcheggio p;

    public Auto(Parcheggio p){
        this.p = p;
    }

    public void run(){
        try {
            p.attendi();
        } catch (ParcheggioPienoException e) {
            System.out.println(getName() + ": non c'erano più posti, termino");
        } catch (InterruptedException e){
            System.out.println("Sono stato interrotto");
        }
    }


}
