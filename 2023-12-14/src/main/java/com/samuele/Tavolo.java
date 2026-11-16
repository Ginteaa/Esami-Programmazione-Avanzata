/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.samuele;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

/**
 *
 * @author samueleinterrante
 */
public class Tavolo {
    int numeroGiocatori;
    int numeroGiri;
    int turnoAttuale;
    int nGiocatoriTurno;
    List<Carta> Mazzo;
    int [] punteggioGiocatori;
    List<Carta> Estratte;
    
    public Tavolo(int g) throws IllegalArgumentException{
        if ((40 % g) != 0 )
            throw new IllegalArgumentException();
        
        numeroGiocatori = g;
        numeroGiri = 40 / g;
        nGiocatoriTurno = 0;
        turnoAttuale = 0;
        Mazzo = new ArrayList<>();
        punteggioGiocatori = new int[g];
        Estratte = new ArrayList<>();
        for (Seme s : Seme.values()){
            for (int j = 1; j <= 10; j++){
               Mazzo.add(new Carta(j, s));
            }
        }
        // Mescolo il mazzo
        Collections.shuffle(Mazzo);
    }
    
    public void punteggio(){
        for (int i = 0; i < numeroGiocatori; i++){
            System.out.println("Giocatore"+ i + ": partita , ho fatto " + punteggioGiocatori[i] + " punti");
        }
    }
    
    private void checkCartaVincente(){
        Carta vincente = Estratte.get(0);
        
        for (int i = 1; i < Estratte.size() - 1; i++){
            if (Estratte.get(i).batte(vincente)){
                vincente = Estratte.get(i);
            }
        }
        punteggioGiocatori[Estratte.indexOf(vincente)]++;
        System.out.println("Punto a giocatore" + Estratte.indexOf(vincente));
        
    }
    
    public synchronized boolean pescaCarta(int turnoGiocatore) throws InterruptedException{
        while (turnoGiocatore != turnoAttuale && numeroGiri > 0){
            //System.out.println(Thread.currentThread().getName() + ": mi blocco non è il mio turno <"+turnoGiocatore+">, turno attuale: " + turnoAttuale);
            wait();
        }
        nGiocatoriTurno++;

        
        if (numeroGiri == 0)
            return true;        
        
        // estraggo la carta
        
        Carta c = Mazzo.removeFirst();
        
        Estratte.add(c);
        System.out.println(Thread.currentThread().getName() + ": ho pescato " + c.toString());
        turnoAttuale++;
        
        //System.out.println("DEBUG: turnoAttuale: " + turnoAttuale + ", nGiocatoriTurno: " + nGiocatoriTurno + ", numeroGiri: " + numeroGiri);
        
        if (nGiocatoriTurno == numeroGiocatori){
            // sono l'ultimo
            numeroGiri--;
            //System.out.println(Thread.currentThread().getName() + ": sono l'ultimo devo checckare");
            checkCartaVincente();
            turnoAttuale = 0;
            nGiocatoriTurno = 0;
            Estratte.clear();
        }
       
        notifyAll();
        return false;

    }
    
    
}
