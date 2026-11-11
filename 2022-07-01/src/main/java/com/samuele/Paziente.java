package com.samuele;

public class Paziente extends Thread{
    int codice;
    SalaAttesa sa;

    public Paziente(int cod, SalaAttesa sa){
        codice = cod;
        this.sa = sa;
    }

    public void run(){
        try {

            sa.entrata(codice);

            int timeSleep = (int) ((Math.random() * 2) + 1);
            sleep(timeSleep);

            sa.uscita();

            
        } catch (InterruptedException e) {
            System.out.println("Eccezzione catturata");
        }
    }


}
