package logic;

import logic.fixedSpaceObject.FixedSpaceObject;

public class FixedPoint {

	private int areaNumber;
	private int x;
	private int y;
	private FixedSpaceObject fixedSpaceObject;

	public FixedPoint(int areaNumber) {
		super();
		this.areaNumber = areaNumber;
		this.setLocation(areaNumber);
	}

	public FixedSpaceObject getFixedSpaceObject() {
		return fixedSpaceObject;
	}

	public void setFixedSpaceObject(FixedSpaceObject fixedSpaceObject) {
		this.fixedSpaceObject = fixedSpaceObject;
	}

	private void setLocation(int areaNumber) {
		this.x = 810;
		this.y = 610;
		if (areaNumber > 18) {
			this.y -= ((24 - areaNumber) * 80);
		} else if (areaNumber > 12) {
			this.x -= (6 * 90);
			this.y -= (6 * 80);
			this.x += ((areaNumber - 12) * 90);
		} else if (areaNumber > 6) {
			this.x -= (6 * 90);
			this.y -= (areaNumber - 6) * 80;
		} else {
			this.x -= (areaNumber * 90);
		}
	}

	public int getAreaNumber() {
		return areaNumber;
	}

	public void setAreaNumber(int areaNumber) {
		this.areaNumber = areaNumber;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

}
