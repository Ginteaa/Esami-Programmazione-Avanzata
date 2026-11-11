package com.samuele;

public class GestoreMemoria {
    private int grandezzaMemoria;
    private int grandezzaBlocco;
    private int numeroBlocchi;
    private Blocco blocchi [];
    private int numeroBlocchiDisponibili;
    private int numeroVipinAttesa;


    public GestoreMemoria(int M, int N) throws IllegalArgumentException{

        if (M % N != 0){
            throw new IllegalArgumentException();
        }

        grandezzaMemoria = M;
        grandezzaBlocco = N;

        numeroBlocchi = grandezzaMemoria / grandezzaBlocco;
        numeroBlocchiDisponibili = numeroBlocchi;
        blocchi = new Blocco[numeroBlocchi];
        for (int i=0;i<numeroBlocchi;i++){
            blocchi[i] = new  Blocco(i);
        }

        numeroVipinAttesa = 0;
    }

    private int getBlocco(){
        // NOTA: se nessun blocco e' libero restituisce 0 (un blocco gia' preso!)
        // senza segnalare nulla. Con la sincronizzazione corretta non succede,
        // ma nella versione originale (vedi sotto) poteva succedere e
        // nascondeva l'errore: due thread si ritrovavano con lo stesso blocco.
        int index = 1;
        for (int i=0; i<numeroBlocchi; i++){
            if (!blocchi[i].getPreso()){
                index = i;
                blocchi[i].ifPreso();
                break;
            }
        }
        return index;
    }


    /* =====================================================================
     *  VERSIONE ORIGINALE (con due while) - commentata con gli errori
     * =====================================================================
     *
     * public synchronized Blocco acquisisci (boolean prio) throws InterruptedException{
     *
     *     while (numeroBlocchiDisponibili == 0){
     *         System.out.println(Thread.currentThread().getName() + ": mi metto in attesa");
     *         if (prio){
     *             numeroVipinAttesa++;
     *         }
     *         wait();
     *
     *         if (prio)
     *             numeroVipinAttesa--;
     *     }
     *     // ERRORE 1: questo secondo while e' separato dal primo. Quando il
     *     // thread si risveglia qui dentro ed esce (perche' i VIP sono finiti),
     *     // NON ricontrolla che ci siano ancora blocchi liberi. Nel frattempo
     *     // il VIP per cui si era fermato puo' aver preso l'ultimo blocco.
     *     // Esempio: 1 blocco libero, V (VIP) in attesa nel primo while.
     *     //   - N (non VIP) entra, vede 1 blocco, salta il primo while,
     *     //     vede numeroVipinAttesa==1 e va in wait qui.
     *     //   - V si sveglia, prende l'ultimo blocco -> disponibili = 0.
     *     //   - N si sveglia, numeroVipinAttesa==0 -> esce dal while e
     *     //     prende un blocco che NON c'e': disponibili = -1 e getBlocco()
     *     //     restituisce l'indice 0 (gia' occupato).
     *     // Regola: dopo OGNI wait() vanno ricontrollate TUTTE le condizioni.
     *     //
     *     // ERRORE 2 (minore): "!= 0" e' fragile. Se il contatore diventa
     *     // negativo (e succedeva, vedi free()) la condizione resta vera per
     *     // sempre e il thread non-VIP non si sblocca piu'. Meglio "> 0".
     *     //
     *     // ERRORE 3 (minore): notifyAll() prima di wait() non serve, sveglia
     *     // tutti a vuoto. Chi deve svegliare gli altri e' free().
     *     while (numeroVipinAttesa != 0 && !prio){
     *         notifyAll();
     *         wait();
     *     }
     *
     *     int indexBlocco = getBlocco();
     *     numeroBlocchiDisponibili--;
     *     ...stampe...
     *
     *     // ERRORE 4 (minore): notifyAll() dopo aver PRESO un blocco non serve:
     *     // prendere una risorsa non rende vera la condizione di nessuno.
     *     notifyAll();
     *     return blocchi[indexBlocco];
     * }
     */


    /* =====================================================================
     *  VERSIONE CORRETTA (un solo while)
     * =====================================================================
     * Un thread deve aspettare se:
     *   - non ci sono blocchi liberi, OPPURE
     *   - non e' VIP e c'e' almeno un VIP in attesa (i VIP hanno precedenza).
     * Mettendo entrambe le condizioni nello stesso while, dopo ogni wait()
     * vengono ricontrollate tutte e due.
     *
     * Il VIP si "registra" come in attesa una sola volta prima del ciclo e si
     * "cancella" una sola volta quando ottiene il blocco: ++ e -- sono sempre
     * in coppia, quindi il contatore non puo' sballare.
     */
    public synchronized Blocco acquisisci (boolean prio) throws InterruptedException{

        if (prio)
            numeroVipinAttesa++;

        while (numeroBlocchiDisponibili == 0 || (!prio && numeroVipinAttesa > 0)){
            System.out.println(Thread.currentThread().getName() + ": mi metto in attesa");
            wait();
        }

        if (prio)
            numeroVipinAttesa--;

        int indexBlocco = getBlocco();
        numeroBlocchiDisponibili--;

        if (prio){
            System.out.println(Thread.currentThread().getName() + "(VIP)" + ": ho acquisito il blocco <" + blocchi[indexBlocco].getIdBlocco() + ">");
        } else {
            System.out.println(Thread.currentThread().getName()  + ": ho acquisito il blocco <" + blocchi[indexBlocco].getIdBlocco() + ">");
        }

        // Se un VIP ha appena smesso di essere in attesa (numeroVipinAttesa
        // e' sceso, magari a 0), dei non-VIP potrebbero ora poter procedere:
        // qui la notifyAll() serve SOLO per questo caso.
        if (prio)
            notifyAll();

        return blocchi[indexBlocco];
    }

    public synchronized void free(Blocco b){
        for (int i = 0; i < numeroBlocchi; i++){
            if (blocchi[i].equals(b)){
                blocchi[i].ifPreso();
                numeroBlocchiDisponibili++;
                break;
            }
        }


       if (((Processo)Thread.currentThread()).getprio()){
            // ERRORE PRINCIPALE (era qui): c'era "numeroVipinAttesa--;".
            // Un VIP che rilascia un blocco NON e' in attesa: il suo ++ era
            // gia' stato compensato dal -- in acquisisci. Decrementare anche
            // qui faceva diventare il contatore NEGATIVO, e con la condizione
            // "numeroVipinAttesa != 0" i non-VIP restavano in wait per sempre
            // (l'ultimo thread non veniva mai sbloccato).
            System.out.println(Thread.currentThread().getName() + "(VIP)" + ": ho rilasciato il blocco " + b.getIdBlocco());

        } else {
            System.out.println(Thread.currentThread().getName() + ": ho rilasciato il blocco " + b.getIdBlocco());
        }

        notifyAll();
    }

    public synchronized void installa(int k) throws IllegalArgumentException{
        if (k % grandezzaBlocco != 0)
            throw new IllegalArgumentException();

        int numeroBlocchiaggiunti = k / grandezzaBlocco;
        numeroBlocchi += numeroBlocchiaggiunti;
        grandezzaMemoria += k;
        numeroBlocchiDisponibili += numeroBlocchiaggiunti;

        Blocco newBlocchi [] = new Blocco [numeroBlocchi];
        for (int i= 0; i<numeroBlocchi; i++){
            if ( i < numeroBlocchi - numeroBlocchiaggiunti)
                newBlocchi[i] = blocchi[i];
            else
                newBlocchi[i] = new Blocco(i);
        }

        // ERRORE: mancava questa assegnazione. Senza, l'array resta quello
        // vecchio mentre numeroBlocchi e' aumentato -> getBlocco() puo'
        // lanciare ArrayIndexOutOfBoundsException.
        blocchi = newBlocchi;

        // ERRORE: mancava la notifyAll(). Ci sono nuovi blocchi liberi,
        // quindi i thread in attesa devono essere svegliati.
        notifyAll();
    }


}
