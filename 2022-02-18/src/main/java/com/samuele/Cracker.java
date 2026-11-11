package com.samuele;

public class Cracker {
    String HashPasswordUser;
    int indexStringToAnalize;
    String [] s;
    boolean found;
    String password;
    int WorkerAttivi;


    public Cracker(String h){
        HashPasswordUser = h;
        indexStringToAnalize = 0;
        found = false;
    }

    public synchronized void setFound(String p){
        //System.out.println(Thread.currentThread().getName() + ": sono entrato");
        found = true;
        password = p;
    }

    public void controlla(String [] s, int numThreads){
        this.s = s;
        WorkerAttivi = numThreads;
        for (int i=0; i < numThreads; i++){
            worker t = new worker(HashPasswordUser, this);
            t.start();
        }


    }

    public synchronized String getStringToAnalize(){
        String toReturn;
        if (indexStringToAnalize >= s.length || found){
            toReturn = "\0";        
            return toReturn;

        }

        toReturn = s[indexStringToAnalize++];
        notifyAll();
        return toReturn;
    } 

    public synchronized void WorkerFinito(){
        WorkerAttivi--;
        notifyAll();
    }

    public synchronized void stampaRisultato() throws InterruptedException{
        while (WorkerAttivi > 0) {
            wait();
        }
    
        if (found){
            System.out.println("La password è: " + password);
        } else {
            System.out.println("Nessuna della parole è la password");
        }
    }


}
