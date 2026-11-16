/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.samuele;

/**
 *
 * @author samueleinterrante
 */
public class Carta {
    int numeroCarta;
    Seme semeCarta;
    
    public Carta(int nC, Seme sC){
        numeroCarta = nC;
        semeCarta = sC;
    }
    
    
    public String toString(){
        return numeroCarta + semeCarta.toString();
    }
    
    public boolean batte(Carta c){
        if (this.numeroCarta > c.numeroCarta){
            return true;
        } else if (this.numeroCarta == c.numeroCarta){
            if (this.semeCarta.ordinal() > c.semeCarta.ordinal()){
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    
}
