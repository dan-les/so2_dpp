package firstProject.dppController;

import firstProject.guiController.Frame;

public class Philosopher extends Thread {
    private int id;
    private Chopstick leftChopstick;
    private Chopstick rightChopstick;
    private Frame frame;

    //regulowanie prędkości działania wątków
    public final int slower = 2;

    int maxEatTimeMs = 4000 * slower;
    int maxThinkTimeMs = 6000 * slower;
    int maxWaitTimeToTakeSecondChopstickMs = 1000 * slower;
    int maxWaitTimeToReleaseSecondChopstickMs = 300;

    Philosopher(int id, Frame frame, Chopstick leftChopstick, Chopstick rightChopstick) {
        this.id = id;
        this.leftChopstick = leftChopstick;
        this.rightChopstick = rightChopstick;
        this.frame = frame;
        setName("Filozof " + id);
    }

    public void run() {
        while (true) {
            // na początku filozof myśli
            System.out.println(getName() + " myśli!");
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
                    System.out.println(getName() + " je!");
                    frame.isEating(id);
                    // czas jedzenia
                    try {
                        Thread.sleep((long) (Math.random() * maxEatTimeMs));
                    } catch (InterruptedException e) {
                        System.out.println(e);
                    }

                } finally {
                    System.out.println(getName() + " skończył jeść!" + "\n");
                    frame.isThinking(id);

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

                    //System.out.println("available Semaphore permits now: " +
                    // mainApp.semaphore.availablePermits());
                    mainApp.semaphore.release();
                    //System.out.println("available Semaphore permits now: " +
                    // mainApp.semaphore.availablePermits());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}