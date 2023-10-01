
package supermario;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;

public class Sprite {
    private Image image;
    private double x, y, width, height;
    
    public Sprite(Image image, double x, double y, double width, double height)
    {
        this.image = image;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    public void render(GraphicsContext gc)
    {
        gc.drawImage(image, x, y, width, height);
    }
    
    public boolean isClicked(MouseEvent e)
    {
        double x = e.getX();
        double y = e.getY();
        
        return this.x <= x && x <= this.x + width && this.y <= y && y <= this.y + height;
    }
    
    // Getters and setters for x, y, width, and height
    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }
}
