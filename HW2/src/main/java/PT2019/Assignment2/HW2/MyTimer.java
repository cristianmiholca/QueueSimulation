package PT2019.Assignment2.HW2;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Clasa simuleaza un timer pentru o durata de timp introdusa de utilizator.
 */
public class MyTimer extends Thread {
    public static int secondsPassed = 0;
    private Timer timer;

    public MyTimer(){
        this.timer = new Timer();
    }

    public int getSecondsPassed() {
        return secondsPassed;
    }

    public void setSecondsPassed(int secondsPassed) {
        this.secondsPassed = secondsPassed;
    }

    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            /*cat timp nu s-a ajuns la timpul simInterval*/
            if(secondsPassed<Shop.simInterval && Main.exit == false) {
                System.out.println("Seconds passed: " + secondsPassed);

                /*cresc secundele*/
                secondsPassed++;

                System.out.println(Thread.activeCount());
            }
        }
    };

    public void run(){
        this.timer.scheduleAtFixedRate(task,1000,1000);
    }

}
