package com.samuele;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Deposito d = new Deposito(3,3);
        for (int i = 0; i < 5; i++){
            new Produttore("Produttore " + (i + 1), 0.5, d).start();
            new Consumatore("Consumatore " + (i + 1), 2, d).start();
        }



    }
}
