package com.samuele;

public class Tavolo {
    private Ingredienti ingredienti_tavolo [];
    private boolean pieno;
    
    public Tavolo(){
        ingredienti_tavolo = new Ingredienti[2];
        pieno = false;
    }


    private  boolean getIngrediente (Ingredienti ii){
        if (ingredienti_tavolo[0] != ii && ingredienti_tavolo[1] != ii) 
            return true;

        return false;
    }

    public synchronized void setIngredienti(Ingredienti i1, Ingredienti i2) throws InterruptedException{

        while (pieno){
            wait();
        }

        ingredienti_tavolo[0] = i1;
        ingredienti_tavolo[1] = i2;

        pieno = true;

        notifyAll();
    }


    public synchronized Ingredienti [] getIngredienti(Ingredienti ii) throws InterruptedException {
        
        while (!pieno || !getIngrediente(ii)){
            wait();
        }

        Ingredienti [] presi = {ingredienti_tavolo[0], ingredienti_tavolo[1]};
        pieno = false;
        notifyAll();

        return presi;


    }

}
