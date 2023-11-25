
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
	private SceneManager sm;

	// Target (Board)
	private Target target;

	// CrossHair
	private Image cross;
	private ImageCursor customCursor;

	/*
	 * Available arrows
	 */
	private int availableArrows;
	private int usedArrows;
	private final int maxArrows = 5;
	private Sprite arrowsSprite;
	private Sprite arrowsSpriteRed;
	private double arrowPosX, arrowPosY, arrowWidth, arrowHeight;

	// Score
	private Label scoreLabel;

	public Play(SceneManager sm) {
		this.sm = sm;
		width = sm.getWidth();
		height = sm.getHeight();

		/*
		 * Create root
		 */
		Group root = new Group();
		scene = new Scene(root);

		// Create canvas and initialize 2d graphics context
		Canvas canvas = new Canvas(width, height);
		gc = canvas.getGraphicsContext2D();

		gc.setFill(Color.CYAN);

		/*
		 * No of arrows available
		 */
		availableArrows = maxArrows;
		usedArrows = 0;

		arrowPosX = 80.0;
		arrowPosY = 80.0;
		arrowWidth = 70.0;
		arrowHeight = 70.0;
		arrowsSprite = new Sprite("images/green_circle.png", arrowPosX, arrowPosY, arrowWidth, arrowHeight);
		arrowsSpriteRed = new Sprite("images/red_circle.png", arrowPosX, arrowPosY, arrowWidth, arrowHeight);

		/*
		 * Display Score
		 */
		scoreLabel = new Label("Score: 0");
		scoreLabel.setLayoutX(width - 200.0d);
		scoreLabel.setLayoutY(100.0d);
		scoreLabel.setFont(Font.font(40));

		// Add canvas, label and circles to root(, which is part of scene)
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
			// Play gunshot
			// Sound sound = new Sound("sounds/gunshot.mp3");
			
			// Check if Target is clicked
			target.onClick(e);
			
			// Handle number of arrows left
			availableArrows--;
			usedArrows++;
			if (availableArrows == 0) {
				try {
					GameOver gameOver = new GameOver(sm);
					sm.setScene(gameOver);
				} catch (Exception exc) {
					exc.printStackTrace();
				}
			}
		});
	}

	@Override
	public void update(double deltaTime) {
		// Draw Background with Fill Color
		gc.fillRect(0, 0, width, height);

		// Draw available arrows
		for (int i = 0; i < availableArrows; i++) {
			arrowsSprite.setX(arrowPosX + 120 * i);
			arrowsSprite.render(gc);
		}

		// Draw arrows used
		for (int i = 0; i < usedArrows; i++) {
			arrowsSpriteRed.setX(arrowPosX + 120 * (maxArrows-i-1));
			arrowsSpriteRed.render(gc);
		}

		// Draw Target
		target.render(gc);

		// Update Target (Position)
		target.update(deltaTime);

		// Show updated score
		scoreLabel.setText("Score: " + sm.getScore());
	}

	@Override
	public Scene getScene() {
		return scene;
	}
}
