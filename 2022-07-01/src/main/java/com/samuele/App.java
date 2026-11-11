package com.samuele;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        try {
            SalaAttesa sa = new SalaAttesa(5, 2);   
            for (int i = 0; i < 10; i++){
                int cod = (int) (Math.random() * 5);
                new Paziente(cod, sa).start();
            }

        } catch (Exception e) {
            System.out.println("Ececzione catturata");
        }
    }
}
