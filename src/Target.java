package src;

import java.lang.Math;

import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;

public class Target extends Sprite {
	private double gameWidth, gameHeight;
	private SceneManager sm;
	
	/*
	 * Properties of target
	 */
	private double centerX, centerY, 
					width, height, 
					velocity;

	/*
	 * How far mouse is clicked from the target
	 */
	private double distance;

	public Target(SceneManager sm) {
		super("images/board.png", 0.0, 0.0, 100.0, 100.0);
		
		/*
		 * Reference to Scenemanager
		 */
		this.sm = sm;
		
		/* Width and Height of the actual game */
		this.gameWidth = sm.getWidth();
		this.gameHeight = sm.getHeight();

		/*
		 * initialize x, y and vel of target
		 */
		this.setX(-width);
		this.setY(generateY());
		this.setVelocity(1);
		
		/*
		 * Stores it locally to avoid calling methods again and again
		 */
		width = this.getWidth();
		height = this.getHeight();
		velocity = this.getVelocity();
	}

	// Generate Random position for Target
	public double generateY() {
		// Range from 0 to window height-board height (including)
		return Math.random() * (gameHeight - height + 1);
	}

	// Update Position
	public void update(double deltaTime) {
		// Generate new Y position
		if (this.getX() == -width) {
			this.setY(generateY());
		}

		// Update X position
		this.setX(this.getX() + velocity * deltaTime);

		// Reset x pos when out of view
		if (this.getX() >= gameWidth) {
			this.setX(-width);
		}
	}

	/*
	 * How far mouse is clicked from the target
	 */
	public double getDistance(double x, double y) {
		/*
		 * Center of Target: it'll be used to find distance of any mouse click from
		 * target
		 */
		centerX = this.getX() + width / 2;
		centerY = this.getY() + height / 2;
		
		return Math.sqrt(Math.pow(x - centerX, 2) + Math.pow(y - centerY, 2));
	}

	public void onClick(MouseEvent e) {
		distance = getDistance(e.getX(), e.getY());
		
		// Update score and reset x position
		if (distance <= width / 8)
		{
			sm.addScore(10);
			this.setX(-width);
		}
		else if (distance <= width/4)
		{
			sm.addScore(7);
			this.setX(-width);
		}
		else if (distance <= 3*width/8) {
			sm.addScore(4);
			this.setX(-width);
		}
		else if (distance <= width/2) {
			sm.addScore(1);
			this.setX(-width);
		}
	}
}
