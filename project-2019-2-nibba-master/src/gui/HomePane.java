package gui;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class HomePane extends Pane {
	private String buttonStyle = "-fx-base: gray;" + "-fx-font-weight: bold;" + "-fx-font-size: 30px;";
	public Button p1;
	public Button p2;
	public Button p3;
	public Button p4;
	public Button QuitButton;

	public HomePane() {

		this.setStyle("-fx-background-color: black;");
		this.setPrefSize(800, 600);

		Label line1 = new Label();
		line1.setLayoutX(0);
		line1.setLayoutY(40);
		line1.setPrefSize(800, 100);
		line1.setAlignment(Pos.CENTER);
		line1.setStyle("-fx-background-color: gray;" + "-fx-text-fill: white;" + "-fx-background-radius: 100;"
				+ "-fx-font-size:50px;" + "-fx-font-weight: bold;");
		line1.setText("Galactic Colonization");

		HBox line2 = new HBox(38);
		line2.setLayoutX(0);
		line2.setLayoutY(200);
		line2.setPrefSize(800, 100);
		line2.setAlignment(Pos.CENTER);
		p1 = new Button("1 Player");
		p1.setPrefSize(200, 100);
		p1.setStyle(buttonStyle);
		p2 = new Button("2 Player");
		p2.setPrefSize(200, 100);
		p2.setStyle(buttonStyle);
		line2.getChildren().addAll(p1, p2);

		HBox line3 = new HBox(38);
		line3.setLayoutX(0);
		line3.setLayoutY(338);
		line3.setPrefSize(800, 100);
		line3.setAlignment(Pos.CENTER);
		p3 = new Button("3 Player");
		p3.setPrefSize(200, 100);
		p3.setStyle(buttonStyle);
		p4 = new Button("4 Player");
		p4.setPrefSize(200, 100);
		p4.setStyle(buttonStyle);
		line3.getChildren().addAll(p3, p4);

		QuitButton = new Button("QUIT");
		QuitButton.setPrefSize(190, 80);
		QuitButton.setLayoutX(305);
		QuitButton.setLayoutY(470);
		QuitButton.setStyle(buttonStyle);
		this.getChildren().addAll(line1, line2, line3, QuitButton);
	}
}
