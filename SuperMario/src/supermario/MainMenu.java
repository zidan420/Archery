package supermario;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;

public class MainMenu implements Updatable {
    private Scene scene;
    //private SceneManager sm;
    private GraphicsContext gc;
    private int width, height;

    public MainMenu (SceneManager sm)
    {
        this.width = sm.getHeight();
        this.height = sm.getHeight();
        
        // Main canvas
        Group root = new Group();
        this.scene = new Scene(root);
        
        Canvas canvas = new Canvas(this.width, this.height);
        
        this.gc = canvas.getGraphicsContext2D();
        
        // Play Button
        Button btn = new Button("PLAY");
        int width = 150, height = 60;
        btn.setLayoutX((this.width-width)/2);
        btn.setLayoutY((this.height-height)/2);
        
        btn.setMinWidth(width);
        btn.setMinHeight(height);
        
        btn.setFont(Font.font(40));
        
        btn.setStyle("-fx-background-color: #49fc03;");
        
        // Switch scene to Play
        btn.setOnAction(e -> {
            Play play = new Play(sm);
            sm.setScene(play);
        });
        
        // Hover effect
        btn.setOnMouseEntered((MouseEvent e) -> {
            btn.setStyle("-fx-background-color: #55b52f;");
        });
        
        btn.setOnMouseExited((MouseEvent e) -> {
            btn.setStyle("-fx-background-color: #49fc03;");
        });
        
        // Add canvas and buttons to root
        root.getChildren().add(canvas);
        root.getChildren().add(btn);
    }
    
    @Override
    public void update()
    {
        
    }
    
    @Override
    public Scene getScene()
    {
        return this.scene;
    }
}
