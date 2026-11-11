package com.samuele;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        
        Campeggio c = new Campeggio(20);
        for (int i = 0; i < 5; i++){
            int n = (int) (Math.random() * 20) + 1;
            Cliente cl = new Cliente(n, c);
            cl.start();
        }

    }
}
