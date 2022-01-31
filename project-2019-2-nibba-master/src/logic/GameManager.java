package logic;

import java.util.ArrayList;

import gui.GameScreen;
import gui.GameStatusBar;
import logic.player.BotPlayer;
import logic.player.Player;

public class GameManager {

	// field
	private ArrayList<Player> players;
	private int round;
	private boolean isGameEnd;
	private String winnerName;
	private GameScreen gameScreen;
	private GameStatusBar gameStatusBar;

	// Constructor
	public GameManager(int playerNumbers) {

		this.round = 1;
		this.winnerName = null;

		this.players = new ArrayList<>();
		for (int i = 1; i <= playerNumbers; i++) {
			this.players.add(new Player(i));
		}
		for (int i = playerNumbers + 1; i <= 4; i++) {
			this.players.add(new BotPlayer(i));
		}

		this.gameStatusBar = new GameStatusBar(this);

		isGameEnd = false;

		this.gameScreen = new GameScreen();
		this.gameScreen.drawAllComponent();
	}

	public GameScreen getGameScreen() {
		return gameScreen;
	}

	public boolean checkGameEnd() {
		int losePlayerNumber = 0;
		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).isLose())
				losePlayerNumber += 1;
		}
		if (losePlayerNumber == (players.size() - 1)) {
			for (int i = 0; i < players.size(); i++) {
				if (!players.get(i).isLose()) {
					winnerName = players.get(i).getName();
					break;
				}
			}
		}

		return (losePlayerNumber == (players.size() - 1));
	}

	public boolean isGameEnd() {
		return isGameEnd;
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public String getWinnerName() {
		return winnerName;
	}

	public GameStatusBar getGameStatusBar() {
		return gameStatusBar;
	}

	public void addRound() {
		this.round += 1;
	}

	public int getRound() {
		return round;
	}

}
