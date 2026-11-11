package com.samuele;

public class Blocco {
    private int idBlocco;
    boolean preso;

    public Blocco(int id){
        idBlocco = id;
        preso = false;
    }

    public int getIdBlocco() {return idBlocco;}

    public boolean getPreso() {return preso;}

    public void ifPreso() {
        if (preso)
            preso = false;
        else 
            preso = true;
    }

}
