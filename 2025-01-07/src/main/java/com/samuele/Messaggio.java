package com.samuele;

public class Messaggio {
    boolean urg;

    public Messaggio(boolean urg){
        this.urg = urg;
    }

    public boolean isUrgente() {return urg;}

    @Override
    public String toString() {
        return urg ? "URGENTE" : "NORMALE";
    }


}
