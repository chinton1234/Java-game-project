package sharedObject;

import javafx.scene.layout.Pane;

public interface Renderable {

	public int getZ();

	public void draw(Pane gameScreen);

	public boolean isVisible();

}
