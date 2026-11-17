package com.samuele;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Logger {
    int bufSize;
    String nomeFile;
    int nMessaggiBufferizzati;

    List<Integer> bufCodici;
    List<String> bufStringhe;

    int codiceSuFile;


    public Logger(String fn, int n){
        nomeFile = fn;
        bufSize = n;
        nMessaggiBufferizzati = 0;

        bufCodici = new ArrayList<>();
        bufStringhe = new ArrayList<>();

        codiceSuFile = 0;
    }

    public synchronized void log(String s, int o) throws InterruptedException{
        while (o != codiceSuFile && nMessaggiBufferizzati == bufSize) {
            System.out.println(Thread.currentThread().getName() + ": mi blocco, codice <" + o + ">, numero messaggi bufferizzati: " + nMessaggiBufferizzati);
            wait();
        }


        if (o != codiceSuFile && nMessaggiBufferizzati != bufSize){
            bufCodici.add(o);
            bufStringhe.add(s);
            nMessaggiBufferizzati++;
            System.out.println(Thread.currentThread().getName() + ": ho bufferizzato " + s + ", codice : " + o + ", codice da inserire: " + codiceSuFile);
        } else {
            // trasferisco il mio messaggio poi trasferisco gli altri messaggi 

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(nomeFile,true))){
                String stringa = o + " " + s; 
                bw.write(stringa);
                bw.write("\n");
                System.out.println(Thread.currentThread().getName() + ": ho inserito " + stringa);
                codiceSuFile++;

                int cont = bufCodici.size();
                while (cont > 0){
                    int codiceEstratto = bufCodici.removeFirst();
                    String stringaEstratat = bufStringhe.removeFirst();
                    if (codiceEstratto != codiceSuFile){
                        bufCodici.add(codiceEstratto);
                        bufStringhe.add(stringaEstratat);
                    } else{
                        String stringaEstratta = bufStringhe.removeFirst();
                        stringa = codiceEstratto + " " + stringaEstratta;
                        bw.write(stringa);
                        bw.write("\n");
                        System.out.println(Thread.currentThread().getName() + ": è stato inserito " + stringa);
                        codiceSuFile++;
                        nMessaggiBufferizzati--;
                    }                    
                    cont--;
                }
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        notifyAll();


    }


}
