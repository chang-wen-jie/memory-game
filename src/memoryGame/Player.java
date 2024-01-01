package memoryGame;

public class Player {
	private String name;
	public int score = 0;
	
	public Player(String playerName) {
		this.name = playerName;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getScore() {
		return this.score;
	}
}
