package com.samuele;

import java.util.ArrayList;

public class Tavolo {
    private int giri;
    private int giocatori;
    private ArrayList<Carta> carteTavolo;
    private ArrayList<Integer> puntiGiocatoriTavolo;
    private int turnoAttuale;
    public  ArrayList<Carta> mazzo;


    public Tavolo(int g) {
        giocatori = g;
        if (40 % giocatori != 0) 
            throw new IllegalArgumentException();
        giri = 40 / giocatori;
        turnoAttuale = 0;

        carteTavolo = new ArrayList<>();
        mazzo = new ArrayList<>();

        puntiGiocatoriTavolo = new ArrayList<>();

        for (int i = 0; i < giocatori; i++){
            puntiGiocatoriTavolo.add(0);
        }


        // inizializzo il mazzo
        for (Seme s : Seme.values()){
            for (int i = 1; i <= 10; i++){
                mazzo.add(new Carta(i, s));
            }
        }         
       
    }

    private void checkCartaVincente_assegnaPunto(){
        Carta c = new Carta(-1, Seme.C);
        for (int i = 0; i < carteTavolo.size(); i++){;
            boolean t = carteTavolo.get(i).batte(c);
            if (t)
                c = carteTavolo.get(i);
        }   

        int a = carteTavolo.indexOf(c);
        int prec = puntiGiocatoriTavolo.get(a);
        puntiGiocatoriTavolo.set(a,++prec);
        System.out.println("Punto a giocatore" + a);            
    }

    public void stampaPunti(){
        for (int i=0; i<giocatori; i++){
            System.out.println("Giocatore" + i + ": partita terminata, ho fatto " + puntiGiocatoriTavolo.get(i));
        }
    }

    public synchronized int pescaCarta(int turno) throws InterruptedException{
        while (turnoAttuale != turno && giri > 0) {
            wait();
        }

        if (giri == 0)
            return 0;


        Carta c = mazzo.removeFirst();
        System.out.println(Thread.currentThread().getName() + " ho pescato " + c);
        
        carteTavolo.add(c);

        //System.out.println("Turno attuale " + turnoAttuale);
        if (turnoAttuale == (giocatori - 1)){
            checkCartaVincente_assegnaPunto();
            carteTavolo.clear();
            
            giri--;
            turnoAttuale = 0;
            
            if (giri == 0){
                // stampa i punti assegnati
                stampaPunti();
            }
        } else {
            turnoAttuale++;
        }
        notifyAll();
        return giri;


    }


}
