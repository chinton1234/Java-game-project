package gui;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import sharedObject.Renderable;
import sharedObject.RenderableHolder;

public class GameScreen extends Pane {

	public GameScreen() {
		super();
		this.setPrefSize(1080, 720);
		this.setVisible(true);
		BackgroundSize backgroundSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false,
				false, true);
		this.setBackground(new Background(new BackgroundImage(RenderableHolder.gameSceneBg, BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, backgroundSize)));
	}

	public void drawAllComponent() {
		for (Renderable renderableObject : RenderableHolder.getInstance().getRenderableObjects()) {
			{
				renderableObject.draw(this);
			}
		}
	}

}
