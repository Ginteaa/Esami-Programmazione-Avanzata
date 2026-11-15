package com.samuele;

public class Fornitore extends Thread{
    Bancone b;

    public Fornitore(String m, Bancone b){
        super(m);
        this.b = b;
    }


    public void run(){
        while (true) {
            try {
                
                Ingrediente IngredienteDaInserire;
                boolean scelta = Math.random() < 0.5;
                if (scelta)
                    IngredienteDaInserire = Ingrediente.CAFFE;
                else
                    IngredienteDaInserire = Ingrediente.LATTE;

                b.inserisciIngrediente(IngredienteDaInserire);

                sleep((int)(Math.random() * 1000));


            } catch (InterruptedException e) {
                System.out.println("Eccezoine catturata");
            }
        }
    }

}
