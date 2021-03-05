package firstProject.dppController;

import firstProject.guiController.Frame;

public class Philosopher extends Thread {
    private int id;
    private Chopstick leftChopstick;
    private Chopstick rightChopstick;
    private Frame frame;


    //regulowanie prędkości działania wątków
    public final int slower = 1;

    int maxEatTime = 4000 * slower;
    int maxThinkTime = 6000 * slower;
    int maxWaitTimeToTakeSecondChopstick = 1000 * slower;

    Philosopher(int ID, Frame frame, Chopstick left, Chopstick right) {
        this.id = ID;
        setName("Filozof nr " + ID);
        this.leftChopstick = left;
        this.rightChopstick = right;
        this.frame = frame;
    }

    public void run() {
        while (true) {
            // na początku filozof myśli
            frame.isThinking(id);
            System.out.println(getName() + " myśli!");

            try {
                Thread.sleep((long) (Math.random() * maxThinkTime));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(getName() + " skończył myśleć!");


//            //jeśli id filozofa jest nieparzyste to najpierw podnosi lewą pałeczkę
//            if (id % 2 == 1) {
            try {
                try {
                    mainApp.semaphore.acquire();
                    //System.out.println("available Semaphore permits now: " + semaphore.availablePermits());
                    // Filozof próbuje podnieść lewą pałeczkę
                    System.out.println(getName() + " chce podnieść lewą pałeczkę!");
                    leftChopstick.takeChopstick();


                    frame.takeChopstick(id, leftChopstick.getID());
                    System.out.println(getName() + " podniósł lewą pałeczkę!");

                    // czekamy chwilę na podniesienie drugiej pałeczki
                    try {
                        Thread.sleep(maxWaitTimeToTakeSecondChopstick);
                    } catch (InterruptedException e) {
                        System.out.println(e);
                    }

                    // Filozof próbuje podnieść prawą pałeczkę
                    System.out.println(getName() + " chce podnieść prawą połeczkę");
                    rightChopstick.takeChopstick();


                    frame.takeChopstick(id, rightChopstick.getID());
                    System.out.println(getName() + " podniósł prawą połeczkę");

                    // Filozof je
                    frame.isEating(id);
                    System.out.println(getName() + " je!");

                    // przerwa na czas jedzenia
                    try {
                        Thread.sleep((long) (Math.random() * maxEatTime));
                    } catch (InterruptedException e) {
                        System.out.println(e);
                    }


                } finally {
                    System.out.println(getName() + " skończył jeść!" + "\n");
                    frame.isThinking(id);

                    // opuszczenie lewej pałeczki
                    frame.releaseChopstick(id, leftChopstick.getID());
                    leftChopstick.releaseChopstick();
                    System.out.println(getName() + " opuścił lewą pałeczkę!");

                    // opuszczenie prawej pałeczki
                    frame.releaseChopstick(id, rightChopstick.getID());
                    rightChopstick.releaseChopstick();
                    System.out.println(getName() + " opuścił lewą pałeczkę!");

                    //System.out.println("available Semaphore permits now: " + semaphore.availablePermits());
                    mainApp.semaphore.release();
                    //System.out.println("available Semaphore permits now: " + semaphore.availablePermits());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

//            }
//            // filozofowie o numerach parzystych najpierw podnoszą prawą pałeczkę
//            else {
//                // Filozof próbuje podnieść prawą pałeczkę
//                System.out.println(getName() + " chce podnieść prawą pałeczkę!");
//                rightChopstick.takeChopstick();
//
//
//                frame.takeChopstick(id, rightChopstick.getID());
//                System.out.println(getName() + " podniósł prawą pałeczkę!");
//
//                // czekamy chwilę na podniesienie drugiej pałeczki
//                try {
//                    Thread.sleep(maxWaitTimeToTakeSecondChopstick);
//                } catch (InterruptedException e) {
//                    System.out.println(e);
//                }
//
//                // Filozof próbuje podnieść lewą pałeczkę
//                System.out.println(getName() + " chce podnieść lewą połeczkę");
//                leftChopstick.takeChopstick();
//
//
//                frame.takeChopstick(id, leftChopstick.getID());
//                System.out.println(getName() + " podniósł lewą połeczkę");
//
//                // Filozof je
//                frame.isEating(id);
//                System.out.println(getName() + " je!");
//
//                // przerwa na czas jedzenia
//                try {
//                    Thread.sleep((long) (Math.random() * maxEatTime));
//                } catch (InterruptedException e) {
//                    System.out.println(e);
//                }
//
//
//                System.out.println(getName() + " skończył jeść!" + "\n");
//                frame.isThinking(id);
//
//                // opuszczenie prawej pałeczki
//                frame.releaseChopstick(id, rightChopstick.getID());
//                rightChopstick.releaseChopstick();
//                System.out.println(getName() + " opuścił lewą pałeczkę!");
//
//                // opuszczenie lewej pałeczki
//                frame.releaseChopstick(id, leftChopstick.getID());
//                leftChopstick.releaseChopstick();
//                System.out.println(getName() + " opuścił lewą pałeczkę!");
//
//
//            }
        }
    }
}