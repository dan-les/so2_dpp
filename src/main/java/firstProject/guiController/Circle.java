package firstProject.guiController;

import java.awt.*;

class Circle {
    private Point point;
    private Color color;
    private int size;

    Circle(Point coords, int size, Color color) {
        this.size = size;
        this.color = color;
        this.point = new Point(coords);
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.drawOval(point.x, point.y, size, size);
    }
}