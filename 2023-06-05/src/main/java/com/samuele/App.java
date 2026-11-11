package com.samuele;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        
        Deposito d = new Deposito(5);
        Produttore pX = new Produttore("ProduttoreX", "X", d);
        Produttore pY = new Produttore("ProduttoreY", "Y", d);
        Consumatore c = new Consumatore("Consumatore" ,d);
        Controllore contr = new Controllore("Controllore", d);
        pX.start();
        pY.start();
        c.start();
        contr.start();






    }
}
