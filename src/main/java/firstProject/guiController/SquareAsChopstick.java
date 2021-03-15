package firstProject.guiController;

import java.awt.*;

class SquareAsChopstick {
    private Point point;
    private Color color;
    private int ID;
    private int philosopheId;

    SquareAsChopstick(int ID, Point coordStart) {
        this.ID = ID;
        this.philosopheId = Integer.MAX_VALUE;
        setColor(philosopheId);
        this.point = new Point(coordStart);
    }

    public void drawFilledRect(Graphics g) {
        g.setColor(color);
        g.fillRect(point.x, point.y, 25, 25);
    }

    public void setColor(int phID) {
        this.philosopheId = phID;

        if (phID == 0)
            this.color = Color.BLUE;
        else if (phID == 1)
            this.color = Color.GREEN;
        else if (phID == 2)
            this.color = Color.orange;
        else if (phID == 3)
            this.color = Color.CYAN;
        else if (phID == 4)
            this.color = Color.PINK;
        else if (phID == Integer.MAX_VALUE)
            this.color = Color.GRAY;
    }
}
