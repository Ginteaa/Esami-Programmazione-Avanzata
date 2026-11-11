package com.samuele;

import java.util.ArrayList;
import java.util.List;

public class Deposito {
    private int NumeroTotalePezzi;
    private int NumeroPezziX;
    private int NumeroPezziY;
    private int NumeroPezziInseriti;
    List<Pezzo> PezziX;
    List<Pezzo> PezziY;

    public Deposito(int dim){
        NumeroTotalePezzi = dim;
        NumeroPezziInseriti = 0;

        PezziX = new ArrayList<>();
        NumeroPezziX = 0;

        PezziY = new ArrayList<>();
        NumeroPezziY = 0;

    }

    private boolean estrazione_possibile(){
        if ((NumeroPezziX > 0 && NumeroPezziY == 0) || (NumeroPezziX == 0 && NumeroPezziY > 0))
            return false;
        return true;
    }


    public synchronized void inserisci(Pezzo p) throws InterruptedException {
        while(NumeroPezziInseriti == NumeroTotalePezzi || 
             (NumeroPezziX == (NumeroTotalePezzi-1)) && p.getTipo().equals("X") || 
             (NumeroPezziY == (NumeroTotalePezzi-1)) && p.getTipo().equals("Y")){
            
            System.out.println(Thread.currentThread().getName() + ": mi sono messo in attesa, ci sono " + NumeroPezziInseriti + " pezzi inseriti di cui: x: " + NumeroPezziX + ", y: " + NumeroPezziY);
            wait();        
        }

        if (p.getTipo().equals("X")){
            PezziX.add(p);
            NumeroPezziX++;
        } else {
            PezziY.add(p);
            NumeroPezziY++;
        }

        NumeroPezziInseriti++;

        System.out.println(Thread.currentThread().getName() + ": ho aggiunto un pezzo: " + p);
        notifyAll();
    }

    public synchronized Pezzo [] estrai() throws InterruptedException {
        while(!estrazione_possibile() || NumeroPezziInseriti == 0){
            System.out.println(Thread.currentThread().getName() + ": mi sono messo in attesa");
            wait();            
        }

        Pezzo [] DaRestituire = new Pezzo[2];

        // estraggo i pezzi
        DaRestituire[0] = PezziX.removeFirst();
        NumeroPezziX--;

        DaRestituire[1] = PezziY.removeFirst();
        System.out.println(Thread.currentThread().getName() + ": ho rimosso i pezzi: " + DaRestituire[0] + ", " + DaRestituire[1]);
        NumeroPezziY--;

        NumeroPezziInseriti -= 2;

        notifyAll();
        return DaRestituire;


    }

    public synchronized int controlla(){
        // Non si puo' fare list.remove() dentro un for-each: l'iteratore se ne accorge
        // e lancia ConcurrentModificationException. removeIf rimuove in modo sicuro.
        PezziX.removeIf(p -> p.getLivelloQualità() < 0.2);
        PezziY.removeIf(p -> p.getLivelloQualità() < 0.2);

        int numeroPezziTolti = (NumeroPezziX - PezziX.size()) + (NumeroPezziY - PezziY.size());

        NumeroPezziX = PezziX.size();
        NumeroPezziY = PezziY.size();
        NumeroPezziInseriti -= numeroPezziTolti;
        notifyAll();
        return numeroPezziTolti;

    }


}
        



