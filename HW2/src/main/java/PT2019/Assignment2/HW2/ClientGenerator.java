package PT2019.Assignment2.HW2;

import java.util.Random;

/**
 * Clasa are rolul de a genera clienti random si de a-i pune in coada.
 */
public class ClientGenerator extends Thread {
    /*Folosesc variabila shop pentru a avea acces la cozi*/
    private Shop shop;

    public ClientGenerator(Shop shop) {
        this.shop = shop;
    }

    @Override
    public void run() {
        /*cat timp simpularea nu s-a incheiat*/
        while (MyTimer.secondsPassed < Shop.simInterval && Main.exit == false) {
            try {
                Random rand = new Random();
                /*pauseInterval reprezinta timpul dupa care va veni urmatorul client*/
                int pauseInterval = shop.getMinArrTime() + rand.nextInt(shop.getMaxArrTime() - shop.getMinArrTime());
                /*newClientServiceTime reprezinta timpul de servire al noului client*/
                int newClientServiceTime = shop.getMinServTime() + rand.nextInt(shop.getMaxServTime());
                /*ID-ul noului client*/
                int newClientId = shop.getCurrentId() + 1;
                shop.setCurrentId(shop.getCurrentId() + 1);
                Client newClient = new Client(newClientId, MyTimer.secondsPassed, newClientServiceTime);

                /*adaug clientul la coada cu timpul minim de asteptare*/
                int minIndex = shop.getIndexOfMinWaitingTimeQueue();
                synchronized (shop.getQueueList().get(minIndex)) {
                    shop.getQueueList().get(minIndex).addClient(newClient);
                    System.out.println("A fost adaugat clientul cu ID-ul: "+newClient.getClientId()+" la secunda "+
                            MyTimer.secondsPassed);
                }
                System.out.println("Urmatorul client va fi generat dupa " + pauseInterval + " secunde.");

                /*opresc threadul pentru timpul in care clientul curent este servit*/
                Thread.sleep(pauseInterval * 1000);
            } catch (InterruptedException ex) {
                System.out.println("Thread interrupted!");
            }
        }
    }
}
