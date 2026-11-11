package com.samuele;

public class Prenotazione  {
    int numeroUnivocoCliente;
    int numeroPiazzoleOccupateCliente;

    public  Prenotazione(int nU, int nP){
        numeroUnivocoCliente = nU;
        numeroPiazzoleOccupateCliente = nP;
    }

    public int get_nU(){ return numeroUnivocoCliente;}

    public int get_nP(){ return numeroPiazzoleOccupateCliente;}


}