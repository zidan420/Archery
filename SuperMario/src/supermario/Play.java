
package supermario;

import javafx.scene.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import java.lang.Math;

public class Play implements Updatable{
    private Scene scene;
    private int width, height;
    private GraphicsContext gc;
    
    private Image board;
    private final int board_w = 100, board_h = 100;
    private int board_x, board_y, board_vel;
    
    public Play(SceneManager sm)
    {
        this.width = sm.getHeight();
        this.height = sm.getHeight();
        
        // Play canvas
        Group root = new Group();
        this.scene = new Scene(root);
        
        Canvas canvas = new Canvas(this.width, this.height);
        this.gc = canvas.getGraphicsContext2D();
        
        this.gc.setFill(Color.CYAN);
        
        // Add canvas to root(, which is part of scene)
        root.getChildren().add(canvas);
        
        // Initialise board
        this.board = new Image("images/board.png");
        this.board_x = -this.board_w;
        this.board_vel = 3;
        
        // Initialise crosshair
         Image cross = new Image("images/crosshair.png");
        // set cursor
        this.scene.setCursor(new ImageCursor(cross, cross.getWidth()/2, cross.getHeight()/2));
    }
    
    @Override
    public void update()
    {
        // Draw Background with Fill Color
        this.gc.fillRect(0, 0, this.width, this.height);
        
        // Generate Random position for board
        if (this.board_x == -this.board_w)
        {
            // Range from 0 to window height-board height (including)
            this.board_y = (int) (Math.random() * (this.height - this.board_h + 1));
        }
        
        // Draw board
        this.gc.drawImage(this.board, this.board_x, this.board_y, this.board_w, this.board_h);
        this.board_x += this.board_vel;
        
        // Reset x pos when out of view
        if (this.board_x >= this.width)
        {
            this.board_x = -this.board_w;
        }
    }
    
    @Override
    public Scene getScene()
    {
        return this.scene;
    }
}
