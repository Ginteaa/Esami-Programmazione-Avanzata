package com.samuele;

public class Bancone {
    Ingrediente ingredienteSulTavolo;
    
    public Bancone(){
        ingredienteSulTavolo = Ingrediente.NIENTE;
    }

    public synchronized Ingrediente prendiIngrediente(Ingrediente RichiestaCliente) throws InterruptedException {

        while ((RichiestaCliente != ingredienteSulTavolo && RichiestaCliente != Ingrediente.NIENTE) || ingredienteSulTavolo == Ingrediente.NIENTE) {
            wait();
        }

        System.out.println(Thread.currentThread().getName() + ": ho preso " + ingredienteSulTavolo);
        
        Ingrediente daRestituire = ingredienteSulTavolo;
        ingredienteSulTavolo = Ingrediente.NIENTE;
        notifyAll();
        return  daRestituire;
    }

    public synchronized void inserisciIngrediente(Ingrediente IngredienteDaInserire) throws InterruptedException{
        Ingrediente check = ingredienteSulTavolo;
        while (ingredienteSulTavolo != Ingrediente.NIENTE) {
            System.out.println(Thread.currentThread().getName() + ": il bancone non è vuoto, mi blocco");

            wait(3000);

            if (check.equals(ingredienteSulTavolo)){
                System.out.println(Thread.currentThread().getName() + ": nessuno può andare avanti, levo l'ingrediente (" + ingredienteSulTavolo +")");
                System.out.println(Thread.currentThread().getName() + ": ero bloccato, cambio ingrediente");
                if (IngredienteDaInserire.equals(ingredienteSulTavolo)){
                    if (IngredienteDaInserire == Ingrediente.CAFFE)
                        IngredienteDaInserire = Ingrediente.LATTE;
                    else 
                        IngredienteDaInserire = Ingrediente.CAFFE;
                }
                break;
            }
        }
        

        ingredienteSulTavolo = IngredienteDaInserire;
        System.out.println(Thread.currentThread().getName() + ": ho inserito " + ingredienteSulTavolo);
        notifyAll();

    }

    
  


}
