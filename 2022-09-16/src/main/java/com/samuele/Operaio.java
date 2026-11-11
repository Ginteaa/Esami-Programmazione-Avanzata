package com.samuele;

public class Operaio extends Thread{
    Parcheggio p;
    private int numeroAutoLavate;
    private int numeroAutoDaLavare;

    public Operaio(Parcheggio p, String m){
        super(m);
        this.p = p;
        numeroAutoLavate = 0;
    }

    public void run(){
        while(true){
            try {
                
                p.lava();
                numeroAutoLavate++;
                if (numeroAutoLavate == 3){
                    System.out.println(getName() + ": mi riposo per 10 sec");
                    sleep(2000);
                    numeroAutoLavate = 0;
                }
                numeroAutoDaLavare = p.getAutoDaLavare();
                if (numeroAutoDaLavare == 0)
                    break;


            } catch (InterruptedException e) {
                // TODO: handle exception
                System.out.println("Eccezione catturara");
            }



        }
    }

}
