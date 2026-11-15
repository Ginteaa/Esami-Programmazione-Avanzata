package com.samuele;

public class Barista extends Thread {
    private final Bancone bancone;

    public Barista(String nome, Bancone bancone) {
        super(nome);
        this.bancone = bancone;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Ingrediente primo = bancone.prendiIngrediente(Ingrediente.NIENTE);
                Ingrediente secondo = bancone.prendiIngrediente(primo.altro());
                System.out.println(getName() + ": ho preso " + primo + " e poi " + secondo + ": cappuccino!!!");
            }
        } catch (InterruptedException e) {
            System.out.println(getName() + ": interrotto");
        }
    }
}
