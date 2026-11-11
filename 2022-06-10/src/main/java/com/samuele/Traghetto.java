package com.samuele;

public class Traghetto {

    int numeroPiani;
    int [] [] Traghetto;
    long [][] timerPosti;

    public Traghetto(int n){
        numeroPiani = n;
        Traghetto = new int[n][60];
        timerPosti = new long[n][60];
        for (int i = 0; i < n; i++){
            for (int j = 0; j < 60; j++){
                Traghetto[i][j] = 0;
                timerPosti[i][j] = 0;
            }
        }
    }


    private void starTimer(int piano, int posto){
        timerPosti[piano][posto]  = System.currentTimeMillis() + 5000; 
    }

    private boolean timerScaduto(int piano, int posto){
        System.out.println(Thread.currentThread().getName() + ": deadline: " + timerPosti[piano][posto]);
        if (System.currentTimeMillis() > timerPosti[piano][posto])
            return false;
        return true;
    }


    public synchronized void visualizza(int n){
        System.out.println(Thread.currentThread().getName() + ": piano che voglio visualizzare: " + n);
        int j = 1;
        for (int i = 0; i < 60; i++){
            switch (Traghetto[n][i]) {
                case 0:
                    System.out.print("L");
                    break;
                case 1:
                    System.out.print("S");
                    break;
                case 2:
                    System.out.print("P");
                    break;
                default:
                    break;
            }
            if (j == 6){
                System.out.println();
                j = 0;
            }
            j++;

        }
        System.out.println();     
        notifyAll();   
    }

    public synchronized boolean seleziona(int piano, int posto){
        
        // posto occupati
        if (Traghetto[piano][posto] != 0)
            return false;
        
        Traghetto[piano][posto] = 1; // posto selezionato
        System.out.println(Thread.currentThread().getName() + ": posto " + posto + " del piano " + piano  + " selezionato");
        starTimer(piano, posto);
        notifyAll();
        return true;

    }

    public synchronized boolean paga(int piano, int posto){

        if (timerScaduto(piano,posto)){
            Traghetto[piano][posto] = 0; // il posto ritorna libero
            timerPosti[piano][posto] = 0; // risetto a 0 il timer
            notifyAll();
            return false;
        }

        Traghetto[piano][posto] = 2;
        System.out.println(Thread.currentThread().getName() + ": pagamento effettuato del posto: " + posto + " al piano " + piano); 

        notifyAll();
        return true;
    }

    



}
