package com.samuele;

public class Controllore extends Thread{
    Deposito d;

    public Controllore(String m, Deposito d){
        super(m);
        this.d = d;
    }

    public void run(){
        while (true) {
            try {

                int NumeroPezziRimossi = d.controlla();
                System.out.println(getName() + ": controllo eseguito, pezzi rimossi: " + NumeroPezziRimossi);
                sleep(1000); // dormo per un secondo;

            } catch (InterruptedException e) {
                // TODO: handle exception
                System.out.println("Sono stato interroto, " + e.getMessage());
            }
        }
    }

}
