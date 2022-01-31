package gui;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import logic.player.Player;

public class InformationPane extends Pane {

	private Player player;
	private String type;
	private int value;
	private String itemName;
	private Label infor;

	public InformationPane(Player player, String type) {
		this.setPrefSize(320, 50);
		this.setLayoutX(380);
		this.setLayoutY(400);
		this.infor = new Label();
		this.player = player;
		this.type = type;
		this.infor.setStyle("-fx-text-fill: white; -fx-font: normal bold 18px 'Arial';");
		this.infor.setAlignment(Pos.CENTER);
		SetContent();
		this.setBackground(new Background(new BackgroundFill(player.getColor(), null, null)));
		this.getChildren().add(infor);
	}

	public InformationPane(Player player, String type, int value) {
		this.setPrefSize(300, 50);
		this.setLayoutX(390);
		this.setLayoutY(400);
		this.infor = new Label();
		this.player = player;
		this.type = type;
		this.value = value;
		this.infor.setStyle("-fx-text-fill: white; -fx-font: normal bold 18px 'Arial';");
		this.infor.setAlignment(Pos.CENTER);
		SetContent();
		this.setBackground(new Background(new BackgroundFill(player.getColor(), null, null)));
		this.getChildren().add(infor);

	}

	public InformationPane(Player player, String type, String itemName) {
		this.setPrefSize(300, 50);
		this.setLayoutX(390);
		this.setLayoutY(400);
		this.infor = new Label();
		this.player = player;
		this.type = type;
		this.itemName = itemName;
		this.infor.setStyle("-fx-text-fill: white; -fx-font: normal bold 18px 'Arial';");
		this.infor.setAlignment(Pos.CENTER);
		SetContent();
		this.setBackground(new Background(new BackgroundFill(player.getColor(), null, null)));
		this.getChildren().add(infor);
	}

	private void SetContent() {

		switch (type) {

		case "BlackHole Escape": {
			infor.setText(this.player.getName() + " use \"BlackHole Escape\" to\n escape BlackHole.");
			break;
		}
		case "Trapped BlackHole": {
			infor.setText(this.player.getName() + " are trapped \nin the BlackHole");
			break;
		}
		case "Space Lab": {
			infor.setText(this.player.getName() + "'s planets are all improved.");
			break;
		}
		case "Pirate Pass": {
			infor.setText(this.player.getName() + " use \"Pirate Pass\" to \npass Pirate Ship.");
			break;
		}
		case "Pirate Steal": {
			infor.setText(this.player.getName() + " lost " + value + " Resource.");
			break;
		}
		case "Item get": {
			infor.setText(this.player.getName() + " get \"" + itemName + "\".");
			break;
		}
		case "Get Resource": {
			infor.setText(this.player.getName() + " get " + value + " Resource.");
			break;
		}
		case "Attacked": {
			infor.setText(this.player.getName() + " got " + value + " Dammage.");
			break;
		}
		case "Imprison": {
			infor.setText(this.player.getName() + " can't go anywhere.\n " + value + " Turn left.");
			break;
		}
		case "Warp Gate": {
			infor.setText(this.player.getName() + " use \"Warp Gate\".\n Plese set your destination.");
			break;
		}
		case "Barrier": {
			infor.setText(this.player.getName() + " use \"AttackBarier\".\n Dont get any damage.");
			break;
		}
		case "Winner": {
			infor.setText(itemName + " win!!!!");
			break;
		}
		}
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
