
package src;

import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

public class SceneManager {
	private Stage window;
	private Updatable scene;
	private double width, height;

	/*
	 * Delta time Delta time is the time between current frame and last frame
	 * Multiply any kinds of movements with delta time to make - the game FRAME
	 * INDEPENDENT - or Hardware independent
	 */
	private long lastTime;
	private double deltaTime;
	
	/*
	 * Scores
	 */
	private int score, highestScore;

	public SceneManager(Stage window) {
		this.window = window;

		// default
		width = 600.0d;
		height = 600.0d;
		this.setDimension(width, height);
		lastTime = System.nanoTime();
	}

	public void setTitle(String title) {
		window.setTitle(title);
	}

	public void setDimension(double width, double height) {
		this.width = width;
		this.height = height;

		/*
		 * Makes changes to window (stage)
		 */
		window.setWidth(width);
		window.setHeight(height);
	}

	public double getWidth() {
		return width;
	}

	public double getHeight() {
		return height;
	}

	// Responsible for changing scene
	public void setScene(Updatable scene) {
		this.scene = scene;
		window.setScene(scene.getScene());

		// Event Handler
		scene.getScene().setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.ESCAPE)
				Platform.exit();
		});
	}

	public Scene getScene() {
		return window.getScene();
	}

	public void update(long currentTime) {
		// Delta Time
		deltaTime = (currentTime - lastTime) / 10000000.0;
		lastTime = currentTime;
		scene.update(deltaTime);
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
		
		if (score > highestScore) {
			highestScore = score;
		}
	}

	public int getHighestScore() {
		return highestScore;
	}
}
