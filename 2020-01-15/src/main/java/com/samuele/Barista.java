package com.samuele;

public class Barista extends Thread{
    Ingrediente [] ingredienti;
    Ingrediente cosaVoglio;
    int ingredientiPresi;
    Bancone b;

    public Barista(String m, Bancone b){
        super(m);
        this.b = b;
        cosaVoglio = Ingrediente.NIENTE;
        ingredientiPresi = 0;
        ingredienti = new Ingrediente[2];
        for (int i = 0; i<2;i++){
            ingredienti[i] = Ingrediente.NIENTE;
        }
    }

    public void run(){
        while (true) {
            try {
                
                // cerco di prendere un ingrediente
                ingredienti[ingredientiPresi] = b.prendiIngrediente(cosaVoglio);
                if (ingredienti[ingredientiPresi] == Ingrediente.CAFFE)
                    cosaVoglio = Ingrediente.LATTE;
                else
                    cosaVoglio = Ingrediente.CAFFE;
                ingredientiPresi++;

                if (ingredientiPresi == 2){
                    System.out.println(getName() + ": ho preso " + ingredienti[0] + " e poi il " + ingredienti[1] + ": cappuccino!!!");
                    ingredientiPresi = 0;
                    cosaVoglio = Ingrediente.NIENTE;
                    sleep((int)(Math.random() * 1000));
                }

            } catch (InterruptedException e) {
                System.out.println("Eccezione catturata");
            }
        }
    }


}
