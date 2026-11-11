package com.samuele;

public class Cliente extends Thread {
    boolean Vip;
    Negozio n;


    public Cliente(boolean b, Negozio n){
        this.Vip = b;
        this.n = n;
    }

    public void run(){
        boolean entrato = false;
        while (!entrato){
            try {
                // entra nel negozio
                n.entrata(Vip);
                entrato = true;

                // rimango nel negozio
                int timeSleep = 1000 + (int) (Math.random() * 1000);
                //System.out.println(getName() + ": vado in sleep per " + timeSleep);
                sleep(timeSleep);

                //System.out.println(getName() + " Mi sono svegliato");

                // esco dal negozio
                n.uscita(Vip);
                
            }  catch (InterruptedException e) {
                System.out.println(getName() + " Sono stato interrotto, ricomincio");
            }
        }


    }

}