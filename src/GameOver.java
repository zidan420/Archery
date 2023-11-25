package src;

import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import java.io.*;

public class GameOver implements Updatable {
	private Scene scene;
	private double width, height;
	private GraphicsContext gc;
	private SceneManager sm;

	// Scoreboard
	private Sprite scoreboard;
	private double boardWidth, boardHeight, boardPosX, boardPosY;

	// Score
	private Label scoreLabel;
	private Label currentScoreLabel;
	
	// Score
	private Label heighestScoreLabel;
	private Label currentHeighestScoreLabel;
	
	// Retry Button
	private Sprite retry;
	private double retryWidth, retryHeight, retryPosX, retryPosY;

	public GameOver(SceneManager sm) throws Exception {
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

		gc.setFill(Color.LIMEGREEN);
		
		/*
		 * Initialise Scoreboard
		 */
		boardWidth = 800.0;
		boardHeight = 600.0;
		boardPosX = 100.0;
		boardPosY = 100.0;
		scoreboard = new Sprite("images/scoreboard.png", boardPosX, boardPosY, boardWidth, boardHeight);
		
		/*
		 * Display Score
		 */
		scoreLabel = new Label("SCORE");
		scoreLabel.setFont(Font.font(70));
		scoreLabel.setLayoutY(150.0);
		scoreLabel.setTextFill(Color.YELLOW);
		
		currentScoreLabel = new Label(Integer.toString(sm.getScore()));
		currentScoreLabel.setFont(Font.font(100));
		currentScoreLabel.setLayoutY(250.0);
		currentScoreLabel.setTextFill(Color.RED);
		
		/*
		 * Heighest Score
		 */
		heighestScoreLabel = new Label("HEIGHEST SCORE");
		heighestScoreLabel.setFont(Font.font(70));
		heighestScoreLabel.setLayoutY(350.0);
		heighestScoreLabel.setTextFill(Color.YELLOW);
		
		currentHeighestScoreLabel = new Label(Integer.toString(sm.getHighestScore()));
		currentHeighestScoreLabel.setFont(Font.font(100));
		currentHeighestScoreLabel.setLayoutY(450.0);
		currentHeighestScoreLabel.setTextFill(Color.RED);
		
		/*
		 * Retry Button
		 */
		retryWidth = 100.0;
		retryHeight = 100.0;
		retryPosX = (width-retryWidth)/2.0;
		retryPosY = 580.0;
		retry = new Sprite("images/retry.png", retryPosX, retryPosY, retryWidth, retryHeight);
		
		// Add canvas, label and circles to root(, which is part of scene)
		root.getChildren().add(canvas);
		root.getChildren().add(scoreLabel);
		root.getChildren().add(currentScoreLabel);
		root.getChildren().add(heighestScoreLabel);
		root.getChildren().add(currentHeighestScoreLabel);
		
		// Ensure that the label is laid out before calculating the X position
		Platform.runLater(() -> {
		    scoreLabel.setLayoutX((width - scoreLabel.prefWidth(-1)) / 2.0);
		    currentScoreLabel.setLayoutX((width - currentScoreLabel.prefWidth(-1)) / 2.0);
		    
		    heighestScoreLabel.setLayoutX((width - heighestScoreLabel.prefWidth(-1)) / 2.0);
		    currentHeighestScoreLabel.setLayoutX((width - currentHeighestScoreLabel.prefWidth(-1)) / 2.0);
		});
		
		// Check for Mouse Click Event
		scene.setOnMouseClicked(e -> {
			double x = e.getX();
			double y = e.getY();
			
			if (x >= retryPosX && x <= retryWidth + retryPosX
					&& y >= retryPosY && y <= retryPosY + retryHeight)
			{
				// Reset Score
				sm.setScore(0);
				
				// Play again
				Play play = new Play(sm);
				sm.setScene(play);
			}
		});
		
		// Save Current Game State
		GameState gs = new GameState();
		gs.setHighestScore(sm.getHighestScore());
		
		File file = new File("gameState.dat");
		
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));){
			oos.writeObject(gs);
		}
	}

	@Override
	public void update(double deltaTime) {
		// Draw Background with Fill Color
		gc.fillRect(0, 0, width, height);

		// Draw Scoreboard
		scoreboard.render(gc);
		
		// Draw retry button
		retry.render(gc);
	}

	@Override
	public Scene getScene() {
		return scene;
	}
}
