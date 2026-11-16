package com.samuele;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Contenitore c = new Contenitore(2000, 1000, 1000);
        for (int i = 0; i < 3; i++){
            new Scrittore("Scrittore", c).start();
            new Lettore("Lettore" + i, c).start();
        }
    }
}
