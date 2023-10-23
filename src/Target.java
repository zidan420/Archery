package src;

import java.lang.Math;

import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;

public class Target extends Sprite {
	private double gameWidth, gameHeight;
	public double centerX, centerY;
	private SceneManager sm;

	/*
	 * How far mouse is clicked from the target
	 */
	private double distance;

	public Target(SceneManager sm) {
		super("images/board.png", 0.0, 0.0, 100.0, 100.0);
		this.setX(-this.getWidth());
		this.setY(generateY());
		this.setVelocity(1);

		/*
		 * Reference to Scenemanager
		 */
		this.sm = sm;
		
		/* Width and Height of the actual game */
		this.gameWidth = sm.getWidth();
		this.gameHeight = sm.getHeight();
	}

	// Generate Random position for Target
	public double generateY() {
		// Range from 0 to window height-board height (including)
		return Math.random() * (gameHeight - this.getHeight() + 1);
	}

	// Update Position
	public void update(double deltaTime) {
		// Generate new Y position
		if (this.getX() == -this.getWidth()) {
			this.setY(generateY());
		}

		// Update X position
		this.setX(this.getX() + this.getVelocity() * deltaTime);

		// Reset x pos when out of view
		if (this.getX() >= gameWidth) {
			this.setX(-this.getWidth());
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
		centerX = this.getX() + this.getWidth() / 2;
		centerY = this.getY() + this.getHeight() / 2;
		
		return Math.sqrt(Math.pow(x - centerX, 2) + Math.pow(y - centerY, 2));
	}

	public void onClick(MouseEvent e) {
		distance = getDistance(e.getX(), e.getY());
		
		if (distance <= this.getWidth()/8)
		{
			sm.setScore(sm.getScore()+10);
		}
		else if (distance <= this.getWidth()/4)
		{
			sm.setScore(sm.getScore()+7);
		}
		else if (distance <= 3*this.getWidth()/8) {
			sm.setScore(sm.getScore()+4);
		}
		else if (distance <= this.getWidth()/2) {
			sm.setScore(sm.getScore()+1);
		}
		System.out.println(sm.getScore());
	}
}
