package whateber;

import java.awt.Color;

import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

import processing.core.PVector;

public class Food extends SimulationObject {

	private Ellipse2D.Double foodShape; // geometric shape
	public Color foodColor; // shape color

	private Polygon tail;
	private Ellipse2D.Double eye;

	public Food(float x, float y, float size) {
		super(x, y, 10, 10, size);
		foodColor = Util.randomColor();

	}

	@Override
	public void draw(Graphics2D g) {
		AffineTransform at = g.getTransform();

		g.translate(position.x, position.y);
		g.scale(size, size);
		g.setColor(new Color(135, 54, 37));
		g.fill(tail);

		// draw food
		g.setColor(foodColor);
		g.fill(foodShape);
		// fill eye
		g.setColor(Color.black);
		g.fill(eye);

		g.setTransform(at);
	}

	@Override
	protected void setShapeAttributes() {
		this.foodShape = new Ellipse2D.Double(-width / 2, -height / 2, width, height / 2);

		eye = new Ellipse2D.Double(width / 4, -height / 4, width / 20, width / 20);

		int[] px = { -7, 3, -7 };
		int[] py = { -5, -2, 1 };
		tail = new Polygon(px, py, px.length);
	}

	@Override
	protected void setBoundingBox() {
		boundingBox = new Area(foodShape);
		boundingBox.add(new Area(tail));
	}

	@Override
	public AffineTransform getAffineTransform() {
		AffineTransform at = new AffineTransform();
		at.translate(position.x, position.y);
		at.scale(size, size);
		return at;
	}

	@Override
	public void update(ArrayList<SimulationObject> objList) {
		// nothing, food don't need to be updated
	}

	// public static void getColor() {
	// // TODO Auto-generated method stub
	//
	// foodColor = Util.randomColor();
	// }

}
