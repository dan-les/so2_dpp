package firstProject.dppController;

public class Chopstick {
    private final int id;
    private boolean isUsed;

    Chopstick(int ID) {
        this.id = ID;
        this.isUsed = false;
    }

    public int getId() {
        return id;
    }

    synchronized void takeChopstick() {
        // czekamy aż będzie można wziąć pałeczkę
        //     - będzie ona nieużywana
        while (isUsed) {
            try {
                wait();
            } catch (InterruptedException interruptedException) {
                System.out.println(("Exception in class: " +
                        Chopstick.class.getName() + interruptedException));
            }
        }
        isUsed = true;

        System.out.println("Pałeczka nr  " + getId() + " została podniesiona!");
        notifyAll();
    }

    synchronized void releaseChopstick() {
        isUsed = false;
        System.out.println("Pałeczka nr  " + getId() + " została odłożona!");
        notifyAll();
    }
}