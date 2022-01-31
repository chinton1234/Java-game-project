package logic.fixedSpaceObject;

import java.util.Random;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import logic.FixedPoint;
import logic.player.Player;
import sharedObject.RenderableHolder;

public class SpacePirateShip extends FixedSpaceObject {

	private ImageView spacePirateShipObject;
	private Pane spacePiratePane;
	private Text spacePirateText;

	public SpacePirateShip(FixedPoint fixedPoint) {
		super("Space Pirate Ship", RenderableHolder.getFixedSpaceObjectImages().get(fixedPoint.getAreaNumber()), 3,
				fixedPoint.getX(), fixedPoint.getY(), true, fixedPoint);
		spacePirateShipObject = new ImageView(this.image);
		spacePirateShipObject.setLayoutX(this.getX());
		spacePirateShipObject.setLayoutY(this.getY());
		spacePirateShipObject.setFitHeight(40);
		spacePirateShipObject.setFitWidth(40);
		setInformationLabel();
		RenderableHolder.getInstance().add(this);
	}

	@Override
	public void draw(Pane pane) {

		pane.getChildren().addAll(spacePirateShipObject, spacePiratePane);
	}

	public static int steal(Player player) {

		Random random = new Random();
		int stealNumber = player.getResource() / 10 * random.nextInt(5);
		player.reduceResource(stealNumber);

		return stealNumber;

	}

	private void setInformationLabel() {
		spacePirateText = new Text();
		spacePiratePane = new Pane();
		spacePirateText.setText("\nSpacePirat Ship" + "\nThey take your resources." + "\nNot your life.");
		spacePirateText.setStyle("-fx-font: normal bold 11px 'Arial';");
		spacePiratePane.setLayoutX(475);
		spacePiratePane.setLayoutY(520);
		spacePiratePane.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
		spacePiratePane.getChildren().add(spacePirateText);
		spacePiratePane.setPrefSize(150, 60);
		spacePiratePane.setVisible(false);
		spacePirateShipObject.setOnMouseEntered((event) -> {
			spacePiratePane.setVisible(true);
		});
		spacePirateShipObject.setOnMouseExited((event) -> {
			spacePiratePane.setVisible(false);
		});
	}

}
