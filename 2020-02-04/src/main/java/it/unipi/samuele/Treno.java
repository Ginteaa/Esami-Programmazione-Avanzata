/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unipi.samuele;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author samueleinterrante
 */
public class Treno {
    int numeroPosti;
    private int numeroPrenotazione;
    List<Integer> treno;
    
    public Treno(){
        numeroPosti = 100;
        numeroPrenotazione = 0;
        treno = new ArrayList<>();
    }
    public Treno(int q){
        numeroPosti = q;
        numeroPrenotazione = 0;
        treno = new ArrayList<>();
    }
    
    public synchronized int prenota(int q, long t) throws InterruptedException, PrenotazioneFallitaException{
        long start = System.currentTimeMillis();
        while (numeroPosti < q){
            System.out.println(Thread.currentThread().getName() +  ": mi blocco per " + t + "ms, voglio prenatore " + q + " posti, ce ne sono disponibili: " + numeroPosti);
            long rimanente = start + t - System.currentTimeMillis();
            if (rimanente > 0)
                wait(t);
            else
                throw new PrenotazioneFallitaException();
        }
        
        treno.add(q);
        numeroPosti -= q;
        System.out.println(Thread.currentThread().getName() + ": ho prenotato " + q + " posti <" + numeroPrenotazione + ">, disponibili: " + numeroPosti);
        notifyAll();
        return numeroPrenotazione++;   
    }
    
    public synchronized void disdici(int numeroPrenotazione){
        System.out.println(Thread.currentThread().getName() + ": disdico la prenotazione <" + numeroPrenotazione + ">");
        
        int postiDaAggiungere = treno.get(numeroPrenotazione);
        treno.set(numeroPrenotazione, -1);
        numeroPosti += postiDaAggiungere;
        notifyAll();
    }
    
    
    
    
}

