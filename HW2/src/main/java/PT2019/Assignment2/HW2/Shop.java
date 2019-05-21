package PT2019.Assignment2.HW2;

import java.util.*;

/**
 * Clasa simuleaza o locatie in care niste clienti vin la un anumit timp, se aseaza la coada si sunt procesati.
 */
public class Shop extends Thread {
    /*datele de intrare*/
    private int minArrTime;
    private int maxArrTime;
    private int minServTime;
    private int maxServTime;
    private int nbOfQueues;
    public static int simInterval;

    /*id-ul clientului curent*/
    private int currentId;
    /*lista de cozi*/
    private List<ClientQueue> queueList;
    /*generator de clienti*/
    private ClientGenerator clientGenerator;

    //constructor
    public Shop() {
        this.nbOfQueues = nbOfQueues;
        this.currentId = 0;
        this.queueList = new ArrayList<ClientQueue>();
        this.clientGenerator = new ClientGenerator(this);
    }

    public void setMinArrTime(int minArrTime) {
        this.minArrTime = minArrTime;
    }

    public void setMaxArrTime(int maxArrTime) {
        this.maxArrTime = maxArrTime;
    }

    public void setMinServTime(int minServTime) {
        this.minServTime = minServTime;
    }

    public void setMaxServTime(int maxServTime) {
        this.maxServTime = maxServTime;
    }

    public void setCurrentId(int currentId) {
        this.currentId = currentId;
    }

    public void setNbOfQueues(int nbOfQueues) {
        this.nbOfQueues = nbOfQueues;
    }

    public int getNbOfQueues() {
        return nbOfQueues;
    }

    public int getMinArrTime() {
        return minArrTime;
    }

    public int getMaxArrTime() {
        return maxArrTime;
    }

    public int getMinServTime() {
        return minServTime;
    }

    public int getMaxServTime() {
        return maxServTime;
    }

    public int getCurrentId() {
        return currentId;
    }

    public List<ClientQueue> getQueueList() {
        return queueList;
    }

    /**
     * Functia initializeaza lista de cozi.
     */
    public void initializeQueueList() {
        int i;
        for (i = 0; i < nbOfQueues; i++) {
            ClientQueue q = new ClientQueue();
            this.queueList.add(q);
        }
    }

    /**
     * Functia returneaza coada cu timpul de asteptare cel mai mic.
     * @return
     */
    public int getIndexOfMinWaitingTimeQueue() {
        float minTime = 10000;
        int indexMin = 0;

        /*daca o coada este goala, clientul va alege acea coada*/
        for (ClientQueue q : queueList) {
            if (q.getQ().isEmpty())
                return queueList.indexOf(q);
        }

        /*altfel alege coada cu timpul de asteptare cel mai scurt*/
        for (ClientQueue q : queueList) {
            float s = q.getAvgWaitingTime();
            if (s < minTime) {
                minTime = s;
                indexMin = queueList.indexOf(q);
            }
        }

        return indexMin;
    }

    /**
     * Functia porneste threadul ClientGenerator si cozile.
     */
    public void run() {
        if(Main.exit == false) {
            clientGenerator.start();
            for (ClientQueue q : queueList) {
                q.start();
            }
        }
    }
}
