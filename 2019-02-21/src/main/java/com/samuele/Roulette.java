package com.samuele;

public class Roulette {
    long tempoMin;
    long tempoMax;
    boolean betting;
    boolean timeUP;


    int pallina;

    public Roulette(){
        tempoMin = 1000;
        tempoMax = 4000;
        pallina = -1;
        timeUP = false;
    }

    public Roulette(int tempoMin, int tempoMax){
        this.tempoMin = tempoMin;
        this.tempoMax = tempoMax;
        pallina = -1;
        timeUP = false;
    }

    void avvia() throws InterruptedException {
        betting = true;
        long timeSpin = (long) (Math.random() * (tempoMax - tempoMin) + tempoMin);
        System.out.println("Tempo di spin: " + timeSpin);

        Pallina p = new Pallina(this, timeSpin);
        p.start();
        new Routa(this, timeSpin / 2).start();
        p.join();
        timeUP = true;

        pallina = p.getValore();
        synchronized(this){
            this.notifyAll();
        }
    }

    public synchronized void stopBet(){ 
        betting = false;
        System.out.println("Rien ne va plus, les jeux sont faits!");
    }

    public synchronized boolean puntata(int n) throws PuntateNonPossibiliException, InterruptedException{
        System.out.println(Thread.currentThread().getName() + ": punto sul " + n);
        if (timeUP == true || !betting)
            throw new PuntateNonPossibiliException();

        while (!timeUP && betting) {
            wait();
        }

        if (n == pallina)
            return true;
        else
            return false;
    }
}
