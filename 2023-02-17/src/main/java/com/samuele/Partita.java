package com.samuele;

import java.util.ArrayList;
import java.util.List;

public class Partita {
    private int numGiocatori;
    private List<Carta> mazzo1;
    private List<Carta> mazzo2;
    private int numgiocatoriInAttesa;
    private int cartedaEstrarre;
    boolean estrazioneFatta = false;
    private Carta cartaVincente = null;


    public Partita(int n){
        numGiocatori = n;
        numgiocatoriInAttesa = 0;
        cartedaEstrarre = 40;

        // inizializzo i mazzi;
        mazzo1 = new ArrayList<>();
        mazzo2 = new ArrayList<>();
        for (Seme s : Seme.values()){
            for (int i=1; i<=10;i++){
                mazzo1.add(new Carta(i, s));
                mazzo2.add(new Carta(i, s));
            }
        }
    }


    public synchronized Carta compraCarta(int v, Seme s) throws CartaNonDisponibileException, IllegalArgumentException{
        
        if (v < 1 || v > 10 || (s != Seme.CUORI && s != Seme.QUADRI && s != Seme.FIORI && s != Seme.PICCHE)){
            throw new IllegalArgumentException();
        }

        Carta daEstrarre = new Carta(v, s);
        boolean trovato = false;
        for (Carta c : mazzo1){
            if (c != null){
                if (c.equals(daEstrarre)){
                    mazzo1.remove(c);
                    trovato = true;
                    break;
                }
            }
        }
        if (!trovato){
            notifyAll();
            trovato = false; 
            throw new CartaNonDisponibileException();
        }
        cartedaEstrarre--;

        notifyAll();
        return daEstrarre;

    }


    public synchronized Carta[] carteDisponibili(){

        Carta [] carte = new Carta[cartedaEstrarre];

        int index = 0;
        for (Carta c : mazzo1){
                carte[index] = new Carta(c.getNum(), c.getSeme());
                index++;
        }

        notifyAll();
        return carte;

    }

    private void stampaMazzo(Thread t, Carta [] bb){
        System.out.print(t.getName() + ": ho queste carte [ ");
        for (int i = 0; i<bb.length; i++){
            System.out.print(bb[i]);
            if (i < bb.length - 1)
                System.out.print(", ");
        }
        System.out.println("]");
    }

    public synchronized boolean  AttendiEstrazione(Carta[] bb) throws InterruptedException{
        stampaMazzo(Thread.currentThread(), bb);
        numgiocatoriInAttesa++;
        while (!mazzo1.isEmpty() || !estrazioneFatta) {
            if (numGiocatori == numgiocatoriInAttesa){
                break;
            }
            wait();
        }
        if (!estrazioneFatta){
            estrazioneFatta = true;
            int indiceCartadaEstrarre = -1;
            // sono l'ultimo giocatore in attesa, effettuo l'estrazoine e sveglio gli altri
            indiceCartadaEstrarre = (int) (Math.random() * 40) ;
            System.out.println("indice: " + indiceCartadaEstrarre);
            cartaVincente = mazzo2.get(indiceCartadaEstrarre);
            System.out.println("carta estratta: " + cartaVincente);
        }

        // cerco se sono il vincitore
        for (int i = 0; i<bb.length;i++){
            if (bb[i].equals(cartaVincente)){
                notifyAll();
                return true;
            }
        }
        notifyAll();
        return false;

    }

}
