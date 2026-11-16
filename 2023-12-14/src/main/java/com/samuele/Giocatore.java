/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.samuele;

/**
 *
 * @author samueleinterrante
 */
public class Giocatore extends Thread {
    Tavolo t;
    int turnoGiocatore;
    
    public Giocatore(String s,Tavolo t, int turno){
        super(s);
        this.t = t;
        turnoGiocatore = turno;
    }
    
    
    public void run(){
        boolean finito = false;
        while (!finito){
            
            try{
                // pesco la carta
                finito = t.pescaCarta(turnoGiocatore);
                
            } catch (InterruptedException e){
                
            }
            
        }
        
        
    }
    
}
