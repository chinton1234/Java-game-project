package gui;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import logic.GameManager;
import logic.GameThread;
import sharedObject.Renderable;
import sharedObject.RenderableHolder;

public class GameStatusBar extends VBox implements Renderable {

	private int z;
	private VBox gameStatusVBox;
	private Label showRound;
	private Label playerBox;

	public GameStatusBar(GameManager gameManager) {
		super();
		this.z = 5;
		showRound = new Label();
		showRound.setText("Round : " + 1);
		showRound.setStyle("-fx-text-fill: white; -fx-font: normal bold 30px 'Arial';");

		playerBox = new Label();
		playerBox.setText(gameManager.getPlayers().get(0).getName() + "'s turn");
		playerBox.setStyle("-fx-text-fill: white; -fx-font: normal bold 24px 'Arial';");

		gameStatusVBox = new VBox();
		gameStatusVBox.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
		gameStatusVBox.setPrefSize(180, 90);
		gameStatusVBox.setAlignment(Pos.CENTER);
		gameStatusVBox.getChildren().addAll(showRound, playerBox);

		this.setPrefSize(200, 110);
		this.setLayoutX(450);
		this.setLayoutY(210);
		this.setAlignment(Pos.CENTER);
		this.setBackground(new Background(new BackgroundFill(gameManager.getPlayers().get(0).getColor(), null, null)));
		this.getChildren().add(gameStatusVBox);
		RenderableHolder.getInstance().add(this);
	}

	@Override
	public int getZ() {
		return this.z;
	}

	@Override
	public void draw(Pane gameScreen) {
		// TODO Auto-generated method stub
		gameScreen.getChildren().add(this);
	}

	public void update(GameThread gameThread) {
		showRound.setText("Round : " + gameThread.getGameManager().getRound());
		playerBox.setText(gameThread.getCurrentPlayer().getName() + "'s turn");
		this.setBackground(new Background(new BackgroundFill(gameThread.getCurrentPlayer().getColor(), null, null)));
	}
}
