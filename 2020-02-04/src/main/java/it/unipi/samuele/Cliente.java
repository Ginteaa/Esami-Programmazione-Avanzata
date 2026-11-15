/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unipi.samuele;

/**
 *
 * @author samueleinterrante
 */
public class Cliente extends Thread{
    Treno t;
    int numeroPostiTreno;
    
    public Cliente(Treno t, int q){
        this.t = t;
        numeroPostiTreno = q;
    }
    
    public void run(){
        try{
            int MaxWait = (int) (Math.random() * 1000);
            int postiDaPrenotare = ((int) (Math.random() * numeroPostiTreno)) + 1;
            
            int codicePrenotazione = t.prenota(postiDaPrenotare, MaxWait);   
            
            boolean disdico = Math.random() < 0.5;
            if (disdico)
                t.disdici(codicePrenotazione);

        } catch (InterruptedException e){
            System.out.println(getName() + ": eccezione catturata");
        } catch(PrenotazioneFallitaException e){
            System.out.println(getName() + ": prenotazione fallita");
        } finally{
            System.out.println(getName() + ": termino");
        }

    }
    
    
}
