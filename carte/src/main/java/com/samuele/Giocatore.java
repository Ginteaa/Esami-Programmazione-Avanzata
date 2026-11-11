package com.samuele;

public class Giocatore extends Thread{
    
    private int mioTurno;
    private Tavolo t;

    public Giocatore(int turno, Tavolo t){
        super("Giocatore" + turno);
        mioTurno = turno;
        this.t = t;
    }


    public void run(){
        int giri =  -1;
        do  {
            try {
                // provo a prendere la carta
                giri = t.pescaCarta(mioTurno);
                //System.out.println("giri mancanti: " + giri)
            } catch (InterruptedException e) {
                // TODO: handle exception
            }            
        } while (giri != 0);
        

    }
}
