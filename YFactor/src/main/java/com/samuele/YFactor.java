package com.samuele;

/**
 * Hello world!
 */
public class YFactor {
    public static void main(String[] args) {
        
        Studio s = new Studio(10);
        for (int i=0; i < 20; i++){
            new Cantante("Cantante" + i, s).start();
        }

        



    }
}
