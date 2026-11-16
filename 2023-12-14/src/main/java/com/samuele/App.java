package com.samuele;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        try {
            Tavolo t = new Tavolo(8);
            Giocatore [] giocatori = new Giocatore[8];
            for (int i = 0; i < 8; i++){
                Giocatore g = new Giocatore("Giocatore" + i, t, i);
                giocatori[i] = g;
                g.start();
            } 
            
            for (Giocatore g : giocatori)
                g.join();

            t.punteggio();
            
        } catch (InterruptedException e){
            System.out.println("Eccezione catturata");
        }
    }
}
