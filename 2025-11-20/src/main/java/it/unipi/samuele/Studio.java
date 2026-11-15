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
public class Studio {
    public int numeroCamerini;
    private int numeroCantantiInCamerino;
    private boolean palcoOccupato;
    public boolean [] camerini;
    private List<Thread> cantanti;
    
    private Object camerino = new Object();
    private Object palco = new Object();
    
    public Studio(int n){
        numeroCamerini = n;
        camerini = new boolean[numeroCamerini];
        cantanti = new ArrayList<>();
        for (int i = 0; i < numeroCamerini; i++){
            camerini[i] = false;
        }
        numeroCantantiInCamerino = 0;
        palcoOccupato = false;
    }
    
    public void forseFumo(){
        
        boolean fumo = Math.random() < 0.1;
        
        if (!fumo)
            return;
        
        System.out.println(Thread.currentThread().getName() + ": fumo una sigaretta in camerino, parte l'allarme antincendio!");
        // fumo, devo svegliare tuttu gli altri thread in camerino
        
        for (Thread t : cantanti)
            t.interrupt();
        cantanti.clear();
        
        numeroCantantiInCamerino = 0;
        for (int i = 0; i < numeroCamerini; i++) {
            camerini[i] = false;
        }
        
        
        
    }
    
    public int prendiCamerino() throws InterruptedException{
        synchronized (camerino){
            while (numeroCantantiInCamerino == numeroCamerini){
                camerino.wait();
            }
            
            int camerinoPreso = 0;
            for (int i = 0; i < numeroCamerini; i++){
                if (!camerini[i]){
                    camerini[i] = true;
                    camerinoPreso = i;
                    break;
                }
            }
            
            System.out.println(Thread.currentThread().getName() + ": entro in camerino");
            cantanti.add(Thread.currentThread());
            numeroCantantiInCamerino++;
            camerino.notifyAll();
            return camerinoPreso;
        }   
    }
    
    public void lasciaCamerino(int indiceCamerino){
        synchronized(camerino){
            
            camerini[indiceCamerino] = false;
            numeroCantantiInCamerino--;
            System.out.println(Thread.currentThread().getName() + ": esco dal camerino");
            cantanti.remove(Thread.currentThread());
            camerino.notifyAll();
        }
    }
    
    public void saliPalco() throws InterruptedException{
        synchronized(palco){
            while(palcoOccupato){
                palco.wait();
            }
            
            palcoOccupato = true;
            System.out.println(Thread.currentThread().getName() + ": salgo sul palco");
            palco.notifyAll();
        }
    }
    
    public void scendiPalco() throws InterruptedException{
        synchronized(palco){
            palcoOccupato = false;
            System.out.println(Thread.currentThread().getName() + ": scendo dal palco");
            palco.notifyAll();
        }
    }
    
    
}
