package com.samuele;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Parcheggio p = new Parcheggio(5);
        Operaio op = new Operaio(p, "operaio");
        op.start();
        for (int i = 0; i < 10; i++){
            new Auto(p).start();
        }
    }
}
