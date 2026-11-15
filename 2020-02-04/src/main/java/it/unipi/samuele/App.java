/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package it.unipi.samuele;

/**
 *
 * @author samueleinterrante
 */
public class App {

    public static void main(String[] args) {
        Treno t = new Treno(15);
        for (int i = 0; i<20; i++){
            new Cliente(t, 10).start();
        }
    }
}
