/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.samuele;

/**
 *
 * @author samueleinterrante
 */
public enum Seme {
    PICCHE,
    FIORI,
    QUADRI,
    CUORI;
    
    public String toString(){
        switch(this){
            case CUORI:
                return "C";
            case QUADRI:
                return "Q";
            case FIORI:
                return "F";
            case PICCHE:
                return "P";
            default:
                return "";
        }
    }
    
}
