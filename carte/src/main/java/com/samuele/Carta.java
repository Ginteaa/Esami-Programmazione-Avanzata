package com.samuele;

public class Carta {

    private Seme semeCarta;
    private int numero;

    public Carta (int numero, Seme s){
        this.numero = numero;
        this.semeCarta = s;
    }

    @Override
    public String toString(){
        return "Carta " + this.numero + " " +  this.semeCarta;
    }

    public boolean batte (Carta c){
        if (numero > c.numero) 
            return true;
        else if (numero == c.numero ){
            int a = semeCarta.ordinal();
            int b = c.semeCarta.ordinal();
            
            if (a < b)
                return  true;
            else
                return false;

        }         
        else 
            return false;
    }

}
