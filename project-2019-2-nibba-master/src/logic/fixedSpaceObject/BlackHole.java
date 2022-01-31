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

public class BlackHole extends FixedSpaceObject {

	private final static int imprisonTurnAmount = 2;
	private ImageView blackHoleObject;
	private Pane blackHolePane;
	private Text blackHoleText;

	public BlackHole(FixedPoint fixedPoint) {
		super("BlackHole", RenderableHolder.fixedSpaceObjectImages.get(6), 3, fixedPoint.getX(), fixedPoint.getY(),
				true, fixedPoint);
		blackHoleObject = new ImageView(this.image);
		blackHoleObject.setLayoutX(this.getX() - 40);
		blackHoleObject.setLayoutY(this.getY() - 40);
		blackHoleObject.setFitHeight(80);
		blackHoleObject.setFitWidth(80);
		setInformationLabel();
		RenderableHolder.getInstance().add(this);
	}

	@Override
	public void draw(Pane pane) {
		pane.getChildren().addAll(blackHoleObject, blackHolePane);
	}

	public static void imprisonPlayer(Player player) {
		player.setImprison(true);
		player.setImprisonTurnLeft(imprisonTurnAmount);
	}

	private void setInformationLabel() {
		blackHoleText = new Text();
		blackHolePane = new Pane();
		blackHoleText.setText(
				"\nBlackHole" + "\nAt this point, It is very" + "\n time-consuming to get out " + "\nof here.");
		blackHoleText.setStyle("-fx-font: normal bold 11px 'Arial';");
		blackHolePane.setLayoutX(475);
		blackHolePane.setLayoutY(520);
		blackHolePane.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
		blackHolePane.getChildren().add(blackHoleText);
		blackHolePane.setPrefSize(150, 60);
		blackHolePane.setVisible(false);
		blackHoleObject.setOnMouseEntered((event) -> {
			blackHolePane.setVisible(true);
		});
		blackHoleObject.setOnMouseExited((event) -> {
			blackHolePane.setVisible(false);
		});
	}

}
