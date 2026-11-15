/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unipi.samuele;

/**
 *
 * @author samueleinterrante
 */
public class Cantante extends Thread{
    Studio s;
    
    public Cantante(String m, Studio s){
        super(m);
        this.s = s;
    }
    
    
    public void run(){
        boolean finito = false;
        while (!finito){
            try{
               
                int timeCamerino = (int) (Math.random() * 1000);
                int indiceCamerino = s.prendiCamerino();
                
                System.out.println(getName() + ": mi preparo in camerino per " + timeCamerino + "ms");
                
                s.forseFumo();
                
                sleep(timeCamerino);
                             
                s.lasciaCamerino(indiceCamerino);
                
                int timePalco = (int) (Math.random() * 1000);
                s.saliPalco();
                System.out.println(getName() + ": mi esibisco sul palco per " + timePalco + "ms");
                
                s.scendiPalco();
                finito = true;
                
            } catch(InterruptedException e){
                System.out.println(getName() + ": sono stato interroto");
            }              
        }
        System.out.println(getName() + ": ho terminato");
    }

    
    
}
