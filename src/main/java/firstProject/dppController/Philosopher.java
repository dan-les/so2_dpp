package firstProject.dppController;

import firstProject.guiController.Frame;

import static com.diogonunes.jcolor.Ansi.colorize;
import static com.diogonunes.jcolor.Attribute.*;

public class Philosopher extends Thread {
    private int id;
    private Chopstick leftChopstick;
    private Chopstick rightChopstick;
    private Frame frame;
    private Integer eatQuantity;

    //regulowanie prędkości działania wątków
    public final int slower = 3;

    int maxEatTimeMs = 4000 * slower;
    int maxThinkTimeMs = 6000 * slower;
    int maxWaitTimeToTakeSecondChopstickMs = 1000 * slower;
    int maxWaitTimeToReleaseSecondChopstickMs = 300;

    Philosopher(int id, Frame frame, Chopstick leftChopstick, Chopstick rightChopstick) {
        this.eatQuantity = 0;
        this.id = id;
        this.leftChopstick = leftChopstick;
        this.rightChopstick = rightChopstick;
        this.frame = frame;
        setName("Filozof " + id);
    }

    public Integer getEatQuantity() {
        return eatQuantity;
    }

    public void run() {
        while (true) {
            // na początku filozof myśli
            System.out.println(colorize(getName() + " myśli!", BLUE_TEXT()));
            frame.isThinking(id);

            try {
                Thread.sleep((long) (Math.random() * maxThinkTimeMs));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(getName() + " skończył myśleć!");

            try {
                try {
                    mainApp.semaphore.acquire();

                    // Filozof próbuje podnieść lewą pałeczkę
                    System.out.println(getName() + " chce podnieść lewą pałeczkę!");
                    leftChopstick.takeChopstick();
                    System.out.println(getName() + " podniósł lewą pałeczkę!");
                    frame.takeChopstick(id, leftChopstick.getId());

                    // czekamy chwilę na podniesienie drugiej pałeczki
                    try {
                        Thread.sleep(maxWaitTimeToTakeSecondChopstickMs);
                    } catch (InterruptedException e) {
                        System.out.println(e);
                    }

                    // Filozof próbuje podnieść prawą pałeczkę
                    System.out.println(getName() + " chce podnieść prawą połeczkę");
                    rightChopstick.takeChopstick();
                    System.out.println(getName() + " podniósł prawą połeczkę");
                    frame.takeChopstick(id, rightChopstick.getId());

                    // Filozof je
                    String text = getName().toUpperCase() + " je!";
                    System.out.println(colorize(text, GREEN_TEXT(), BOLD()));
                    frame.isEating(id);
                    // czas jedzenia
                    try {
                        Thread.sleep((long) (Math.random() * maxEatTimeMs));
                    } catch (InterruptedException e) {
                        System.out.println(e);
                    }

                } finally {
                    String text = getName().toUpperCase() + " skończył jeść!";
                    System.out.println(colorize(text, BRIGHT_RED_TEXT(), BOLD()));

                    frame.isThinking(id);
                    ++eatQuantity;
                    System.out.println(colorize(getName().toUpperCase()
                            + " JADŁ " + getEatQuantity()
                            + " RAZ(Y)", BRIGHT_RED_TEXT()));

                    // opuszczenie lewej pałeczki
                    leftChopstick.releaseChopstick();
                    System.out.println(getName() + " opuścił lewą pałeczkę!");
                    frame.releaseChopstick(id, leftChopstick.getId());

                    // czekamy chwilę na opuszczenie drugiej pałeczki
                    try {
                        Thread.sleep(maxWaitTimeToReleaseSecondChopstickMs);
                    } catch (InterruptedException e) {
                        System.out.println(e);
                    }

                    // opuszczenie prawej pałeczki
                    rightChopstick.releaseChopstick();
                    System.out.println(getName() + " opuścił lewą pałeczkę!");
                    frame.releaseChopstick(id, rightChopstick.getId());

                    System.out.println();
                    //System.out.println("available Semaphore permits now: " +
                    // mainApp.semaphore.availablePermits());
                    mainApp.semaphore.release();

                    //System.out.println("available Semaphore permits now: " +
                    // mainApp.semaphore.availablePermits());

                    if (eatQuantity == mainApp.maxEatQuantity) {
                        Thread.currentThread().interrupt();
                        System.out.println(
                                colorize(
                                        getName() + " ODCHODZI OD STOŁU!",
                                        BRIGHT_WHITE_TEXT(),
                                        RED_BACK(),
                                        BOLD())
                        );
                        frame.leaveTable(id);
                        return;
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}