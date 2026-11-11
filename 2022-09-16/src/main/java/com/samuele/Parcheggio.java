package com.samuele;

import java.util.ArrayList;
import java.util.List;

public class Parcheggio {
    int numeroPosti;
    List<Auto> codaAuto;
    int turnoAttuale;
    private static int turnoAutoP;
    int numeroPostiDisponibili;
    int numeroAutoDaLavare;

    public Parcheggio(int n){
        numeroPosti = n;
        codaAuto = new ArrayList<>();
        turnoAttuale = 0;
        turnoAutoP = 0;
        numeroAutoDaLavare = 0;
        numeroPostiDisponibili = n;
    }

    public synchronized int getAuto() {return numeroPosti;}

    public synchronized int getAutoDaLavare() {return numeroAutoDaLavare;}

    public synchronized void attendi() throws InterruptedException, ParcheggioPienoException{
        if (numeroPostiDisponibili < 1){
            throw new ParcheggioPienoException();
        }
        int turnoAuto = turnoAutoP;
        turnoAutoP++;   
        numeroPostiDisponibili--;
        numeroAutoDaLavare++;

        while (turnoAttuale != turnoAuto){
            System.out.println(Thread.currentThread().getName() + ": mi metto in attesa, mio turno: " + turnoAuto + ", turno attuale: " + turnoAttuale);
            wait();
        }

        System.out.println(Thread.currentThread().getName() + ": uscito dalla coda, aspetto di essere lavato");
        codaAuto.add((Auto)Thread.currentThread());
        notifyAll();
    }

    public synchronized void lava() throws InterruptedException{
        while(codaAuto.isEmpty()){
            System.out.println(Thread.currentThread().getName() + ": mi metto in attesa, non ci sono auto in coda");
            wait();
        }

        Auto a = codaAuto.removeFirst();
        turnoAttuale++;
        numeroPostiDisponibili++;
        numeroAutoDaLavare--;
        System.out.println(Thread.currentThread().getName() + ": lavato: " + a.getName());
        System.out.println("Mancano da lavare: " + numeroAutoDaLavare);

        notifyAll();

    }




}
