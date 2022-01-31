package logic.fixedSpaceObject;

import java.util.HashMap;
import java.util.Map;

import input.InputUtility;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import logic.AttackAbility;
import logic.Building;
import logic.FixedPoint;
import logic.player.Player;
import sharedObject.RenderableHolder;

public class Planet extends FixedSpaceObject implements AttackAbility {

	private static Map<Integer, String> planetNameMap = new HashMap<>() {
		{
			put(1, "Mercury");
			put(2, "Venus");
			put(4, "Earth");
			put(5, "Moon");
			put(7, "Mars");
			put(8, "Ceres");
			put(10, "Jupiter");
			put(11, "Uranus");
			put(13, "Neptune");
			put(14, "Pluto");
			put(16, "Make-Make");
			put(17, "Eris");
			put(19, "Kepler-176");
			put(20, "Gliese-65");
			put(22, "Tau-Ceiti");
			put(23, "HD-40307");
		}
	};

	private Player owner;
	private int capturedCost;
	private int radius;
	private int resourcePerTurn;
	private int attackPoint;
	private Building building;
	private int constructionCost;
	private Pane planetPane;
	private Text planetText;

	private Circle planetCircle;

	public Planet(FixedPoint fixedPoint) {

		super(planetNameMap.get(fixedPoint.getAreaNumber()),
				RenderableHolder.fixedSpaceObjectImages.get(fixedPoint.getAreaNumber()), 3, fixedPoint.getX(),
				fixedPoint.getY(), true, fixedPoint);
		this.owner = null;
		this.setCapturedCost(fixedPoint.getAreaNumber());
		this.setRadius(fixedPoint.getAreaNumber());
		this.resourcePerTurn = this.capturedCost / 8;
		this.attackPoint = this.capturedCost / 3;
		this.building = null;
		this.constructionCost = this.capturedCost / 2;
		this.planetCircle = new Circle();
		planetCircle.setLayoutX(this.getX());
		planetCircle.setLayoutY(this.getY());
		planetCircle.setRadius(this.getRadius());
		planetCircle.setFill(new ImagePattern(this.image));
		;
		planetCircle.setStroke(Color.WHITE);
		planetCircle.setStrokeWidth(5);
		planetCircle.setOnMouseClicked((MouseEvent e) -> {
			InputUtility.setSelectedPlanetNumber(this.fixedPoint.getAreaNumber());
		});
		setInformationLabel();
		RenderableHolder.getInstance().add(this);
	}

	public void multipleCapturedCost() {
		this.capturedCost *= 2;
	}

	@Override
	public int attack(Player player) {
		return player.reduceHp(attackPoint);
	}

	public void constructBuilding() {
		this.owner.reduceResource(this.constructionCost);
		this.building = new Building(this);
		this.update();
	}

	@Override
	public void draw(Pane spacePane) {
		spacePane.getChildren().addAll(planetCircle, planetPane);
	}

	public int getAttackPoint() {
		return attackPoint;
	}

	public Building getBuilding() {
		return this.building;
	}

	public int getCapturedCost() {
		return capturedCost;
	}

	public int getConstructionCost() {
		return constructionCost;
	}

	public Player getOwner() {
		return owner;
	}

	public Shape getPlanetCircle() {
		return planetCircle;
	}

	public int getRadius() {
		return radius;
	}

	public int getResourcePerTurn() {
		return resourcePerTurn;
	}

	public void setAttackPoint(int attackPoint) {
		this.attackPoint = attackPoint;
	}

	public void setCapturedCost(int areaNumber) {
		if (areaNumber < 6) {
			this.capturedCost = 100 + (5 * (areaNumber - 1));
		} else if (areaNumber < 12) {
			this.capturedCost = 150 + (10 * (areaNumber - 7));
		} else if (areaNumber < 18) {
			this.capturedCost = 250 + (25 * (areaNumber - 13));
		} else {
			this.capturedCost = 400 + (40 * (areaNumber - 19));
		}
	}

	public void setOwner(Player owner) {
		this.owner = owner;
	}

	public void setRadius(int areaNumber) {
		if (areaNumber == 1 || areaNumber == 7 || areaNumber == 16)
			this.radius = 25;
		else if (areaNumber == 2 || areaNumber == 17 || areaNumber == 20)
			this.radius = 27;
		else if (areaNumber == 4 || areaNumber == 19 || areaNumber == 23)
			this.radius = 29;
		else if (areaNumber == 5 || areaNumber == 8 || areaNumber == 14)
			this.radius = 31;
		else if (areaNumber == 11 || areaNumber == 13 || areaNumber == 22)
			this.radius = 33;
		else if (areaNumber == 10)
			this.radius = 35;
	}

	public void setResourcePerTurn(int resourcePerTurn) {
		this.resourcePerTurn = resourcePerTurn;
	}

	public void update() {
		this.planetCircle.setStroke(owner.getColor());
		planetText.setText("\n" + this.name + "\nCapture cost: " + this.capturedCost + "\nAttack: " + this.attackPoint
				+ "\nResource per turn:" + this.resourcePerTurn);
	}

	private void setInformationLabel() {
		planetPane = new Pane();
		planetText = new Text();
		planetText.setText("\n" + this.name + "\nCapture cost: " + this.capturedCost + "\nAttack: " + this.attackPoint
				+ "\nResource per turn:" + this.resourcePerTurn);
		planetText.setStyle("-fx-font: normal bold 11px 'Arial';");
		planetPane.setLayoutX(490);
		planetPane.setLayoutY(520);
		planetPane.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
		planetPane.getChildren().add(planetText);
		planetPane.setPrefSize(120, 60);
		planetPane.setVisible(false);
		planetCircle.setOnMouseEntered((event) -> {
			planetPane.setVisible(true);
		});
		planetCircle.setOnMouseExited((event) -> {
			planetPane.setVisible(false);
		});
	}

}
