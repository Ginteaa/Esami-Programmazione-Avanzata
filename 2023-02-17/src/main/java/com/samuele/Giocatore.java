package com.samuele;

public class Giocatore extends Thread{
    Carta [] bb;
    boolean vittoria;
    private int numeroCarteAcquistate;
    Partita p;

    public Giocatore(Partita p){
        this.p = p;
        vittoria = false;
        numeroCarteAcquistate = 0;
        bb = new Carta[10];
    }

    public void run(){
        while (numeroCarteAcquistate != 10) {
            try {
                Carta [] carteDisponibli = p.carteDisponibili();
                int indiceCartaDaEstrarre  = (int) (Math.random() * carteDisponibli.length);
                //System.out.println(getName() + ": indice carta da estrarre: " + indiceCartaDaEstrarre);
                
                int numeroC = carteDisponibli[indiceCartaDaEstrarre].getNum();
                Seme s = carteDisponibli[indiceCartaDaEstrarre].getSeme();
                
                //System.out.println(getName() + ": numero carta:" + numeroC + ", seme carta: " + s);

                Carta c = p.compraCarta(numeroC, s);
                //System.out.println(getName() + ": ho comprato " + c);
                bb[numeroCarteAcquistate] = c;
                numeroCarteAcquistate++;
            } catch (CartaNonDisponibileException e) {
                //handle exceptio
                //System.out.println("Carta non disponibile, ritento");
            }
        }
        try {
            // attendo l'estrazione
            vittoria = p.AttendiEstrazione(bb);

            if (vittoria)
                System.out.println(getName() + ": Ho vinto");
            else
                System.out.println(getName() + ": Non ho vinto");

        } catch (InterruptedException e) {
            //handle exception
        }
    }

}
