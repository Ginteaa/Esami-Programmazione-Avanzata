package com.samuele;

public class Lancia {
    public static void main(String[] args) {
        
        String hash = "xItN9WWwyW+E/t8Y8mWW7UCqn0bxECGvcSXTTR06z/4=";
        int numWorker = 4;
        String[] parole = {"abc", "qwerty", "pluto", "pippo", "ingegneria", "informatica"};
        
        Cracker c = new Cracker(hash);
        c.controlla(parole, numWorker);
        try {
            c.stampaRisultato();
        } catch (InterruptedException e) {
            System.out.println("Eccezione catturata");
        }
        
        
        


    }
}