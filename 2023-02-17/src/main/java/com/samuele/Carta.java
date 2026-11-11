package com.samuele;

public class Carta {
    private int numeroCarta;
    private Seme semeCarta;

    public Carta(int n, Seme s){
        numeroCarta = n;
        semeCarta = s;
    }

    public int getNum() {return  numeroCarta;}

    public Seme getSeme() {return semeCarta;}

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (!(o instanceof Carta)) return false;
        Carta altra = (Carta) o;
        return numeroCarta == altra.numeroCarta && semeCarta == altra.semeCarta;
    }

    @Override
    public int hashCode(){
        return 31 * numeroCarta + semeCarta.hashCode();
    }

    @Override
    public String toString(){
        return numeroCarta + "" + semeCarta;
    }

}
