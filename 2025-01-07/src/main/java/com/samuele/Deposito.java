package com.samuele;

import java.util.ArrayList;
import java.util.List;

public class Deposito {
    int m;
    int n;
    List<Messaggio> Normali;
    List<Messaggio> Urgenti;
    int nMsgN;
    int nMsgU;

    public Deposito(){
        this(10, 10);
    }

    public Deposito(int n, int m){
        this.n = n;
        this.m = m;

        nMsgN = nMsgU = 0;
        Normali = new ArrayList<>();
        Urgenti = new ArrayList<>();
    }


    public synchronized void inserisci(Messaggio m) throws InterruptedException{
        while ((nMsgU == this.m && m.isUrgente()) || (nMsgN == n && !m.isUrgente())) {
            System.out.println(Thread.currentThread().getName() + ": mi blocco nella inserisci, voglio inserire un messagio urgente <"+m.isUrgente()+">");
            wait();
        }

        // inserisco il messaggio
        if (m.isUrgente()){
            Urgenti.add(m);
            nMsgU++;
            System.out.println(Thread.currentThread().getName() + ": ho inserito il messaggio URGENTE");
        } else {
            Normali.add(m);
            nMsgN++;
            System.out.println(Thread.currentThread().getName() + ": ho inserito il messaggio NON URGENTE");
        }

        System.out.println("DEBUG: " + Thread.currentThread().getName() + ": ci sono " + nMsgU + " messaggi urgenti e " + nMsgN + " messaggi normali");

        notifyAll();

    }
    

    public synchronized Messaggio [] estrai(int q) throws InterruptedException, IllegalArgumentException {
        if (q > n){
            throw new IllegalArgumentException();
        }

        while((nMsgU == 0 && q > (nMsgN + nMsgU))){
            System.out.println(Thread.currentThread().getName() + ": mi blocco nella estrai, voglio estrarre " + q + " messaggi");
            wait();
        }

        int k = Math.min(q, nMsgN + nMsgU);
        Messaggio [] daEstrarre = new Messaggio[k];

        int cont = 0;

        while(q > 0 && nMsgU != 0){
            Messaggio mE = Urgenti.removeFirst();
            daEstrarre[cont] = mE;
            cont++;
            nMsgU--;
            q--;
        }


        while(q > 0 && nMsgN != 0){
            Messaggio mE = Normali.removeFirst();
            daEstrarre[cont] = mE;
            cont++;
            nMsgN--;
            q--;
        }


        notifyAll();
        return daEstrarre;





    }




}
