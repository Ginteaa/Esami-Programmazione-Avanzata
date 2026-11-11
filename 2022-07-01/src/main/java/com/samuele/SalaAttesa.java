package com.samuele;

public class SalaAttesa {
    int N;
    int K;
    int numeroPazientiInSala;
    boolean salaChiusa;
    int numeroPazientiPrioritariAttesa [];

    public SalaAttesa(int N, int K) throws ParametriErratiException{

        if (K < 0 || K >= N){
            throw new ParametriErratiException();
        }

        this.N = N;
        this.K = K;
        salaChiusa = false;

        numeroPazientiPrioritariAttesa = new int [N];
        for (int i = 0; i < N; i++){
            numeroPazientiPrioritariAttesa[i] = 0;
        }

    }

    private boolean check(int prio){
        // priorità da 1 a 5
        for (int i=0; i   <N;i++){
            // ci sono pazienti prioritari in attesa 
            if (prio > i && numeroPazientiPrioritariAttesa[i] != 0)
                return true;
        }
        return false;

    }

    public synchronized void entrata(int prio) throws InterruptedException{
        numeroPazientiPrioritariAttesa[prio]++;
        while (salaChiusa || check(prio)) {
            System.out.println(Thread.currentThread().getName() + ": con codice " + prio + " in attesa");
            wait();
        }

        numeroPazientiInSala++;
        numeroPazientiPrioritariAttesa[prio]--;
        System.out.println("Paziente: " + Thread.currentThread().getName() + ": con codice " + prio + " entrato");
        if (numeroPazientiInSala == N){
            System.out.println("Sala chiusa");
            salaChiusa = true;
        }

        notifyAll();

    }

    public synchronized void uscita(){
        numeroPazientiInSala--;
        System.out.println("Paziente " + Thread.currentThread().getName() + " uscito");

        if (numeroPazientiInSala == (N - K)){
            salaChiusa = false;
            System.out.println("Sala riaperta");
        }

        notifyAll();

    }


}
