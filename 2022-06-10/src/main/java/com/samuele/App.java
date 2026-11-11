package com.samuele;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Traghetto t = new Traghetto(4);
        System.out.println(System.currentTimeMillis());
        for (int i = 0; i<10;i++){
            Cliente c = new Cliente(t, 4);
            c.start();
        }
    }
}
