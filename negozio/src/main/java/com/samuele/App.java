package com.samuele;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
     
        try {
            Negozio n = new Negozio(3, 2);

            for (int i=0; i<10; i++){
                Cliente c;
                if (i < 5){
                    c = new Cliente(false, n);
                }
                else {
                    c = new Cliente(true, n);
                }
                c.start();
            }


        } catch (ParametriErratiException e) {
            // TODO: handle exception
            System.out.println("Errore nell'inserimento dei parametri");
        }

    }
}
