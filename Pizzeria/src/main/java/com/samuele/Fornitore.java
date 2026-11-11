package com.samuele;

public class Fornitore extends Thread {
    //private Ingredienti ingredienti_fornitore [];
    Tavolo t;

    public Fornitore(Tavolo t){
        this.t = t;
    }


    public void run(){
        try {
            while (true) {

                // genero un numero tra 0 e 2
                int int1 = -1;
                int int2 = -1;
                while (int1 == int2) {
                    int1 = (int) (Math.random() * 3);
                    int2 = (int) (Math.random() * 3);
                }

                Ingredienti [] a = Ingredienti.values();

                Ingredienti i1 = a[int1];
                Ingredienti i2 = a[int2];

                t.setIngredienti(i1, i2);

                sleep(10);

            }
            
        } catch (InterruptedException e) {
            // TODO: handle exception
            System.out.println("Sono stato interrotto");
        }
    }



}
