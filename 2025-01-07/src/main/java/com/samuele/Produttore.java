package com.samuele;

public class Produttore extends Thread {
    double prob;
    Deposito d;
    
    public Produttore(String m, double prob, Deposito d){
        super(m);
        this.prob = prob;
        this.d = d;
    }

    public void run(){
        while (true) {
            try {
                boolean isUrgente = Math.random() < prob;  // prob di tipo double, passi 0.5

                //System.out.println(getName() + "isUrgente " + isUrgente);

                Messaggio m;
                if (isUrgente)
                    m = new Messaggio(true);
                else
                    m = new Messaggio(false);

                d.inserisci(m);

                int timeSleep = (int) (Math.random() * 1000);
                sleep(timeSleep);

            } catch (InterruptedException e) {
                // TODO: handle exception
                System.out.println("Eccezione catturata");
            }
            


        }
    }

}
