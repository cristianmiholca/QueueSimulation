package PT2019.Assignment2.HW2;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.*;


/**
 * Clasa descrie partea vizuala a aplicatiei.
 */
public class AppView extends JFrame{
    /*Folosesc butonul Start pentru a porni aplicatia*/
    private JButton startButton = new JButton("Start");
    /*STOP pentru a opri aplicatia*/
    private JButton stopButton = new JButton("Stop");

    /*Labeluri pentru datele de intrare*/
    private JLabel minArrTimeLbl = new JLabel("Min Arriving Time");
    private JLabel maxArrTimeLbl = new JLabel("Max Arriving Time");
    private JLabel minServTimeLbl = new JLabel("Min Service Time");
    private JLabel maxServTimeLbl = new JLabel("Max Service Time");
    private JLabel nbOfQueuesLbl = new JLabel("Number of Queues");
    private JLabel simIntervalLbl = new JLabel("Simulation Interval");
    private JLabel peakTimeLbl = new JLabel("Peak Time");

    /*TextFielduri pentru datele de intrare*/
    private JTextField minArrTimeTf = new JTextField(20);
    private JTextField maxArrTimeTf = new JTextField(20);
    private JTextField minServTimeTf = new JTextField(20);
    private JTextField maxServTimeTf = new JTextField(20);
    private JTextField nbOfQueuesTf = new JTextField(20);
    private JTextField simIntervalTf = new JTextField(20);
    private JTextField peakTimeTf = new JTextField("0");

    /*Folosesc 5 vectori de Labeluri pentru a reprezenta cozile*/
    private JLabel[] queue1 = new JLabel[ClientQueue.MAX_NB_OF_CLIENTS];
    private JLabel[] queue2 = new JLabel[ClientQueue.MAX_NB_OF_CLIENTS];
    private JLabel[] queue3 = new JLabel[ClientQueue.MAX_NB_OF_CLIENTS];
    private JLabel[] queue4 = new JLabel[ClientQueue.MAX_NB_OF_CLIENTS];
    private JLabel[] queue5 = new JLabel[ClientQueue.MAX_NB_OF_CLIENTS];

    /*TextFielduri pentru timpii calculati*/
    private JTextField[] avgWatingTimes = new JTextField[5];
    private JTextField[] avgServiceTimes = new JTextField[5];
    private JTextField[] emptyQueueTimes = new JTextField[5];

    private Shop shop;

    public AppView(Shop shop) {
        this.shop = shop;

        this.setBounds(100, 100, 1200, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(new GridLayout(1, 6));

        for (int i = 0; i < 5; i++) {
            avgWatingTimes[i] = new JTextField("Average waiting time: ");
            avgWatingTimes[i].setEditable(false);
            avgServiceTimes[i] = new JTextField("Average service time: ");
            avgServiceTimes[i].setEditable(false);
            emptyQueueTimes[i] = new JTextField("Empty queue time: ");
            emptyQueueTimes[i].setEditable(false);
        }

        for (int i = 0; i < ClientQueue.MAX_NB_OF_CLIENTS; i++) {
            queue1[i] = new JLabel("\u2327", SwingConstants.CENTER);
            queue2[i] = new JLabel("\u2327", SwingConstants.CENTER);
            queue3[i] = new JLabel("\u2327", SwingConstants.CENTER);
            queue4[i] = new JLabel("\u2327", SwingConstants.CENTER);
            queue5[i] = new JLabel("\u2327", SwingConstants.CENTER);
        }

        /*Input Panel*/
        JPanel inputPanel = new JPanel();

        GroupLayout gl = new GroupLayout(inputPanel);
        gl.setAutoCreateContainerGaps(true);
        gl.setAutoCreateGaps(true);

        inputPanel.setLayout(gl);

        gl.setHorizontalGroup(gl.createSequentialGroup()
                .addGroup(gl.createParallelGroup(GroupLayout.Alignment.TRAILING).addComponent(simIntervalLbl)
                        .addComponent(nbOfQueuesLbl).addComponent(minArrTimeLbl).addComponent(maxArrTimeLbl)
                        .addComponent(minServTimeLbl).addComponent(maxServTimeLbl).addComponent(startButton)
                        .addComponent(peakTimeLbl))
                .addGroup(gl.createParallelGroup().addComponent(simIntervalTf).addComponent(nbOfQueuesTf)
                        .addComponent(minArrTimeTf).addComponent(maxArrTimeTf).addComponent(minServTimeTf)
                        .addComponent(maxServTimeTf).addComponent(stopButton).addComponent(peakTimeTf)));

        gl.setVerticalGroup(gl.createSequentialGroup()
                .addGroup(gl.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(simIntervalLbl)
                        .addComponent(simIntervalTf))
                .addGroup(gl.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(nbOfQueuesLbl)
                        .addComponent(nbOfQueuesTf))
                .addGroup(gl.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(minArrTimeLbl)
                        .addComponent(minArrTimeTf))
                .addGroup(gl.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(maxArrTimeLbl)
                        .addComponent(maxArrTimeTf))
                .addGroup(gl.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(minServTimeLbl)
                        .addComponent(minServTimeTf))
                .addGroup(gl.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(maxServTimeLbl)
                        .addComponent(maxServTimeTf))
                .addGroup(gl.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(startButton)
                        .addComponent(stopButton))
                .addGroup(gl.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(peakTimeLbl)
                        .addComponent(peakTimeTf)));

        inputPanel.add(startButton);

        /*Queue1 Panel*/
        JPanel queue1Panel = new JPanel();
        queue1Panel.add(new JLabel("Queue 1", SwingConstants.CENTER));
        addLabelArrayToPanel(queue1Panel, queue1);
        queue1Panel.add(avgWatingTimes[0]);
        queue1Panel.add(avgServiceTimes[0]);
        queue1Panel.add(emptyQueueTimes[0]);

        /*Queue2 Panel*/
        JPanel queue2Panel = new JPanel();
        queue2Panel.add(new JLabel("Queue 2", SwingConstants.CENTER));
        addLabelArrayToPanel(queue2Panel, queue2);
        queue2Panel.add(avgWatingTimes[1]);
        queue2Panel.add(avgServiceTimes[1]);
        queue2Panel.add(emptyQueueTimes[1]);

        /*Queue3 Panel*/
        JPanel queue3Panel = new JPanel();
        queue3Panel.add(new JLabel("Queue 3", SwingConstants.CENTER));
        addLabelArrayToPanel(queue3Panel, queue3);
        queue3Panel.add(avgWatingTimes[2]);
        queue3Panel.add(avgServiceTimes[2]);
        queue3Panel.add(emptyQueueTimes[2]);

        /*Queue4 Panel*/
        JPanel queue4Panel = new JPanel();
        queue4Panel.add(new JLabel("Queue 4", SwingConstants.CENTER));
        addLabelArrayToPanel(queue4Panel, queue4);
        queue4Panel.add(avgWatingTimes[3]);
        queue4Panel.add(avgServiceTimes[3]);
        queue4Panel.add(emptyQueueTimes[3]);

        /*Queue5 Panel*/
        JPanel queue5Panel = new JPanel();
        queue5Panel.add(new JLabel("Queue 5", SwingConstants.CENTER));
        addLabelArrayToPanel(queue5Panel, queue5);
        queue5Panel.add(avgWatingTimes[4]);
        queue5Panel.add(avgServiceTimes[4]);
        queue5Panel.add(emptyQueueTimes[4]);

        this.add(queue1Panel);
        this.add(queue2Panel);
        this.add(queue3Panel);
        this.add(queue4Panel);
        this.add(queue5Panel);
        this.add(inputPanel);

        this.setVisible(true);
    }

    public void addLabelArrayToPanel(JPanel panel, JLabel[] queue) {
        panel.setLayout(new GridLayout(ClientQueue.MAX_NB_OF_CLIENTS + 4, 1));
        /*Generate random color*/
        Random rand = new Random();
        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();

        for (int i = 0; i < ClientQueue.MAX_NB_OF_CLIENTS; i++) {
            Border border = BorderFactory.createLineBorder(new Color(r, g, b), 1);
            queue[i].setBorder(border);
            panel.add(queue[i]);
        }
    }

    public JLabel[] getQueue1() {
        return queue1;
    }

    public void setQueue1(JLabel[] queue1) {
        this.queue1 = queue1;
    }

    public JLabel[] getQueue2() {
        return queue2;
    }

    public void setQueue2(JLabel[] queue2) {
        this.queue2 = queue2;
    }

    public JLabel[] getQueue3() {
        return queue3;
    }

    public void setQueue3(JLabel[] queue3) {
        this.queue3 = queue3;
    }

    public JLabel[] getQueue4() {
        return queue4;
    }

    public void setQueue4(JLabel[] queue4) {
        this.queue4 = queue4;
    }

    public JLabel[] getQueue5() {
        return queue5;
    }

    public void setQueue5(JLabel[] queue5) {
        this.queue5 = queue5;
    }

    public JTextField[] getAvgWatingTimes() {
        return avgWatingTimes;
    }

    public void setAvgWatingTimes(JTextField[] avgWatingTimes) {
        this.avgWatingTimes = avgWatingTimes;
    }

    public JTextField[] getAvgServiceTimes() {
        return avgServiceTimes;
    }

    public void setAvgServiceTimes(JTextField[] avgServiceTimes) {
        this.avgServiceTimes = avgServiceTimes;
    }

    public JTextField[] getEmptyQueueTimes() {
        return emptyQueueTimes;
    }

    public JTextField getMinArrTimeTf() {
        return minArrTimeTf;
    }

    public void setMinArrTimeTf(JTextField minArrTimeTf) {
        this.minArrTimeTf = minArrTimeTf;
    }

    public JTextField getMaxArrTimeTf() {
        return maxArrTimeTf;
    }

    public void setMaxArrTimeTf(JTextField maxArrTimeTf) {
        this.maxArrTimeTf = maxArrTimeTf;
    }

    public JTextField getMinServTimeTf() {
        return minServTimeTf;
    }

    public void setMinServTimeTf(JTextField minServTimeTf) {
        this.minServTimeTf = minServTimeTf;
    }

    public JTextField getMaxServTimeTf() {
        return maxServTimeTf;
    }

    public JTextField getPeakTimeTf() {
        return peakTimeTf;
    }

    public void setPeakTimeTf(JTextField peakTimeTf) {
        this.peakTimeTf = peakTimeTf;
    }

    public void setMaxServTimeTf(JTextField maxServTimeTf) {
        this.maxServTimeTf = maxServTimeTf;
    }

    public JTextField getNbOfQueuesTf() {
        return nbOfQueuesTf;
    }

    public void setNbOfQueuesTf(JTextField nbOfQueuesTf) {
        this.nbOfQueuesTf = nbOfQueuesTf;
    }

    public JTextField getSimIntervalTf() {
        return simIntervalTf;
    }

    public void setSimIntervalTf(JTextField simIntervalTf) {
        this.simIntervalTf = simIntervalTf;
    }

    public void setEmptyQueueTimes(JTextField[] emptyQueueTimes) {
        this.emptyQueueTimes = emptyQueueTimes;
    }

    public void addStartListener(ActionListener startAL){
        startButton.addActionListener(startAL);
    }

    public void addStopListener(ActionListener stopAL){
        stopButton.addActionListener(stopAL);
    }

    public void showError(String errorMessage){
        JOptionPane.showMessageDialog(this, errorMessage, "Error", JOptionPane.WARNING_MESSAGE);
    }

}
