package gui;

import java.util.Random;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sharedObject.RenderableHolder;

public class Dice extends ImageView {

	private int dicePoint;
	private Image diceFace;

	public Dice(int diceNumber) {
		this.randomPerFrame();
		this.prefHeight(100);
		this.prefWidth(100);
		if (diceNumber == 1) {
			this.setLayoutX(400);
		} else {
			this.setLayoutX(600);
		}
		this.setLayoutY(350);
	}

	public int getDicePoint() {
		return dicePoint;
	}

	public int randomPerFrame() {
		Random random = new Random();
		this.dicePoint = random.nextInt(6) + 1;
		this.diceFace = RenderableHolder.diceFaceImages.get(this.dicePoint - 1);
		this.setImage(this.diceFace);
		return this.dicePoint;
	}

}
