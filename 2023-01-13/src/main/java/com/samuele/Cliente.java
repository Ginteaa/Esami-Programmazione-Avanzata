package com.samuele;

public class Cliente extends Thread{
    private int numeroPiazzoledaPrenotare;
    Campeggio c;
    
    public Cliente(int n, Campeggio c){
        numeroPiazzoledaPrenotare = n;
        this.c = c;
    }

    public void run(){
        try {
            
            long timeToSleep = (long) (Math.random()* 1000);
            // prenote un numero di piazzole 
            int nU = c.prenota(numeroPiazzoledaPrenotare, timeToSleep);
            // decido se disdire
            boolean disdico = Math.random() < 0.5;
            if (disdico)
                c.disdici(nU);
            
        } catch (PrenotazioneFallitaException | InterruptedException e) {
            System.out.println(getName() + ", prenotazione fallita");

        }

    }


}
