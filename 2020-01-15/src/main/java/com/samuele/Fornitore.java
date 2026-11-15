package com.samuele;

public class Fornitore extends Thread {
    private final Bancone bancone;

    public Fornitore(String nome, Bancone bancone) {
        super(nome);
        this.bancone = bancone;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Ingrediente scelto = Math.random() < 0.5 ? Ingrediente.CAFFE : Ingrediente.LATTE;
                bancone.inserisciIngrediente(scelto);
            }
        } catch (InterruptedException e) {
            System.out.println(getName() + ": interrotto");
        }
    }
}
