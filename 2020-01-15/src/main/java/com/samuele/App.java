package com.samuele;

public class App {
    public static void main(String[] args) {
        Bancone b = new Bancone();
        new Fornitore("Fornitore", b).start();
        for (int i=0;i<3;i++){
            new Barista("Barista " + (i + 1), b).start();
        }

    }
}
