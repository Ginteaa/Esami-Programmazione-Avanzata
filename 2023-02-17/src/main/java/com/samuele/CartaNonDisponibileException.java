package com.samuele;

public class CartaNonDisponibileException extends Exception{
    public CartaNonDisponibileException(){
        super();
    }

    public CartaNonDisponibileException(String s){
        super(s);
    }

}
