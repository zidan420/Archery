
package src;

import javafx.scene.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.control.Label;

public class Play implements Updatable {
	private Scene scene;
	private double width, height;
	private GraphicsContext gc;

	// Target (Board)
	private Target target;

	// CrossHair
	private Image cross;
	private ImageCursor customCursor;

	public Play(SceneManager sm) {
		width = sm.getWidth();
		height = sm.getHeight();

		/*
		 * Create root.
		 */
		Group root = new Group();
		scene = new Scene(root);

		// Create canvas and initialize 2d graphics context
		Canvas canvas = new Canvas(this.width, this.height);
		gc = canvas.getGraphicsContext2D();

		gc.setFill(Color.CYAN);
		
		/*
		 * Display Score
		 */
		Label scoreLabel = new Label("Score");
		scoreLabel.setLayoutX(width-200.0d);
		scoreLabel.setLayoutY(100.0d);
		scoreLabel.setFont(Font.font(40));

		// Add canvas and labels to root(, which is part of scene)
		root.getChildren().add(canvas);
		root.getChildren().add(scoreLabel);

		/*
		 * Initialise target with Scene Manager. Target need width and height for
		 * calculation. It also can modify score in Scene Manager.
		 */
		target = new Target(sm);

		// Initialise crosshair
		cross = new Image("images/crosshair.png");
		customCursor = new ImageCursor(cross, cross.getWidth() / 2, cross.getHeight() / 2);
		// set cursor
		scene.setCursor(customCursor);

		// Check for Mouse Click Event
		scene.setOnMouseClicked(e -> {
			target.onClick(e);
		});
	}

	@Override
	public void update(double deltaTime) {
		// Draw Background with Fill Color
		gc.fillRect(0, 0, width, height);

		// Draw Target
		target.render(gc);

		// Update Target (Position)
		target.update(deltaTime);
	}

	@Override
	public Scene getScene() {
		return scene;
	}
}
