package com.samuele;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        
        Partita p = new Partita(4);
        for (int i=0; i<4; i++){
            Giocatore g = new Giocatore(p);
            g.start();
        }
    }
}
