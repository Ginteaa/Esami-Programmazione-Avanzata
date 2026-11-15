package com.samuele;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Bancone b = new Bancone();
        new Fornitore("Fornitore", b).start();
        for (int i=0;i<3;i++){
            new Barista("Barista" + i, b).start();
        }

    }
}
