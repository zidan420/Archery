
package supermario;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.animation.AnimationTimer;

public class SuperMario extends Application {
    
    @Override
    public void start(Stage window) {
        // Initialise Scene Manager
        SceneManager sm = new SceneManager(window);
        sm.setTitle("Super Mario");
        sm.setDimension(1000, 800);
        
        // Initialise MainMenu
        MainMenu mainMenu = new MainMenu(sm);
        
        // set scene to MainMenu
        sm.setScene(mainMenu);
        
        // Game Loop
        new AnimationTimer()
        {
            @Override
            public void handle(long currentTime)
            {
                sm.update();
            }
        }.start();

        // Show Window
        window.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
