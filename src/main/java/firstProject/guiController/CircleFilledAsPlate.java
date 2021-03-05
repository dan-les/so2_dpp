package firstProject.guiController;

import java.awt.*;

class CircleFilledAsPlate {

    private Point point;
    private Color color;
    private int philosopheId;
    private int size;
    private int ident;

    CircleFilledAsPlate(int ident, Point coords, int size) {
        this.ident = ident;
        this.size = size;
        this.philosopheId = Integer.MAX_VALUE;
        setColor(philosopheId);
        this.point = new Point(coords);

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
            this.color = Color.DARK_GRAY;
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(point.x, point.y, size, size);
    }

}