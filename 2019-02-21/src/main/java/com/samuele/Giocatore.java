package com.samuele;

public class Giocatore extends Thread{
    Roulette r;

    public Giocatore(Roulette r){
        this.r = r;
    }

    public void run(){
        try {
            sleep((int)(Math.random()*1000));
            int puntata = (int) (Math.random() * 36);
            
            if (r.puntata(puntata))
                System.out.println(getName() + ": ho vinto");
            else 
                System.out.println(getName() + ": ho perso");

        } catch (PuntateNonPossibiliException e) {
            System.out.println(getName() + ": troppo tardi...");
        } catch (InterruptedException e){
            System.out.println(getName() + ": eccezione catturata");
        }
    }

}
