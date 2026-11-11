package com.samuele;

public class Cliente extends Thread{
    Traghetto t;
    boolean postoSelezionato;
    boolean prenotazioneEffettuta;
    int numeroPianoTraghetto;

    Cliente(Traghetto t, int nP){
        this.t = t;
        numeroPianoTraghetto = nP;
    }

    public void run(){
        
        try {
            int piano = (int) (Math.random() * numeroPianoTraghetto);
            //System.out.println(getName() + " : piano che voglio selezionare " + piano);

            t.visualizza(piano);

            int posto = (int) ((Math.random() * 60));
            System.out.println(getName() + ": voglio selezionare il posto " + posto + " al piano " + piano);
            postoSelezionato = t.seleziona(piano, posto);

            int time = (int) (Math.random() * 4000);
            sleep(time);

            prenotazioneEffettuta = t.paga(piano, posto);

            if (prenotazioneEffettuta){
                System.out.println(getName() + ": ho pagato il posto " + posto + " al piano " + piano);
            } else {
                System.out.println(getName() + ": non ho prenotato il posto");
            }
        System.out.println(getName() + ": termino");            
        } catch (InterruptedException e) {
            System.out.println("Eccezzione catturata");
        }

    }


}
