package whateber;

import java.awt.Color;

import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

import processing.core.PVector;

public class Predator extends Animal {

	private Color color; // featured color
	// -----------------------------------------------------------------
	private Arc2D.Double head; // the original pacman body
	private Ellipse2D.Double eye; // the eye
	private Polygon tail; // tail
	public static float gotSize;
	public static float gotPos;
	public boolean speedBoost = false;

	public static boolean checkHighLisght = false;

	public boolean highLight = false;

	public Predator(float x, float y, float size) {
		super(x, y, 20, 10, size);
		this.color = Util.randomColor();
		gotSize = size;
	}

	@Override
	protected void setShapeAttributes() {
		head = new Arc2D.Double(0, -height / 2, width / 2, height, 0, 300, Arc2D.PIE);
		eye = new Ellipse2D.Double(width / 4, -height / 4, width / 20, width / 20);

		int[] px = { (int) (width / 4), (int) (-width / 2), (int) (-width / 5), (int) (-width / 2) };
		int[] py = { 0, (int) (-height / 2), 0, (int) (height / 2) };
		tail = new Polygon(px, py, px.length);

	}

	@Override
	public void draw(Graphics2D g) {
		// transformation
		AffineTransform at = g.getTransform();
		g.translate(position.x, position.y);
		g.rotate(speed.heading());
		g.scale(size, size);
		if (speed.x < 0)
			g.scale(1, -1);
		g.setColor(Color.black);
		if (highLight == true) {
			checkHighLisght = true;
			g.drawOval(-10, 0 - 10, 20, 20);
		}

		// tail
		g.setColor(color.darker());
		g.fill(tail);

		// body
		g.setColor(color);
		g.fill(head);

		// eye
		g.setColor(Color.black);
		if (energy < FULL_ENERGY * .3f)
			g.setColor(Util.randomColor());
		g.fill(eye);

		g.setTransform(at);

		drawInfo(g);
	}

	@Override
	protected void setBoundingBox() {
		boundingBox = new Area(head);
		boundingBox.add(new Area(tail));
	}

	@Override
	public AffineTransform getAffineTransform() {
		AffineTransform at = new AffineTransform();
		at.translate(position.x, position.y);
		at.rotate(speed.heading());
		at.scale(size, size);
		return at;
	}

	@Override
	protected boolean eatable(SimulationObject food) {
		return (food instanceof Animal && !(food instanceof Predator));
	}

	protected float getEnergy() {
		return energy;
	}

	protected float getSpeed() {
		return speedMagnitude;
	}

}
