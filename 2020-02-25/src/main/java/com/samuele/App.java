package com.samuele;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Logger l = new Logger("prova.txt", 5);
        for (int i = 0; i < 10; i++){
            new Produttore(0, 10, l).start();
        }
    }
}
