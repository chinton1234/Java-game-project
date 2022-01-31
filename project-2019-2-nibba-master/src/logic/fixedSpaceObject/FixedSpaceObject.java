package logic.fixedSpaceObject;

import javafx.scene.image.Image;
import logic.FixedPoint;
import logic.SpaceObject;

public abstract class FixedSpaceObject extends SpaceObject {

	protected FixedPoint fixedPoint;

	public FixedSpaceObject(String name, Image image, int z, int x, int y, boolean visible, FixedPoint fixedPoint) {
		super(name, image, z, x, y, visible);

		this.fixedPoint = fixedPoint;

	}

	public FixedPoint getFixedPoint() {
		return this.fixedPoint;
	}

}
