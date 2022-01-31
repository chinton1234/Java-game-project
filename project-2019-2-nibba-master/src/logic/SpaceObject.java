package logic;

import javafx.scene.image.Image;
import sharedObject.Renderable;

public abstract class SpaceObject implements Renderable {

	protected String name;
	protected Image image;
	protected int z;
	protected int x;
	protected int y;
	protected boolean visible;

	public SpaceObject(String name, Image image, int z, int x, int y, boolean visible) {
		super();
		this.name = name;
		this.image = image;
		this.z = z;
		this.x = x;
		this.y = y;
		this.visible = visible;
	}

	@Override
	public boolean isVisible() {
		return visible;
	}

	@Override
	public int getZ() {
		return z;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setZ(int z) {
		this.z = z;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

}
