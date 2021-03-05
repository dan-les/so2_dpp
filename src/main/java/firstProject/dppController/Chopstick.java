package firstProject.dppController;

public class Chopstick {
    private final int ID;
    private boolean isUnused;

    Chopstick(int ID) {
        this.ID = ID;
        this.isUnused = true;
    }

    public int getID() {
        return (ID);
    }

    synchronized void takeChopstick() {


        // czekamy aż będzie można wziąć pałeczkę
        while (!isUnused) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println(("Exception in class: " + Chopstick.class.getName() + e));
            }
        }

        isUnused = false;

        System.out.println("Pałeczka nr  " + getID() + " została podniesiona!");
        notifyAll();
    }

    synchronized void releaseChopstick() {
        while (isUnused) {
            try {
                wait();
            } catch (InterruptedException ignored) {
            }
        }
        isUnused = true;
        System.out.println("Pałeczka nr  " + getID() + " została odłożona!");
        notifyAll();
    }
}