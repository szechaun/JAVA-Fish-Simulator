package whateber;

import java.awt.BasicStroke;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

public class Fish extends Animal {

	private Color color; // featured color
	// -----------------------------------------------------------------
	private Arc2D.Double head; // the original pacman body
	private Ellipse2D.Double eye; // the eye
	private Ellipse2D.Double eye2; // the eye

	private Line2D.Double leg; // leg
	private Line2D.Double leg2; // leg
	private Line2D.Double leg3;// leg
	public boolean highLight = false;
	public static float gotSize;
	public static boolean checkHighLisght = false;

	public Fish(float x, float y, float size) {
		super(x, y, 10, 10, size);
		this.color = Util.randomColor();
		gotSize = size;
	}

	@Override
	protected void setShapeAttributes() {
		head = new Arc2D.Double(0, -height / 2, width, height, 0, 180, Arc2D.PIE);
		eye = new Ellipse2D.Double(width - 5, -height / 4, width / 20, width / 20);
		eye2 = new Ellipse2D.Double(width - 2, -height / 4, width / 20, width / 20);
		leg = new Line2D.Double(width - 2, 0, width - 2, 5);

		leg2 = new Line2D.Double(width / 4, 0, width / 4, 5);
		leg3 = new Line2D.Double(width / 2, 0, width / 2, 5);
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
		// legs
		g.setStroke(new BasicStroke(0.2f));
		g.setColor(Color.black);
		g.draw(leg);
		g.draw(leg2);
		g.draw(leg3);
		if (highLight == true) {
			checkHighLisght = true;
			g.drawOval(-5, -10, 20, 20);
		}

		// body
		g.setColor(color);
		g.fill(head);

		// eye
		g.setColor(Color.black);
		if (energy < FULL_ENERGY * .3f)
			g.setColor(Util.randomColor());
		g.fill(eye);
		g.fill(eye2);

		g.setTransform(at);

		drawInfo(g);
	}

	@Override
	protected void setBoundingBox() {
		boundingBox = new Area(head);
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
		return (food instanceof Food);
	}

	public float getSpeedMagnitude() {
		return speedMagnitude;
	}

	protected float getEnergy() {
		return energy;
	}

	protected float getSpeed() {
		return speedMagnitude;
	}

}
