package gui;

import input.InputUtility;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;

public class DiceButton extends Button {

	public DiceButton(String name) {
		this.setText(name);
		this.setAlignment(Pos.CENTER);
		this.setLayoutX(470);
		this.setLayoutY(460);
		this.setPrefSize(160, 40);
		this.setStyle(
				"-fx-padding: 1 ;" + "-fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;" + "-fx-background-radius: 3;"
						+ "-fx-background-color: " + "linear-gradient(from 0% 93% to 0% 100%,black, white),"
						+ "radial-gradient(center 50% 50%, radius 100%,white,black);"
						+ "-fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );" + "-fx-font-weight: bold;"
						+ "-fx-font-size: 2em;");
		this.setTooltip(new Tooltip("Yes,it is a Roll button."));
		this.setCursor(Cursor.HAND);
		this.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				InputUtility.confirmClick();
			}
		});
	}

}
