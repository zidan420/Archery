package src;

import java.io.Serializable;

public class GameState implements Serializable {
	private int highestScore;
	
	public void setHighestScore(int score) {
		highestScore = score;
	}
	
	public int getHighestScore() {
		return highestScore;
	}
}
