package com.samuele;

public class Routa extends Thread{
    long timeBet;
    Roulette r;
    

    public Routa(Roulette r, long timeSpin){
        this.timeBet = timeSpin;
        this.r = r;
    }


    public void run(){
        try {

            sleep(timeBet);
            r.stopBet();

        } catch (Exception e) {
            // TODO: handle exception
        }        





    }



}
