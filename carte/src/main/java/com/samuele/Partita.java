package com.samuele;

import  java.util.Collections;
import java.util.List;

public class Partita {
    public static void main(String[] args) {
        int numGiocatori = 8;
        Tavolo tavolo = new Tavolo(numGiocatori);

        Collections.shuffle(tavolo.mazzo);
        

        Giocatore [] giocatori = new Giocatore[numGiocatori];

        for (int i=0; i < numGiocatori; i++){
            giocatori[i] = new Giocatore(i, tavolo);
            giocatori[i].start();
        }
    }
}
