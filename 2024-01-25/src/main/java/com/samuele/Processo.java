package com.samuele;

public class Processo extends Thread{
    boolean prio;
    Blocco b;
    GestoreMemoria gm;

    public Processo(boolean p, GestoreMemoria gm){
        prio = p;
        this.gm = gm;
        b = null;
    }

    public boolean getprio() {return prio;}

    public void run(){
        try {
            b = gm.acquisisci(prio);

            int timeSleep = (int)(Math.random() * 1000);
            System.out.println(getName() + ": dormo per: " + timeSleep + "ms");
            sleep(timeSleep);

            gm.free(b);
            System.out.println(getName() + ": ho finito");


        } catch (InterruptedException e) {
            
        }
    }
}

