package com.samuele;

public class Produttore extends Thread{
    String tipoDiProduttore;
    Deposito d;

    public Produttore(String m ,String tipo, Deposito d){
        super(m);
        tipoDiProduttore = tipo;
        this.d = d;
    }

    public void run(){
        while(true){
            try {
                Pezzo p = new Pezzo(tipoDiProduttore);

                d.inserisci(p);

                int timeSleepProduttore;
                timeSleepProduttore = (int) (Math.random() * 1000);
                System.out.println(getName() + ": mi metto a dormire per " + timeSleepProduttore);
                sleep(timeSleepProduttore);        
                        
            } catch (InterruptedException e) {
                System.out.println("Eccezione catturata, " + e.getMessage());
            }


        }
    }


}
