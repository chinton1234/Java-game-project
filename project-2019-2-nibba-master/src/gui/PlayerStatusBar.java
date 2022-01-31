package gui;

import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import logic.player.Player;
import sharedObject.Renderable;
import sharedObject.RenderableHolder;

public class PlayerStatusBar extends VBox implements Renderable {

	private Player player;
	protected int z;
	protected boolean visible, destroyed;
	private Label nameLabel, HpLabel, ExpLabel, defResource, Item;
	private ProgressBar HpBar, ExpBar;

	public PlayerStatusBar(Player player) {
		super();
		this.z = 5;
		this.player = player;
		this.visible = true;
		this.destroyed = false;
		this.setPrefSize(190, 130);
		this.SetLayout(player.getNumber());
		this.setBackground(new Background(new BackgroundFill(player.getColor(), null, null)));

		nameLabel = new Label();
		nameLabel.setText(this.player.getName() + "  LV: " + this.player.getLevel());
		nameLabel.setStyle("-fx-text-fill: white; -fx-font: normal bold 24px 'serif';");
		this.getChildren().add(nameLabel);

		HpBar = new ProgressBar();
		Double MaxHealth = Double.valueOf(this.player.getMaxHp());
		Double CurHealth = Double.valueOf(this.player.getHp());
		HpBar.setProgress(CurHealth / MaxHealth);
		HpBar.setStyle(
				"-fx-accent: firebrick;" + "-fx-control-inner-background: palegreen;" + "-fx-text-box-border: black;");
		HpLabel = new Label();
		HpLabel.setText("Hp: " + this.player.getHp() + "/" + this.player.getMaxHp());
		HpLabel.setStyle("-fx-text-fill: white; -fx-font: normal bold 12px 'Arial';");
		HBox HpZone = new HBox();
		HpZone.getChildren().addAll(HpBar, HpLabel);

		ExpBar = new ProgressBar();
		Double MaxExp = Double.valueOf(this.player.getMaxExp());
		Double CurExp = Double.valueOf(this.player.getExp());
		ExpBar.setProgress(CurExp / MaxExp);
		ExpBar.setStyle(
				"-fx-accent: gold;" + "-fx-control-inner-background: palegreen;" + "-fx-text-box-border: black;");
		ExpLabel = new Label();
		ExpLabel.setText("Exp: " + this.player.getExp() + "/" + this.player.getMaxExp());
		ExpLabel.setStyle("-fx-text-fill: white; -fx-font: normal bold 12px 'Arial';");
		HBox ExpZone = new HBox();
		ExpZone.getChildren().addAll(ExpBar, ExpLabel);

		this.getChildren().addAll(HpZone, ExpZone);

		defResource = new Label();
		defResource.setText("Defense: " + this.player.getDefensePoint() + "    Resource: " + this.player.getResource());
		defResource.setStyle("-fx-text-fill: white;");
		this.getChildren().add(defResource);

		Item = new Label();
		if (player.getItem() == null)
			Item.setText("Item: ");
		else {
			Item.setText("Item: " + this.player.getItem().getName());
		}
		Item.setStyle("-fx-text-fill: white; -fx-font: normal 16px 'Arial';");
		this.getChildren().add(Item);

		RenderableHolder.getInstance().add(this);
	}

	private void SetLayout(int playerNumber) {
		switch (playerNumber) {
		case 1:
			this.setLayoutX(10);
			this.setLayoutY(10);
			break;
		case 2:
			this.setLayoutX(880);
			this.setLayoutY(10);
			break;
		case 3:
			this.setLayoutX(880);
			this.setLayoutY(570);
			break;
		case 4:
			this.setLayoutX(10);
			this.setLayoutY(570);
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + this.player.getNumber());
		}
	}

	@Override
	public int getZ() {
		return this.z;
	}

	@Override
	public void draw(Pane pane) {
		// TODO Auto-generated method stub
		pane.getChildren().add(this);
	}

	public void update() {
		nameLabel.setText(this.player.getName() + "  LV: " + this.player.getLevel());
		Double MaxHealth = Double.valueOf(this.player.getMaxHp());
		Double CurHealth = Double.valueOf(this.player.getHp());
		HpBar.setProgress(CurHealth / MaxHealth);
		HpLabel.setText("Hp:" + CurHealth + "/" + MaxHealth);
		Double MaxExp = Double.valueOf(this.player.getMaxExp());
		Double CurExp = Double.valueOf(this.player.getExp());
		ExpBar.setProgress(CurExp / MaxExp);
		ExpLabel.setText("Exp:" + CurExp + "/" + MaxExp);
		defResource.setText("Defense: " + this.player.getDefensePoint() + "    Resource: " + this.player.getResource());
		if (player.getItem() == null)
			Item.setText("Item: ");
		else {
			Item.setText("Item: " + this.player.getItem().getName());
		}
	}

}