package com.samuele;

public class Lettore extends Thread{
    Contenitore c;

    public Lettore(String m, Contenitore c){
        super(m);
        this.c = c;
    }

    @Override 
    public void run(){
        try {

            for (int i = 0; i < 1000; i++){
                int num = (int)(Math.random() * 1000);
                boolean trovato = c.verifica(num);
                if (trovato){
                    System.out.println(getName() + ": ho trovato il valore: " + num + " nel contenitore");
                } else {
                    System.out.println(getName() + ": non ho trovato il valore: " + num + " nel contenitore");
                }
                    
            }
            
        } catch (InterruptedException e) {
            System.out.println(getName() + ": eccezione catturata");
        }        
    }

}
