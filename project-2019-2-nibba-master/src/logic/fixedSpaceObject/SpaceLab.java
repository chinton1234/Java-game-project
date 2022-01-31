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

public class SpaceLab extends FixedSpaceObject {

	private ImageView spaceLabObject;
	private Pane spaceLabPane;
	private Text spaceLabText;

	public SpaceLab(FixedPoint fixedPoint) {

		super("Space Lab", RenderableHolder.getFixedSpaceObjectImages().get(fixedPoint.getAreaNumber()), 3,
				fixedPoint.getX(), fixedPoint.getY(), true, fixedPoint);
		spaceLabObject = new ImageView(this.image);
		spaceLabObject.setLayoutX(this.getX()-40);
		spaceLabObject.setLayoutY(this.getY()-40);
		spaceLabObject.setFitHeight(80);
		spaceLabObject.setFitWidth(80);
		setInformationLabel();
		RenderableHolder.getInstance().add(this);
	}

	public static void bonusPlanets(Player player) {
		for (int i = 0; i < player.getCapturedPlanets().size(); i++) {

			Planet planet = player.getCapturedPlanets().get(i);
			planet.setAttackPoint((int) (planet.getAttackPoint() * 1.5));
			planet.setResourcePerTurn((planet.getResourcePerTurn() * 2));
			planet.update();
		}

	}

	@Override
	public void draw(Pane pane) {
		// TODO Auto-generated method stub

		pane.getChildren().addAll(spaceLabObject, spaceLabPane);
	}

	private void setInformationLabel() {
		spaceLabText = new Text();
		spaceLabPane = new Pane();
		spaceLabText.setText("\nSpace Lab" + "\nFor the one who looks for\nthe way of science." + "\nPlanet Improved.");
		spaceLabText.setStyle("-fx-font: normal bold 11px 'Arial';");
		spaceLabPane.setLayoutX(475);
		spaceLabPane.setLayoutY(520);
		spaceLabPane.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
		spaceLabPane.getChildren().add(spaceLabText);
		spaceLabPane.setPrefSize(150, 60);
		spaceLabPane.setVisible(false);
		spaceLabObject.setOnMouseEntered((event) -> {
			spaceLabPane.setVisible(true);
		});
		spaceLabObject.setOnMouseExited((event) -> {
			spaceLabPane.setVisible(false);
		});
	}

}
