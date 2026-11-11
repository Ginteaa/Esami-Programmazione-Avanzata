package com.samuele;

public class Consumatore extends Thread{

    Pezzo [] ProdottoFinito;
    Deposito d;

    public Consumatore(String m, Deposito d){
        super(m);
        this.d = d;
    }

    public void run(){
        while (true) {
            try {
                ProdottoFinito = d.estrai();

                int timeSleepConsumatore;
                timeSleepConsumatore = (int) (Math.random() * 1000);
                System.out.println(getName() + ": mi metto a dormire per " + timeSleepConsumatore);
                sleep(timeSleepConsumatore);                 

            } catch (InterruptedException e) {
                // TODO: handle exception
            }
        }
    }


}
