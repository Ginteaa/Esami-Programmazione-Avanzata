package com.samuele;

/**
 * Hello world!
 */
public class Pizzeria {
    public static void main(String[] args) {
        
        Tavolo t = new Tavolo();
        Pizzaiolo p1 = new Pizzaiolo("Pizzaiolo1",Ingredienti.MOZZARELLA, t);
        Pizzaiolo p2 = new Pizzaiolo("Pizzaiolo2",Ingredienti.POMODORO, t);
        Pizzaiolo p3 = new Pizzaiolo("Pizzaiolo3", Ingredienti.PASTA, t);
        Fornitore f  = new Fornitore(t);

        f.start();
        p1.start();
        p2.start();
        p3.start();
        

    }
}
