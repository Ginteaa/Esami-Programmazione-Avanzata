package com.samuele;

public class Pallina extends Thread{
    int valore;
    Roulette r;
    private final long TIME = 100;
    long timeSpin;

    public Pallina(Roulette r, long timeSpin){
        this.r = r;
        this.timeSpin = timeSpin;
    }

    public void run(){
        long deadline = System.currentTimeMillis() + timeSpin;
        while (deadline > System.currentTimeMillis()) {

            try {
                valore = (int) (Math.random() * 36);
                System.out.println(valore);
                sleep(TIME);      
            } catch (InterruptedException e) {
                System.out.println("Eccezione catturata");
            }

        }
    }

    public int getValore(){return valore;}

}
