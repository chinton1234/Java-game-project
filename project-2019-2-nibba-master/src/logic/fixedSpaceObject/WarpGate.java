package logic.fixedSpaceObject;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import logic.FixedPoint;
import sharedObject.RenderableHolder;

public class WarpGate extends FixedSpaceObject {

	private ImageView warpGateObject;
	private Pane warpGatePane;
	private Text warpGateText;

	public WarpGate(FixedPoint fixedPoint) {
		super("Warp Gate", RenderableHolder.getFixedSpaceObjectImages().get(fixedPoint.getAreaNumber()), 3,
				fixedPoint.getX(), fixedPoint.getY(), true, fixedPoint);
		warpGateObject = new ImageView(this.image);
		warpGateObject.setLayoutX(this.getX()-40);
		warpGateObject.setLayoutY(this.getY()-40);
		warpGateObject.setFitHeight(80);
		warpGateObject.setFitWidth(80);
		setInformationLabel();
		RenderableHolder.getInstance().add(this);
	}

	@Override
	public void draw(Pane pane) {

		pane.getChildren().addAll(warpGateObject, warpGatePane);
	}

	private void setInformationLabel() {
		warpGateText = new Text();
		warpGatePane = new Pane();
		warpGateText.setText("\nWarp Gate" + "\nThe technology that take" + "\nyou everywhere you want."
				+ "\nWhy are we still here.");
		warpGateText.setStyle("-fx-font: normal bold 11px 'Arial';");
		warpGatePane.setLayoutX(475);
		warpGatePane.setLayoutY(520);
		warpGatePane.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
		warpGatePane.getChildren().add(warpGateText);
		warpGatePane.setPrefSize(150, 60);
		warpGatePane.setVisible(false);
		warpGateObject.setOnMouseEntered((event) -> {
			warpGatePane.setVisible(true);
		});
		warpGateObject.setOnMouseExited((event) -> {
			warpGatePane.setVisible(false);
		});
	}
}
