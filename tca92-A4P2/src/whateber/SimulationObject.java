package whateber;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.util.ArrayList;

import processing.core.PVector;

public abstract class SimulationObject {
	protected PVector position;
	protected float width, height, size;
	protected Area boundingBox;

	public SimulationObject(float x, float y, float w, float h, float size) {
		this.position = new PVector(x, y);
		this.width = w;
		this.height = h;
		this.size = size;
		setShapeAttributes();
		setBoundingBox();
	}

	public abstract void draw(Graphics2D g2);

	public abstract void update(ArrayList<SimulationObject> objList);

	protected abstract void setShapeAttributes();

	protected abstract void setBoundingBox();

	public abstract AffineTransform getAffineTransform();

	public float getSize() {
		return size;
	}

	public PVector getPos() {
		return position;
	}

	public Shape getBoundary() {
		return getAffineTransform().createTransformedShape(boundingBox);
	}

	protected boolean collides(SimulationObject other) {
		return (getBoundary().intersects(other.getBoundary().getBounds2D())
				&& other.getBoundary().intersects(getBoundary().getBounds2D()));
	}

	public boolean checkMouseHit(MouseEvent e) {

		return getBoundary().contains(e.getX(), e.getY());
	}

}
