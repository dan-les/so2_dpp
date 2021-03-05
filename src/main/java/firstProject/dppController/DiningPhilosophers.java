package firstProject.dppController;

import firstProject.guiController.Frame;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class DiningPhilosophers {


    // w jednym czasie mogą być użyte pałeczki tylko przez 4 filozofów
    public static Semaphore semaphore = new Semaphore(2);

    public static void main(String args[]) {

        List<Chopstick> chopsticks = new ArrayList<>();
        List<Philosopher> philosophers = new ArrayList<>();


        // dodanie pięciu pałeczek
        for (int i = 0; i < 5; i++) {
            chopsticks.add(new Chopstick(i));
        }

        Frame gui = new Frame();

        // dodanie filozofów oraz przydzielenie im pałeczek, które mogą podniosić
        philosophers.add(new Philosopher(0, gui, chopsticks.get(0), chopsticks.get(4)));
        philosophers.add(new Philosopher(1, gui, chopsticks.get(1), chopsticks.get(0)));
        philosophers.add(new Philosopher(2, gui, chopsticks.get(2), chopsticks.get(1)));
        philosophers.add(new Philosopher(3, gui, chopsticks.get(3), chopsticks.get(2)));
        philosophers.add(new Philosopher(4, gui, chopsticks.get(4), chopsticks.get(3)));

        //wystartowanie wątków
        for (int i = 0; i < 5; i++) {
            philosophers.get(i).start();
            System.out.println("Wątek filozofa nr " + i + " wystartował!");
        }
    }
}