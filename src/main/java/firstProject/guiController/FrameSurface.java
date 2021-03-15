package firstProject.guiController;

import javax.swing.*;
import java.awt.*;

class FrameSurface extends JPanel {

    private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        RenderingHints rh =
                new RenderingHints(
                        RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON
                );
        rh.put(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHints(rh);
        g2d.setFont(new Font("Purisa", Font.PLAIN, 13));

        // napisy z numerami filozofów
        g2d.drawString("Philosopher 0", 295, 90);
        g2d.drawString("Philosopher 1", 530, 240);
        g2d.drawString("Philosopher 2", 375, 440);
        g2d.drawString("Philosopher 3", 215, 440);
        g2d.drawString("Philosopher 4", 59, 240);

        // napisy z numerami pałeczek
        g2d.drawString("ch0", 382, 196);
        g2d.drawString("ch1", 402, 276);
        g2d.drawString("ch2", 322, 336);
        g2d.drawString("ch3", 242, 276);
        g2d.drawString("ch4", 272, 196);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);

        for (int i = 0; i < 5; i++) {
            Frame.circlesFilled.get(i).draw(g);
            Frame.chops.get(i).drawFilledRect(g);
            Frame.circles.get(i).draw(g);
        }
    }
}
