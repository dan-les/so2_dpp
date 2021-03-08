package firstProject.guiController;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Frame extends JFrame {

    private List<Circle> circles = new ArrayList<>();
    private List<FilledCircleAsPlate> circlesFilled = new ArrayList<>();
    private List<SquareAsChopstick> chops = new ArrayList<>();

    public Frame() {
        super();

        // wyłącza program po zamknięciu okna
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setTitle("Problem ucztujących filozofów - Daniel Leśniewicz - 250996");
        setSize(680, 600);
        setBackground(Color.LIGHT_GRAY);

        //dodanie kolorowych otoczek każdego talerza jako okręgi
        circles.add(new Circle(new Point(298, 98), 74, Color.BLUE));
        circles.add(new Circle(new Point(448, 198), 74, Color.GREEN));
        circles.add(new Circle(new Point(378, 348), 74, Color.orange));
        circles.add(new Circle(new Point(218, 348), 74, Color.CYAN));
        circles.add(new Circle(new Point(148, 198), 74, Color.PINK));

        //dodanie talerzy jako wypełnione kółka
        circlesFilled.add(new FilledCircleAsPlate(0, new Point(300, 100), 70));
        circlesFilled.add(new FilledCircleAsPlate(1, new Point(450, 200), 70));
        circlesFilled.add(new FilledCircleAsPlate(2, new Point(380, 350), 70));
        circlesFilled.add(new FilledCircleAsPlate(3, new Point(220, 350), 70));
        circlesFilled.add(new FilledCircleAsPlate(4, new Point(150, 200), 70));

        //dodanie pałeczek jako kwadraty
        chops.add(new SquareAsChopstick(0, new Point(380, 200)));
        chops.add(new SquareAsChopstick(1, new Point(400, 280)));
        chops.add(new SquareAsChopstick(2, new Point(320, 340)));
        chops.add(new SquareAsChopstick(3, new Point(240, 280)));
        chops.add(new SquareAsChopstick(4, new Point(270, 200)));

        setVisible(true);
        setResizable(true);
    }

    // jeśli filozof je, to zmieiamy kolor jego talerza na kolor odpowidający danemu filozofowi
    public void isEating(int phID) {
        circlesFilled.get(phID).setColor(phID);
        repaint();
    }

    // jeśli filozof myśli, to zmieniamy kolor jego talerza na ciemno-szary
    public void isThinking(int phID) {
        circlesFilled.get(phID).setColor(Integer.MAX_VALUE);
        repaint();
    }

    // jeśli została wzięta pałeczka, to zmieniamy jej kolor na kolor odpowiadający filozofowi, którą ją podniósł
    public void takeChopstick(int phID, int chID) {
        chops.get(chID).setColor(phID);
        repaint();
    }

    // jeśli została odłożona pałeczka, to zmieniamy jej kolor na ciemno-szary
    public void releaseChopstick(int phID, int chID) {
        chops.get(chID).setColor(Integer.MAX_VALUE);
        repaint();
    }

    public void paint(Graphics g) {
        g.setColor(this.getBackground());
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        for (int i = 0; i < 5; i++) {
            circlesFilled.get(i).draw(g);
            chops.get(i).drawFilledRect(g);
            circles.get(i).draw(g);
        }
    }

}