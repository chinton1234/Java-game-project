package logic;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import logic.fixedSpaceObject.Planet;
import sharedObject.RenderableHolder;

public class Building {

	private int level;
	private Image image;
	public static final int MAX_LEVEL = 3;
	private Planet locatedPlanet;
	private int upgradeCost;
	private ImageView buildingObject;

	public Building(Planet planet) {
		super();
		this.level = 1;
		this.image = RenderableHolder.buildingImages.get(0);
		this.buildingObject = new ImageView(this.image);
		this.locatedPlanet = planet;
		this.upgradeCost = planet.getConstructionCost() * 3;
		this.buildingObject.setLayoutX(locatedPlanet.getX() - 20);
		this.buildingObject.setLayoutY(locatedPlanet.getY() - 35);
		this.buildingObject.setFitHeight(30);
		this.buildingObject.setFitWidth(30);
	}

	public void upgrade() {
		this.locatedPlanet.getOwner().reduceResource(this.upgradeCost);
		this.level += 1;
		this.upgradeCost *= 3;
		this.locatedPlanet.setAttackPoint(this.locatedPlanet.getAttackPoint() * 2);
		this.update();
		this.locatedPlanet.setAttackPoint(this.locatedPlanet.getAttackPoint() * 2);
		this.locatedPlanet.setResourcePerTurn(this.locatedPlanet.getResourcePerTurn() * 2);
		this.locatedPlanet.update();
	}

	public double getUpgradeCost() {
		return this.upgradeCost;
	}

	public void update() {
		this.image = RenderableHolder.buildingImages.get(this.level - 1);
		this.buildingObject.setFitHeight(50);
		this.buildingObject.setFitWidth(20);
		this.buildingObject.setImage(this.image);
	}

	public ImageView getBuildingObject() {
		return buildingObject;
	}

	public int getLevel() {
		return level;
	}

}
