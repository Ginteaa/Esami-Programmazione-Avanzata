package com.samuele;

import java.util.ArrayList;
import java.util.List;

public class Produttore extends Thread{
    int start;
    int end;
    Logger l;

    public Produttore(int start, int end, Logger l){
        this.start = start;
        this.end = end;
        this.l = l;
    }


    public void run(){
        int numeroM = end - start;
        List<Integer> inseriti = new ArrayList<>();
        while(numeroM > 0){
            try {
                
                int cod = -1;
                do {
                    cod = (int) ((Math.random() * (end - start)) + start);
                } while (inseriti.contains(cod));

                inseriti.add(cod);
                

                String stringaDaBufferizzare = "AAA";
                l.log(stringaDaBufferizzare, cod);

                numeroM--;

            } catch (Exception e) {
                System.out.println(getName() + ": eccezzione catturata");
            }
        }
            
    }

}
