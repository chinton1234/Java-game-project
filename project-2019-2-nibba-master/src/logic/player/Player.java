package logic.player;

import java.util.ArrayList;

import gui.PlayerStatusBar;
import javafx.scene.paint.Color;
import logic.FixedPoint;
import logic.GameBoard;
import logic.fixedSpaceObject.Planet;
import sharedObject.RenderableHolder;

public class Player {

	private static Color[] playerColors = { Color.BLUE, Color.RED, Color.GREEN, Color.DARKGOLDENROD };
	protected String name;
	protected int number;
	protected boolean isLose;
	protected boolean isImprison;
	protected FixedPoint currentPoint;
	protected int level;
	protected int hp;
	protected int maxHp;
	protected int exp;
	protected int maxExp;
	protected int defensePoint;
	protected int resource;
	protected SpaceShip spaceShip;
	protected ArrayList<Planet> capturedPlanets;
	protected Item item;
	protected Color color;
	protected int imprisonTurnLeft;
	protected PlayerStatusBar playerStatusBar;

	public Player(int playerNumber) {
		super();
		this.name = "Player" + Integer.toString(playerNumber);
		this.number = playerNumber;
		this.isLose = false;
		this.isImprison = false;
		this.currentPoint = GameBoard.getInstace().getFixedPoints().get(0);
		GameBoard.getInstace().getFixedPoints().get(0);
		this.level = 0;
		this.hp = 100;
		this.maxHp = 100;
		this.exp = 0;
		this.maxExp = 50;
		this.defensePoint = 10;
		this.resource = 1000;
		this.spaceShip = new SpaceShip(this);
		this.capturedPlanets = new ArrayList<>();
		this.item = null;
		this.color = playerColors[playerNumber - 1];
		this.imprisonTurnLeft = 0;
		this.playerStatusBar = new PlayerStatusBar(this);
		this.item = new Item("null");
	}

	public void addExp(int addedExp) {
		if (this.exp + addedExp >= this.maxExp) {
			this.exp = (this.exp + addedExp) - this.maxExp;
			this.levelUp();
		} else {
			this.exp += addedExp;
		}
		this.playerStatusBar.update();
	}

	public void addHp(int hp) {
		if (this.hp + hp >= this.maxHp) {
			this.hp = this.maxHp;
		} else {
			this.hp += hp;
		}
		this.playerStatusBar.update();
	}

	public void addResource(int resource) {
		this.resource += resource;
		this.playerStatusBar.update();
	}

	public void capturedPlanet(Planet planet) {

		this.reduceResource(planet.getCapturedCost());
		if (planet.getOwner() != null) {
			Player oldOwner = planet.getOwner();
			oldOwner.getCapturedPlanets().remove(planet);
		}
		planet.setOwner(this);
		this.getCapturedPlanets().add(planet);
		planet.getPlanetCircle().setStroke(this.color);
		this.addExp(20);
		this.playerStatusBar.update();
		planet.multipleCapturedCost();
		planet.update();
	}

	public ArrayList<Planet> getCapturedPlanets() {
		return capturedPlanets;
	}

	public Color getColor() {
		return this.color;
	}

	public FixedPoint getCurrentPoint() {
		return currentPoint;
	}

	public double getExp() {
		return this.exp;
	}

	public double getHp() {
		return this.hp;
	}

	public int getImprisonTurnLeft() {
		return imprisonTurnLeft;
	}

	public Item getItem() {
		return item;
	}

	public int getMaxExp() {
		return this.maxExp;
	}

	public int getMaxHp() {
		return this.maxHp;
	}

	public String getName() {
		return name;
	}

	public int getNumber() {
		return number;
	}

	public int getResource() {
		return resource;
	}

	public SpaceShip getSpaceShip() {
		return spaceShip;
	}

	public boolean isImprison() {
		return isImprison;
	}

	public boolean isLose() {
		return isLose;
	}

	public void levelUp() {
		this.level += 1;
		this.maxHp *= 1.2;
		this.hp *= 1.2;
		this.defensePoint *= 1.2;
		this.maxExp *= 1.5;
		playerStatusBar.update();
	}

	public int reduceHp(int attackPoint) {
		int reducedHp = 0;
		if (!(this.defensePoint > attackPoint)) {
			RenderableHolder.damageSound.play();
			reducedHp = attackPoint - this.defensePoint;
			if (this.hp <= reducedHp) {
				this.hp = 0;
				this.setLose();
			} else {
				this.hp -= reducedHp;
			}
		}
		this.playerStatusBar.update();
		return reducedHp;
	}

	public void reduceImprisonTurnLeft() {
		this.imprisonTurnLeft -= 1;
		if (this.imprisonTurnLeft == 0)
			this.setImprison(false);
	}

	public void reduceResource(double constructionCost) {
		if (constructionCost >= this.resource) {
			this.resource = 0;
		} else {
			this.resource -= constructionCost;
		}
		this.playerStatusBar.update();
	}

	public void setCurrentPoint(FixedPoint fixedPoint) {
		FixedPoint shipPoint = fixedPoint;
		shipPoint.setX(fixedPoint.getX());
		shipPoint.setY(fixedPoint.getY() + 10 - this.getNumber() * 5);
		this.currentPoint = shipPoint;
	}

	public void updateCurrentPoint() {
		this.currentPoint = this.spaceShip.getCurrentPoint();
	}

	public void setImprison(boolean isImprison) {
		this.isImprison = isImprison;
	}

	public void setItem(Item item) {
		this.item = item;
		this.playerStatusBar.update();
	}

	public void setLose() {
		RenderableHolder.loseSound.play();
		this.isLose = true;
	}

	public void setImprisonTurnLeft(int imprisonTurnAmount) {
		this.imprisonTurnLeft = imprisonTurnAmount;

	}

	public int getLevel() {
		return level;
	}

	public int getDefensePoint() {
		return defensePoint;
	}

	public PlayerStatusBar getPlayerStatusBar() {
		return playerStatusBar;
	}

}
