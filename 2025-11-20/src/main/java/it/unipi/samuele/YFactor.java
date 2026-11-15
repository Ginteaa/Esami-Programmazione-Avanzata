/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unipi.samuele;

/**
 *
 * @author samueleinterrante
 */
public class YFactor {
    public static void main(String[] args){
        Studio s = new Studio(2);
        new Cantante("FabriCrusca", s).start();
        new Cantante("FrancoAltaEnergia", s).start();
        new Cantante("MarescialloIII", s).start();
        new Cantante("GuePiccolino", s).start();
    }
}
