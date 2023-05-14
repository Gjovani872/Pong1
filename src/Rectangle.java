import java.awt.*;

public class Rectangle{
    private int x,y,width,height;
    private Color color;
    public Rectangle(int x,int y,int width,int height,Color color){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    public void draw(Graphics2D g2){
        g2.setColor(color);
            g2.fillRect(x,y,width,height);



    }


}
