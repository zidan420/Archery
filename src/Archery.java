
package src;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.*;

import javafx.animation.AnimationTimer;

public class Archery extends Application {

	@Override
	public void start(Stage window) {
		// Initialise Scene Manager
		SceneManager sm = new SceneManager(window);
		sm.setTitle("Archery");
		sm.setDimension(1000.0d, 800.0d);

		// Initialise MainMenu
		MainMenu mainMenu = new MainMenu(sm);

		// set scene to MainMenu
		sm.setScene(mainMenu);

		// Load Saved Game State (if any)
		File file = new File("gameState.dat");
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));) {
			GameState gs = (GameState) ois.readObject();
			sm.setHighestScore(gs.getHighestScore());
		} catch (Exception e) {
			// ignore if there is no saved game state
		}

		// Game Loop
		new AnimationTimer() {
			@Override
			public void handle(long currentTime) {
				sm.update(currentTime);
			}
		}.start();

		// Show Window
		window.show();
	}

	/*
	 * Starts the main game (Calls the start() method).
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
