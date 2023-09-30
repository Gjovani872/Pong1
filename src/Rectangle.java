import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Rectangle{
    public double x;
    public double y;
    public double width;
    public double height;
    public Color color;
    public Rectangle(double x,double y,double width,double height,Color color){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    public void draw(Graphics2D g2){
        g2.setColor(color);
        g2.fill(new Rectangle2D.Double(x,y,width,height));//better movement with fill



    }


    public double getX () {
        return x;
    }

    public void setX (double x) {
        this.x = x;
    }

    public double getY () {
        return y;
    }

    public void setY (double y) {
        this.y = y;
    }

    public double getWidth () {
        return width;
    }

    public void setWidth (double width) {
        this.width = width;
    }

    public double getHeight () {
        return height;
    }

    public void setHeight (double height) {
        this.height = height;
    }

    public Color getColor () {
        return color;
    }

    public void setColor (Color color) {
        this.color = color;
    }
}
