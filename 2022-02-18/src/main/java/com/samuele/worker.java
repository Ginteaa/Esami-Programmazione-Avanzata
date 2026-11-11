package com.samuele;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class worker extends Thread{
    Cracker c;
    String toAnalize;
    String HashPasswordUser;

    boolean found;

    public worker(String hpu, Cracker c){
        HashPasswordUser = hpu;
        this.c = c;
        found = false;
    }

    public String getPassword(){
        return toAnalize;
    }

    public boolean getFound(){
        return found;
    }


    public void run(){
        toAnalize = "";
        while (true) {
        
            toAnalize = c.getStringToAnalize();
            if (toAnalize.equals("\0"))
                    break;
            System.out.println(getName() + ": analizzo " + toAnalize);

            try {
                String h2 = Hash.hash(toAnalize);
                
                if (h2.equals(HashPasswordUser)){
                    //System.out.println(getName() + ": entro");
                    c.setFound(toAnalize);
                }
            }
            catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
                System.out.println("Errore nella hash");

            }
        }
        c.WorkerFinito();

    }

}
