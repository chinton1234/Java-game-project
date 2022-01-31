package logic.fixedSpaceObject;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import logic.FixedPoint;
import logic.player.Player;
import sharedObject.RenderableHolder;

public class SpaceStation extends FixedSpaceObject {

	private ImageView spaceStationObject;
	private Pane spaceStationPane;
	private Text spaceStationText;

	public SpaceStation(FixedPoint fixedPoint) {
		super("Space Station", RenderableHolder.getFixedSpaceObjectImages().get(0), 3, fixedPoint.getX(),
				fixedPoint.getY(), true, fixedPoint);
		spaceStationObject = new ImageView(this.image);
		spaceStationObject.setLayoutX(this.getX());
		spaceStationObject.setLayoutY(this.getY());
		spaceStationObject.setFitHeight(40);
		spaceStationObject.setFitWidth(40);
		setInformationLabel();
		RenderableHolder.getInstance().add(this);
	}

	public static void bonusPlayer(Player player) {
		player.addExp(player.getMaxExp() / 3);
		player.addHp(player.getMaxHp() / 3);
	}

	@Override
	public void draw(Pane pane) {

		pane.getChildren().addAll(spaceStationObject, spaceStationPane);
	}

	private void setInformationLabel() {
		spaceStationText = new Text();
		spaceStationPane = new Pane();
		spaceStationText.setText("\nSpace Station" + "\nThis is your start point." + "\nWhen you came pass us,"
				+ "\nThere are supplies for you.");
		spaceStationText.setStyle("-fx-font: normal bold 11px 'Arial';");
		spaceStationPane.setLayoutX(475);
		spaceStationPane.setLayoutY(520);
		spaceStationPane.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
		spaceStationPane.getChildren().add(spaceStationText);
		spaceStationPane.setPrefSize(150, 60);
		spaceStationPane.setVisible(false);
		spaceStationObject.setOnMouseEntered((event) -> {
			spaceStationPane.setVisible(true);
		});
		spaceStationObject.setOnMouseExited((event) -> {
			spaceStationPane.setVisible(false);
		});
	}
}
