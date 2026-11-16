/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.samuele;

/**
 *
 * @author samueleinterrante
 */
public class Image {
    int [][] red;
    int [][] blue;
    int [][] green;
    int row;
    int column;

    public Image(int r, int c, int [][] red, int [][] blue, int [][] green) {
        row = r;
        column = c;
        this.red = red;
        this.blue = blue;
        this.green = green;
    }
    
    
    
}
