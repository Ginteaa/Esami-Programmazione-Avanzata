package com.samuele;

public class App {
    public static void main(String[] args) {
        try {

            GestoreMemoria gm = new GestoreMemoria(16, 2);
            for (int i = 0; i < 10; i++){
                boolean prio = Math.random()  < 0.5;
                Processo p = new Processo(prio, gm);
                p.start();
            }
            
        } catch (IllegalArgumentException e) {
            System.out.println("Eccezzione catturata");

        }
        
    }
}
