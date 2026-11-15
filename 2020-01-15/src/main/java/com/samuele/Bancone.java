package com.samuele;

public class Bancone {
    private static final long TIMEOUT = 3000;

    private Ingrediente ingredienteSulTavolo = Ingrediente.NIENTE;
    private long istanteDeposito;

    // voluto == NIENTE: il barista accetta qualunque ingrediente
    public synchronized Ingrediente prendiIngrediente(Ingrediente voluto) throws InterruptedException {
        while (ingredienteSulTavolo == Ingrediente.NIENTE
                || (voluto != Ingrediente.NIENTE && voluto != ingredienteSulTavolo)) {
            wait();
        }
        Ingrediente preso = ingredienteSulTavolo;
        ingredienteSulTavolo = Ingrediente.NIENTE;
        System.out.println(Thread.currentThread().getName() + ": ho preso " + preso);
        notifyAll();
        return preso;
    }

    public synchronized void inserisciIngrediente(Ingrediente daInserire) throws InterruptedException {
        while (ingredienteSulTavolo != Ingrediente.NIENTE) {
            System.out.println(Thread.currentThread().getName() + ": il bancone non è vuoto, mi blocco.");
            long rimanente = istanteDeposito + TIMEOUT - System.currentTimeMillis();
            if (rimanente > 0) {
                wait(rimanente); // può svegliarsi prima (notify o risveglio spurio): si ricontrolla
                continue;
            }
            // l'ingrediente è sul bancone da più di 3 secondi
            System.out.println(Thread.currentThread().getName()
                    + ": nessuno può andare avanti, levo l'ingrediente (" + ingredienteSulTavolo + ")");
            System.out.println(Thread.currentThread().getName() + ": ero bloccato, cambio ingrediente.");
            daInserire = ingredienteSulTavolo.altro();
            break;
        }
        ingredienteSulTavolo = daInserire;
        istanteDeposito = System.currentTimeMillis();
        System.out.println(Thread.currentThread().getName() + ": ho inserito " + ingredienteSulTavolo);
        notifyAll();
    }
}
