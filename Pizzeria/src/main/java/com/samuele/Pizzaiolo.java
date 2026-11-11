package com.samuele;

public class Pizzaiolo extends Thread{
    Ingredienti ii;
    Tavolo t;

    public Pizzaiolo(String s, Ingredienti ii, Tavolo t){
        super(s);
        this.ii = ii;
        this.t = t;
    }

    private void creaPizza(Ingredienti [] presi){
        System.out.println(getName() + " con ingrediente: " + ii + " , ingredienti: " + presi[0] + ", " +  presi[1] );
    }

    public void run(){
        try {
            while (true){
                Ingredienti [] presi = t.getIngredienti(ii);
                // ho tutti gli ingredienti
                creaPizza(presi);

                sleep(100);
            }
        } catch (InterruptedException e){
            System.out.println("Sono stato interroto");
        }
    }

}
