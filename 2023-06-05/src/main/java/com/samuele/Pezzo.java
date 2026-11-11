package com.samuele;

public class Pezzo {
    float LivelloQualita;
    String TipoPezzo;

    public Pezzo(String tipo){
        TipoPezzo = tipo;
        LivelloQualita = (float) (Math.random());
    }

    public String getTipo() {return TipoPezzo;}

    public double getLivelloQualità() {return LivelloQualita;}

    @Override 
    public String toString(){
        return "(" + LivelloQualita + ", " + TipoPezzo + ")";
    }

}
