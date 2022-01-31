package logic;

import java.util.ArrayList;

import logic.fixedSpaceObject.AlienShip;
import logic.fixedSpaceObject.BlackHole;
import logic.fixedSpaceObject.SpaceLab;
import logic.fixedSpaceObject.Planet;
import logic.fixedSpaceObject.SpacePirateShip;
import logic.fixedSpaceObject.SpaceRuins;
import logic.fixedSpaceObject.SpaceStation;
import logic.fixedSpaceObject.WarpGate;

public class GameBoard {

	private ArrayList<FixedPoint> fixedPoints;
	public static GameBoard instace = new GameBoard();

	public GameBoard() {

		fixedPoints = new ArrayList<>();

		for (int i = 0; i <= 23; i++) {

			this.getFixedPoints().add(new FixedPoint(i));

		}
		for (int i = 0; i <= 23; i++) {

			this.setFixedObjectToFixedPoint(fixedPoints.get(i));

		}
	}

	private void setFixedObjectToFixedPoint(FixedPoint fixedPoint) {

		if (fixedPoint.getAreaNumber() == 0) {
			this.getFixedPoints().get(fixedPoint.getAreaNumber()).setFixedSpaceObject(new SpaceStation(fixedPoint));
		} else if (fixedPoint.getAreaNumber() == 6) {
			this.getFixedPoints().get(fixedPoint.getAreaNumber()).setFixedSpaceObject(new BlackHole(fixedPoint));
		} else if (fixedPoint.getAreaNumber() == 12) {
			this.getFixedPoints().get(fixedPoint.getAreaNumber()).setFixedSpaceObject(new SpaceLab(fixedPoint));
		} else if (fixedPoint.getAreaNumber() == 18) {
			this.getFixedPoints().get(fixedPoint.getAreaNumber()).setFixedSpaceObject(new WarpGate(fixedPoint));
		} else if (fixedPoint.getAreaNumber() == 9 || fixedPoint.getAreaNumber() == 15) {
			this.getFixedPoints().get(fixedPoint.getAreaNumber()).setFixedSpaceObject(new SpaceRuins(fixedPoint));
		} else if (fixedPoint.getAreaNumber() == 21) {
			this.getFixedPoints().get(fixedPoint.getAreaNumber()).setFixedSpaceObject(new SpacePirateShip(fixedPoint));
		} else if (fixedPoint.getAreaNumber() == 3) {
			this.getFixedPoints().get(fixedPoint.getAreaNumber()).setFixedSpaceObject(new AlienShip(fixedPoint));
		} else {
			this.getFixedPoints().get(fixedPoint.getAreaNumber()).setFixedSpaceObject(new Planet(fixedPoint));
		}
	}

	public ArrayList<FixedPoint> getFixedPoints() {
		return fixedPoints;
	}

	public static GameBoard getInstace() {
		return GameBoard.instace;
	}
}
