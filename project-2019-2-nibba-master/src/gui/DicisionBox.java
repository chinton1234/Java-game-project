package gui;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import logic.Building;
import logic.fixedSpaceObject.Planet;
import logic.player.Player;

public class DicisionBox extends Alert {

	public ButtonType yes, no;

	public DicisionBox(Player player, String type) {
		super(AlertType.CONFIRMATION);
		this.setContent(player, type);
		this.setHeaderText(null);
		this.setResizable(false);
		yes = new ButtonType("Yes!!!", ButtonData.YES);
		no = new ButtonType("Not now.", ButtonData.CANCEL_CLOSE);
		this.getButtonTypes().setAll(yes, no);

	}

	private void setContent(Player player, String type) {

		Planet planet = (Planet) player.getCurrentPoint().getFixedSpaceObject();

		switch (type) {

		case "Capture":
			this.setTitle("Capture!!!!");
			this.setContentText("Do you want to capture " + planet.getName() + "???" + "\nYou'r current resource : "
					+ player.getResource() + "\nCapture cost : " + planet.getCapturedCost() + "\nBalance : "
					+ (player.getResource() - planet.getCapturedCost()));
			break;

		case "Construction":
			this.setTitle("Construction Time!!!");
			this.setContentText("Do you want to build???" + "\nYou'r current resource : " + player.getResource()
					+ "\nBuilding cost : " + planet.getConstructionCost() + "\nBalance : "
					+ (player.getResource() - planet.getConstructionCost()));
			break;

		case "Upgrade":
			Building building = planet.getBuilding();
			this.setTitle("Upgrade Time!!!");
			this.setContentText("Do you want to upgrade building???" + "\nYou'r current resource : "
					+ player.getResource() + "\nUpgrade cost : " + building.getUpgradeCost() + "\nBalance : "
					+ (player.getResource() - building.getUpgradeCost()));

		}
	}
}
