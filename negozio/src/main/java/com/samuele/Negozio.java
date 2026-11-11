package com.samuele;

import java.util.ArrayList;

public class Negozio {
    private int N;
    private int M;
    private int numeroClientiTotale;
    private int numeroClientiVip;
    private int numeroClientiNormale;
    private boolean negozioChiuso;
    private ArrayList<Thread> clientVipInAttesa = new ArrayList<>();




    public Negozio(int N, int M) throws ParametriErratiException{

        if (M <= 0 || M >= N) {
            throw new ParametriErratiException();
        }

        this.N = N;
        this.M = M;
        numeroClientiVip = 0;
        numeroClientiNormale = 0;
        numeroClientiTotale = 0;

        negozioChiuso = false;

    }


    private void stampa(boolean vip, String s){
        if (vip){
            System.out.println("Cliente " + Thread.currentThread().getName() + "(VIP)" + s);
        }
        else{
            System.out.println("Cliente " + Thread.currentThread().getName() + s);
        }
    }


    public synchronized void entrata(boolean vip) throws InterruptedException{
        // il normale aspetta anche se ci sono vip in attesa (precedenza ai vip)
        while (negozioChiuso || (!vip && (numeroClientiNormale == M || !clientVipInAttesa.isEmpty()))){
            stampa(vip, " in attesa");
            if (vip && !clientVipInAttesa.contains(Thread.currentThread()))
                clientVipInAttesa.add(Thread.currentThread());
            wait(); // mi blocco
        }

        stampa(vip, " entrato");
        numeroClientiTotale++;
        if (vip){
            clientVipInAttesa.remove(Thread.currentThread());
            numeroClientiVip++;
            // se ero l'ultimo vip in attesa, sveglio i normali che aspettavano per la precedenza
            if (clientVipInAttesa.isEmpty())
                notifyAll();
        } else
            numeroClientiNormale++;

        if (numeroClientiTotale == N ){
            negozioChiuso = true;
            System.out.println("Negozio Chiuso");
        }



    }

    public synchronized void uscita(boolean vip) throws InterruptedException{
        /*
            Decremento i contatori, se il negozio si svuota lo riapro.
            In ogni caso faccio notifyAll: chi aspetta ricontrolla la propria condizione.
        */
        numeroClientiTotale--;
        if (vip)
            numeroClientiVip--;
        else 
            numeroClientiNormale--;
        stampa(vip, " uscito");


        if (numeroClientiTotale == 0 && negozioChiuso){
            negozioChiuso = false;
            System.out.println("negozio riaperto");
        }
        notifyAll();


    }

}