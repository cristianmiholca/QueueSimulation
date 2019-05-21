package PT2019.Assignment2.HW2;

public class Main {
    /*numar secundele trecute*/
    public static int secondsPassed = 0;

    /*exit este folosita pentru a opri threadurile*/
    public static boolean exit = false;

    public static void main(String[] args) {
        Shop s = new Shop();
        AppView view = new AppView(s);
        AppController controller = new AppController(s, view);
    }
}
