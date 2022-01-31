package logic.fixedSpaceObject;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import logic.FixedPoint;
import logic.player.Item;
import sharedObject.RenderableHolder;

public class SpaceRuins extends FixedSpaceObject {

	private ImageView spaceRuinsObject;
	private Pane spaceRuinsPane;
	private Text spaceRuinText;

	public SpaceRuins(FixedPoint fixedPoint) {
		super("Space Ruins", RenderableHolder.getFixedSpaceObjectImages().get(fixedPoint.getAreaNumber()), 3,
				fixedPoint.getX(), fixedPoint.getY(), true, fixedPoint);
		spaceRuinsObject = new ImageView(this.image);
		spaceRuinsObject.setLayoutX(this.getX());
		spaceRuinsObject.setLayoutY(this.getY());
		spaceRuinsObject.setFitHeight(40);
		spaceRuinsObject.setFitWidth(40);
		setInformationLabel();
		RenderableHolder.getInstance().add(this);
	}

	@Override
	public void draw(Pane pane) {
		pane.getChildren().addAll(spaceRuinsObject, spaceRuinsPane);
	}

	public static Item giveItem() {

		return new Item(Item.randomItemPerFrame());

	}

	private void setInformationLabel() {
		spaceRuinText = new Text();
		spaceRuinsPane = new Pane();
		spaceRuinText.setText("\nSpace Ruin" + "\nThere are many useful item.");
		spaceRuinText.setStyle("-fx-font: normal bold 11px 'Arial';");
		spaceRuinsPane.setLayoutX(475);
		spaceRuinsPane.setLayoutY(520);
		spaceRuinsPane.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
		spaceRuinsPane.getChildren().add(spaceRuinText);
		spaceRuinsPane.setPrefSize(150, 60);
		spaceRuinsPane.setVisible(false);
		spaceRuinsObject.setOnMouseEntered((event) -> {
			spaceRuinsPane.setVisible(true);
		});
		spaceRuinsObject.setOnMouseExited((event) -> {
			spaceRuinsPane.setVisible(false);
		});
	}
}
