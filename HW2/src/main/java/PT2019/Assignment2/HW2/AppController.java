package PT2019.Assignment2.HW2;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clasa care face legatura intre view si model.
 */
public class AppController extends Thread {
    private AppView view;
    private Shop shop;

    public AppController(Shop shop, AppView view) {
        this.shop = shop;
        this.view = view;

        view.addStartListener(new StartListener());
        view.addStopListener(new StopListener());
    }

    public AppController getAppController() {
        return AppController.this;
    }

    /**
     * Functia run() ruleaza pe toata durata simularii si actualizeaza informatiile la fiecare pas.
     */
    public void run() {
        while (MyTimer.secondsPassed < Shop.simInterval && Main.exit == false) {
            printTimes();
            for (int i = 0; i < shop.getNbOfQueues(); i++)
                printQueue(i);
        }
    }

    /**
     * Functia printTimes() afiseaza timpurile calculate: average waiting time, average service time, epty queue time.
     */
    public void printTimes() {
        int nbOfClients = 0;
        int maxNbOfClients = 0;
        for (int i = 0; i < shop.getNbOfQueues(); i++) {
            view.getAvgWatingTimes()[i].setText("Average waiting time: " + shop.getQueueList().get(i).getAvgWaitingTime());
            view.getAvgServiceTimes()[i].setText("Average service time: " + shop.getQueueList().get(i).getAvgServiceTime());
            view.getEmptyQueueTimes()[i].setText("Empty queue time: " + shop.getQueueList().get(i).getEmptyQueueTime());
            nbOfClients = nbOfClients + shop.getQueueList().get(i).getQ().size();
        }


        maxNbOfClients = Integer.parseInt(view.getPeakTimeTf().getText());
        if (nbOfClients > maxNbOfClients) {
            String s = "";
            s = s + nbOfClients;
            view.getPeakTimeTf().setText(s);
        }
    }

    /**
     * Functia afiseaza continutul cozilor in vectorul de Labeluri corespunzator indexului transmis ca parametru.
     *
     * @param index
     */
    public void printQueue(int index) {
        int i = 0;
        JLabel[] q = new JLabel[ClientQueue.MAX_NB_OF_CLIENTS];

        /*construiesc un vector de Labeluri cu dimensiunea MAX_NB_OF_CLIENTS*/
        for (i = 0; i < q.length; i++) {
            q[i] = new JLabel(" ");
        }

        i = 0;
        /*extrag coada cu indexul ales*/
        ClientQueue cq = shop.getQueueList().get(index);
        for (Client c : cq.getQ()) {
            String text = "";
            text = text + c.getClientId();
            /*afisez ID-ul clientului in Label*/
            q[i++].setText(text);
        }

        /*In functie de index, aleg coada pe care o afisez*/
        if (index == 0) {
            for (i = 0; i < q.length; i++) {
                view.getQueue1()[i].setText(q[i].getText());
            }
        }
        if (index == 1) {
            for (i = 0; i < q.length; i++) {
                view.getQueue2()[i].setText(q[i].getText());
            }
        }
        if (index == 2) {
            for (i = 0; i < q.length; i++) {
                view.getQueue3()[i].setText(q[i].getText());
            }
        }
        if (index == 3) {
            for (i = 0; i < q.length; i++) {
                view.getQueue4()[i].setText(q[i].getText());
            }
        }
        if (index == 4) {
            for (i = 0; i < q.length; i++) {
                view.getQueue5()[i].setText(q[i].getText());
            }
        }
    }

    /**
     * Listener pentru butonul START.
     */
    class StartListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                /*Citesc datele din view si le transmit modelului*/
                String input = view.getSimIntervalTf().getText();
                Shop.simInterval = Integer.parseInt(input);

                input = view.getNbOfQueuesTf().getText();
                shop.setNbOfQueues(Integer.parseInt(input));
                shop.initializeQueueList();

                input = view.getMinArrTimeTf().getText();
                shop.setMinArrTime(Integer.parseInt(input));

                input = view.getMaxArrTimeTf().getText();
                shop.setMaxArrTime(Integer.parseInt(input));

                input = view.getMinServTimeTf().getText();
                shop.setMinServTime(Integer.parseInt(input));

                input = view.getMaxServTimeTf().getText();
                shop.setMaxServTime(Integer.parseInt(input));

                /*Pornesc un timer*/
                MyTimer timer = new MyTimer();
                timer.start();

                System.out.println(shop.getNbOfQueues());

                /*Start threadului Shop*/
                shop.start();

                /*Start controller*/
                getAppController().start();
            } catch (Exception ex) {
                view.showError("Bad input!");
            }
        }
    }

    /**
     * Listener pentru butonul STOP.
     */
    class StopListener implements ActionListener {
        
        /*Daca se apasa butonul STOP, toate threadurile sunt oprite cu ajutorul variabilei exit*/
        public void actionPerformed(ActionEvent e) {
            Main.exit = true;
        }
    }
}
