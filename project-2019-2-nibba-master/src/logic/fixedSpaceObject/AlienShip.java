package logic.fixedSpaceObject;

import java.util.Random;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import logic.AttackAbility;
import logic.FixedPoint;
import logic.player.Player;
import sharedObject.RenderableHolder;

public class AlienShip extends FixedSpaceObject implements AttackAbility {

	private ImageView alienShipObject;
	private Pane alienShipPane;
	private Text alienShipText;

	public AlienShip(FixedPoint fixedPoint) {
		super("AlienCamp", RenderableHolder.fixedSpaceObjectImages.get(3), 3, fixedPoint.getX(), fixedPoint.getY(),
				true, fixedPoint);
		alienShipObject = new ImageView(this.image);
		alienShipObject.setLayoutX(this.getX());
		alienShipObject.setLayoutY(this.getY());
		alienShipObject.setFitHeight(40);
		alienShipObject.setFitWidth(40);
		setInformationLabel();
		RenderableHolder.getInstance().add(this);
	}

	@Override
	public void draw(Pane pane) {
		pane.getChildren().addAll(alienShipObject, alienShipPane);
	}

	@Override
	public int attack(Player player) {
		Random random = new Random();
		int damage = (player.getMaxHp() / 10 ) * (random.nextInt(1) + 1);
		player.reduceHp(damage);
		return damage;
	}

	public ImageView getAlienShipObject() {
		return alienShipObject;
	}

	private void setInformationLabel() {
		alienShipText = new Text();
		alienShipPane = new Pane();
		alienShipText.setText("\nAelien Ship" + "\nCan attack player.");
		alienShipText.setStyle("-fx-font: normal bold 11px 'Arial';");
		alienShipPane.setLayoutX(490);
		alienShipPane.setLayoutY(520);
		alienShipPane.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
		alienShipPane.getChildren().add(alienShipText);
		alienShipPane.setPrefSize(120, 60);
		alienShipPane.setVisible(false);
		alienShipObject.setOnMouseEntered((event) -> {
			alienShipPane.setVisible(true);
		});
		alienShipObject.setOnMouseExited((event) -> {
			alienShipPane.setVisible(false);
		});
	}
}
