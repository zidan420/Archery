
package src;

import javafx.scene.Scene;

public interface Updatable {
	void update(double deltaTime);

	Scene getScene();
}