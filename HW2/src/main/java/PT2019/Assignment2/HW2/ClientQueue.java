package PT2019.Assignment2.HW2;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Clasa descrie o coada in care fiecare element este de tipul Client.
 */
public class ClientQueue extends Thread {
    private Queue<Client> q;
    private int emptyQueueTime;
    private int clientsProcessed;
    private float avgWaitingTime;
    private float avgServiceTime;
    private int totalWaitingTime;
    private int totalServiceTime;
    /*numarul maxim de clienti la o coada*/
    public static final int MAX_NB_OF_CLIENTS = 10;

    public ClientQueue() {
        this.q = new LinkedList<Client>();
        this.emptyQueueTime = 0;
        this.clientsProcessed = 0;
        this.avgWaitingTime = 0;
        this.avgServiceTime = 0;
        this.totalWaitingTime = 0;
        this.totalServiceTime = 0;
    }

    public Queue<Client> getQ() {
        return q;
    }

    public void setQ(Queue<Client> q) {
        this.q = q;
    }

    public float getAvgWaitingTime() {
        return avgWaitingTime;
    }

    public float getAvgServiceTime() {
        return avgServiceTime;
    }

    public int getEmptyQueueTime() {
        return emptyQueueTime;
    }

    /**
     * Functia adauga clientul c la coada daca aceasta nu este plina.
     * @param c
     */
    public void addClient(Client c) {
        if (q.size() < MAX_NB_OF_CLIENTS) {
            q.add(c);
        } else {
            System.out.println("Queue is full!");
        }
    }

    @Override
    public String toString() {
        return "ClientQueue{" +
                "q=" + q +
                '}';
    }

    /**
     * Functia simuleaza o coada pe durata intervalului selectat.
     */
    public void run() {
        /*cat timp simularea nu s-a incheiat*/
        while (MyTimer.secondsPassed < Shop.simInterval && Main.exit == false) {
            /*daca coada este goala*/
            if (this.getQ().isEmpty() == true) {
                this.emptyQueueTime++;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    System.out.println("Thread interrupted!");
                }
            } else {
                /*daca coada nu este goala*/
                try {
                    /*obtin informatiile despre primul client*/
                    Client firstClient = this.q.peek();

                    /*coada este pusa in asteptare pana cand clientul este servit*/
                    Thread.sleep(firstClient.getServiceTime() * 1000);

                    /*clientul este scos din coada*/
                    firstClient = this.q.remove();


                    /*numarul de clienti procesati creste*/
                    clientsProcessed++;
                    firstClient.setFinishTime(MyTimer.secondsPassed);

                    System.out.println("Clientul cu ID-ul "+firstClient.getClientId()+" a fost scos din coada la" +
                            "secunda "+MyTimer.secondsPassed);

                    /*Calculez timpii de asteptare si de servire*/
                    totalWaitingTime = totalWaitingTime + firstClient.getFinishTime() - firstClient.getArrivalTime();
                    avgWaitingTime = totalWaitingTime / clientsProcessed;

                    totalServiceTime = totalServiceTime + firstClient.getServiceTime();
                    avgServiceTime = totalServiceTime*1.0f / clientsProcessed;
                } catch (InterruptedException ex) {
                    System.out.println("Thread Interrupted!");
                }
            }
        }
    }

}
