package logic.player;

import logic.FixedPoint;
import logic.GameBoard;
import logic.fixedSpaceObject.Planet;

public class BotPlayer extends Player {

	public BotPlayer(int playerNumber) {
		super(playerNumber);
		this.name = "Com" + Integer.toString(playerNumber);
		this.playerStatusBar.update();
	}

	public boolean dicideCapture() {
		if (this.resource + 100 >= ((Planet) this.getCurrentPoint().getFixedSpaceObject()).getCapturedCost())
			return true;
		else
			return false;
	}

	public boolean dicideConstruct() {
		if (this.resource + 100 >= ((Planet) this.getCurrentPoint().getFixedSpaceObject()).getConstructionCost())
			return true;
		else
			return false;
	}

	public boolean dicideUpgrade() {
		if (this.resource + 50 >= ((Planet) this.getCurrentPoint().getFixedSpaceObject()).getBuilding().getUpgradeCost())
			return true;
		else
			return false;
	}

	public int decideWarpDestination() {
		if (this.getCapturedPlanets().size() != 0) {
			return this.getCapturedPlanets().get(0).getFixedPoint().getAreaNumber();
		} else {
			for (FixedPoint fixedPoint : GameBoard.getInstace().getFixedPoints()) {
				if (fixedPoint.getFixedSpaceObject() instanceof Planet) {
					if (((Planet) fixedPoint.getFixedSpaceObject()).getOwner() == null) {
						return fixedPoint.getAreaNumber();
					}
				}
			}
		}
		return 1;
	}

}
