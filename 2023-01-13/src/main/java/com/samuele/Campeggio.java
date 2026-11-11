package com.samuele;

import java.util.ArrayList;

public class Campeggio {
    private int numeroPiazzole;
    private int numeroPiazzoleOccupate;
    private int numeroPiazzoleLibere;
    private ArrayList<Prenotazione> clienti;
    private int prossimoNumero;

    public Campeggio(){
        this.numeroPiazzole = 200;
        this.numeroPiazzoleOccupate = 0;
        prossimoNumero = 0;
        this.numeroPiazzoleLibere = numeroPiazzole;
        clienti = new ArrayList<>();
    }

    public Campeggio(int n){
        numeroPiazzole = n;
        numeroPiazzoleOccupate = 0;
        prossimoNumero = 0;
        numeroPiazzoleLibere = numeroPiazzole;
        clienti = new ArrayList<>();
    }

    public synchronized int prenota(int q, long t) throws InterruptedException, PrenotazioneFallitaException{
        long scadenza = System.currentTimeMillis() + t;
        while (q > numeroPiazzoleLibere){

            long rimanente = scadenza - System.currentTimeMillis();
            if (rimanente <= 0)
                    throw new PrenotazioneFallitaException();

            System.out.println(Thread.currentThread().getName() + " mi blocco per " + rimanente + "ms, volevo prenotare " + q + " piazzole");
            wait(rimanente);
        }

                    

        // ci sono abbastanza piazzole libere
        numeroPiazzoleOccupate += q;
        numeroPiazzoleLibere -= q;

        int numeroUnivoco = ++prossimoNumero;
        Prenotazione p = new Prenotazione(numeroUnivoco, q);
        clienti.add(p);

        System.out.println(Thread.currentThread().getName() + ", prenoto " + q  + " piazzole ne rimangono: " + numeroPiazzoleLibere);

        notifyAll();

        return numeroUnivoco;

    }

    public synchronized void disdici(int nU){

        int q = 0;
        for (Prenotazione p : clienti){
            if (p.get_nU() == nU){
                q = p.get_nP();
                clienti.remove(p);
                break;
            }
        }

        numeroPiazzoleLibere += q;
        numeroPiazzoleOccupate -= q;

        System.out.println(Thread.currentThread().getName() + ", disdico la mia prenotazione <" + nU + "> e tolgo " + q + " piazzole, ne rimangono: " + numeroPiazzoleLibere);

        notifyAll();

    }


}
