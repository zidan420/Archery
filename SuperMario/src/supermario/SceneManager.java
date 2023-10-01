
package supermario;

import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

public class SceneManager {
    private Stage window;
    private Updatable scene;
    private int width, height;
    
    public SceneManager(Stage window)
    {
        this.window = window;
        
        // default
        this.width = 600;
        this.height = 600;
    }
    
    public void setTitle(String title)
    {
        this.window.setTitle(title);
    }
    
    public void setDimension(int width, int height)
    {
        this.width = width;
        this.height = height;
    }
    
    public int getWidth()
    {
        return this.width;
    }
    
    public int getHeight()
    {
        return this.height;
    }
    
    public void setScene(Updatable scene)
    {
        this.scene = scene;
        this.window.setScene(scene.getScene());
        
        // Event Handler
        scene.getScene().setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ESCAPE)
                Platform.exit();
        });
    }
    
    public Scene getScene()
    {
        return this.window.getScene();
    }
    
    public void update()
    {
        this.scene.update();
    }
}
