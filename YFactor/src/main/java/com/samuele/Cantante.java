package com.samuele;

public class Cantante extends Thread{
    Studio st;

    public Cantante(String s, Studio st){
        super(s);
        this.st = st;
    }

    public void run(){
        boolean finito = false;
        int camerino = -1;
        while (!finito){
            try {
                int timePalco, timeCamerino;
                
                //prendo il camerino
                camerino = st.prendiCamerino();
                
                // mi preparo nel camerino
                timeCamerino = (int) (Math.random() * 1000);
                System.err.println(getName() + ": mi preparo in camerino per " + timeCamerino);
                sleep(timeCamerino);

                // se l'allarme è scattato mentre ero ancora in camerino ricomincio
                // (non sono più nell'array, quindi il flag non può più cambiare)
                if (Thread.interrupted())
                    throw new InterruptedException(); 

                //esco dal camerino
                st.lasciaCamerino(camerino);
                camerino = -1;

                //salgo sul palco
                st.saliPalco();

                // sto sul palco
                timePalco = (int) (Math.random() * 1000);
                System.err.println(getName() + ": mi esibisco sul palco per " + timePalco);
                sleep(timePalco); 

                //scendo dal palco               
                st.scendiPalco();
                System.out.println(getName() + ": ho terminato");
                finito = true;
                
            } catch (InterruptedException e) {
                if (camerino != -1){
                    st.lasciaCamerino(camerino);
                    camerino = -1;
                }
                System.out.println(getName() + ": ricomincio da capo");
        
            }           
        }
         
    }

}
