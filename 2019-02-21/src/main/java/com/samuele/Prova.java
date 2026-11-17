package com.samuele;

/**
 * Hello world!
 */
public class Prova {
    public static void main(String[] args) {
        Roulette r = new Roulette();
        try{
            for (int i = 0; i < 5; i++){
                new Giocatore(r).start();
            }
            r.avvia();
            
        } catch (InterruptedException e){
            System.out.println("Eccezine catturata");
        }
    }
}
