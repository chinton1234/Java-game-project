package logic.player;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import logic.FixedPoint;
import logic.GameBoard;
import logic.SpaceObject;
import sharedObject.RenderableHolder;

public class SpaceShip extends SpaceObject {
	private int playerNumber;

	public SpaceShip(Player player) {
		super(player.getName(), RenderableHolder.spaceShipImages.get(player.getNumber() - 1), 4,
				player.getCurrentPoint().getX(), player.getCurrentPoint().getY(), true);
		this.playerNumber = player.getNumber();
		this.currentPoint = player.getCurrentPoint();
		this.height = 80;
		this.width = 80;
		spaceShipObject = new ImageView(this.image);
		spaceShipObject.setLayoutX(this.getX() + 10 - player.getNumber() * 5);
		spaceShipObject.setLayoutY(this.getY() + 10 - player.getNumber() * 5);
		spaceShipObject.setFitHeight(this.height);
		spaceShipObject.setFitWidth(this.width);
		RenderableHolder.getInstance().add(this);
	}

	private FixedPoint currentPoint;
	private int height;
	private int width;
	private ImageView spaceShipObject;

	public void draw(Pane gameScreen) {
		gameScreen.getChildren().add(spaceShipObject);
	}

	public void move() {
		this.currentPoint = GameBoard.getInstace().getFixedPoints().get((this.currentPoint.getAreaNumber() + 1) % 24);
		this.update();
	}

	public void warp(FixedPoint fixedPoint) {
		this.currentPoint = fixedPoint;
		this.update();
	}

	public FixedPoint getCurrentPoint() {
		return currentPoint;
	}

	public void update() {
		this.spaceShipObject.setLayoutX(this.currentPoint.getX() + 10 - this.playerNumber * 5);
		this.spaceShipObject.setLayoutY(this.currentPoint.getY() + 10 - this.playerNumber * 5);
	}

	public ImageView getSpaceShipObject() {
		return spaceShipObject;
	}

}
