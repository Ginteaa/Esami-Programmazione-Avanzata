package com.samuele;

public class Consumatore extends Thread {
    int q;
    Deposito d;
    
    public Consumatore(String m, int q, Deposito d){
        super(m);
        this.q = q;
        this.d = d;
    }

    public void run(){
        while (true) {
            try {

                Messaggio m [] = d.estrai(q);
                System.out.println(getName() + ": ho estratto " + java.util.Arrays.toString(m));

                int timeSleep = (int) (Math.random() * 1000);
                sleep(timeSleep);

            } catch (InterruptedException e) {
                System.out.println("Eccezione catturata");
            }
            


        }
    }

}
