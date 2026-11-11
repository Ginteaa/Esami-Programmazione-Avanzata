package com.samuele;

public class Studio {
    //private  int numCantanti;
    private  int numCamerini;
    private  boolean camerini [];
    private  boolean palcoOccupato;
    private Thread cantantiInCamerino [];
    private int entrateCamerini;

    public Studio(int nCam){
        //numCantanti = nCant;

        entrateCamerini = 0;
        
        numCamerini = nCam;
        camerini = new boolean[numCamerini];
        cantantiInCamerino = new Thread[numCamerini];

        palcoOccupato = false;
    }

    // ritorna -1 se non ci sono camerini liberi altrimenti la posizoine del camerino all'interno dell'array
    private int cameriniLiberi(){
        for (int i = 0; i < numCamerini; i++){
            if (!camerini[i]) return i;
        }
        return  -1;
    }

    // ritorna -1 se il thread che ha preso il camerino è un fumatore 
    // altrimenti ritorna l'indice del camerino
    public synchronized int prendiCamerino() throws InterruptedException{
        int index;
        while ((index = cameriniLiberi()) == -1) {
            wait();
        }

        
        entrateCamerini++;
        System.out.println(Thread.currentThread().getName() + " ho incrementato entrate camerini: " + entrateCamerini);
        
        if (entrateCamerini == 10){
            System.out.println(Thread.currentThread().getName() + ": fumo una sigaretta in camerino, parte l'allarme antincendio!");
            // C'È UN FUMATORE
            entrateCamerini = 0;
            // tolgo tutti i cantanti dai loro camerini 
            for (Thread t : cantantiInCamerino){
                if (t != null){
                    t.interrupt();
                }
            }
            throw new InterruptedException();
        }


        // camerino occupato
        camerini[index] = true;
        cantantiInCamerino[index] = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + ": entro in camerino");

        notifyAll();
        return index;
    }

    public synchronized void lasciaCamerino(int index) {
        camerini[index] = false;
        cantantiInCamerino[index] = null;
        System.out.println(Thread.currentThread().getName() + ": esco dal camerino");
        notifyAll();
    }

    public synchronized void saliPalco() throws InterruptedException{
        while (palcoOccupato) {
            wait();
        }

        palcoOccupato = true;
        System.err.println(Thread.currentThread().getName() + ": salgo sul palco");

        notifyAll();
    }

    public synchronized void scendiPalco(){
        palcoOccupato = false;
        System.out.println(Thread.currentThread().getName() + ": scendo dal palco");
        notifyAll();
    }





}
