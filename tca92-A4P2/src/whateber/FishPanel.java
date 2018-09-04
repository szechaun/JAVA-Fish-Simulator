package whateber;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

import processing.core.PVector;

public class FishPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private static ArrayList<SimulationObject> objList;
	private static Timer t;
	private static Dimension pnlSize;
	private static int MAX_FOOD = 3;
	private static int MAX_FISH_KIND = 5;
	private static int MAX_PREDATOR_KIND = 2;
	private static int fishCount;
	private static int predatorCount;

	private static String status = "Status";
	private static ControlPanel cPanel;

	public int timer = 0;

	public static float gotSize;
	public static float gotPacSize = 0;
	public static float gotEnergy;
	public static float gotSpeed;
	public static float gotPos;
	public static boolean panelShow = true;

	public FishPanel(ControlPanel p) {
		super();
		cPanel = p;
		pnlSize = new Dimension(1060, 600);
		this.setPreferredSize(pnlSize);

		// create new animals
		objList = new ArrayList<>();
		for (int i = 0; i < MAX_FOOD; i++)
			objList.add(Util.randomFood(pnlSize));
		addFish();
		addPredator();

		t = new Timer(33, this);
		t.start();
		this.addKeyListener(new MyKeyAdapter());
		this.setFocusable(true);
		addMouseListener(new MyMouseAdapter());
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		pnlSize = getSize();
		setBackground(Color.darkGray);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// draw objects
		for (SimulationObject obj : objList)
			obj.draw(g2);

		cPanel.update(this);

	}

	public static void setStatus(String st) {
		status = st;
	}

	public String getStatus() {
		return status;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for (int i = 0; i < objList.size(); i++)
			objList.get(i).update(objList);
		respawn();
		repaint();

	}

	private void respawn() {
		while (countObject(Food.class) < MAX_FOOD)
			objList.add(Util.randomFood(pnlSize));
		while (countObject(Fish.class) < MAX_FISH_KIND)
			addFish();
		while (countObject(Predator.class) < MAX_PREDATOR_KIND)
			addPredator();

	}

	public class MyKeyAdapter extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				if (t.isRunning()) {
					t.stop();

				}

				else
					t.start();
			}
			if (e.getKeyCode() == KeyEvent.VK_P) { // make panel appear and disappear

				cPanel.setVisible(false);

			}
			if (e.getKeyCode() == KeyEvent.VK_O) {

				cPanel.setVisible(true);

			}

		}
	}

	public int countObject(Class<?> className) {
		if (className == Fish.class)
			return Util.countObject(className, objList);
		if (className == Predator.class)
			return Util.countObject(className, objList);
		return Util.countObject(className, objList);
	}

	public int countTotalObj(Class<?> className) {
		if (className == Fish.class)
			return fishCount;
		if (className == Predator.class)
			return predatorCount;

		return -1;
	}

	// continue program
	public static void tContinue() {
		t.start();
	}

	// stop program
	public static void tStop() {
		t.stop();
	}

	public static void maxFish() {
		MAX_FISH_KIND += 1;
	}

	// addfish
	public static void addFish() {
		objList.add(Util.randomFish(pnlSize));
		fishCount++;
	}

	// addpredator
	public static void addPredator() {
		objList.add(Util.randomPredator(pnlSize));
		predatorCount++;
	}

	// add big food
	public static void addBigFood() {
		objList.add(Util.randomBigFood(pnlSize));

	}

	// predator speed boost
	public static void addPredatorSpeedBoost() {
		for (int i = 0; i < objList.size(); i++) {
			SimulationObject so = objList.get(i);
			if (so instanceof Predator) {
				Predator pw = (Predator) so;
				pw.MAX_SPEED += 5;
			}
		}
	}

	// increase predator size
	public static void futureBigPredator() {

		Util.randomLowerP += 3;
		Util.randomUpperP += 3;

	}

	// increase fish size
	public static void futureBigFish() {
		Util.randomLower += 2;
		Util.randomUpper += 2;
	}

	// increase food size
	public static void futureBigFood() {
		Util.randomLowerF += 2;
		Util.randomUpperF += 2;
	}

	// fish speed boost
	public static void addFishSpeedBoost() {
		for (int i = 0; i < objList.size(); i++) {
			SimulationObject so = objList.get(i);
			if (so instanceof Fish) {
				Fish pw = (Fish) so;
				pw.MAX_SPEED += 3;
			}
		}
	}

	// +fish energy
	public static void addFishEnergy() {
		for (int i = 0; i < objList.size(); i++) {
			SimulationObject so = objList.get(i);
			if (so instanceof Fish) {
				Fish pw = (Fish) so;
				pw.energy += 50;
			}
		}
	}

	// reduce fish speed
	public static void addFishSpeedReduce() {
		for (int i = 0; i < objList.size(); i++) {
			SimulationObject so = objList.get(i);
			if (so instanceof Fish) {
				Fish pw = (Fish) so;
				pw.MAX_SPEED -= 3;
			}
		}
	}

	// add energy for predator
	public static void addEnergyPredator() {
		for (int i = 0; i < objList.size(); i++) {
			SimulationObject so = objList.get(i);
			if (so instanceof Predator) {
				Predator pw = (Predator) so;
				pw.energy += 100;
			}
		}
	}

	// chagne food color
	public static void changeFoodColor() {
		for (int i = 0; i < objList.size(); i++) {
			SimulationObject so = objList.get(i);
			if (so instanceof Food) {
				Food pw = (Food) so;
				pw.foodColor = Util.randomColor();
			}
		}
	}

	// speed boost highlighted fish
	public static void speedBoostHighlight() {
		for (int i = 0; i < objList.size(); i++) {
			SimulationObject so = objList.get(i);
			if (so instanceof Fish && ((Fish) so).highLight) {
				Fish pw = (Fish) so;
				pw.MAX_SPEED += 6;

			}
		}
	}

	// shirnk all object
	public static void shrinkAll() {
		for (int i = 0; i < objList.size(); i++) {
			SimulationObject so = objList.get(i);
			if (so instanceof Fish) {
				Fish pw = (Fish) so;
				pw.size -= 1;

			}
			if (so instanceof Predator) {
				Predator pw = (Predator) so;
				pw.size -= 1;

			}
			if (so instanceof Food) {
				Food pw = (Food) so;
				pw.size -= 1;

			}
		}
	}

	// get highlighted object
	public SimulationObject getHighlightedObject() {
		SimulationObject highlighted = null;

		for (int i = 0; i < objList.size(); i++) {
			SimulationObject so = objList.get(i);
			if (so instanceof Predator) {
				Predator pw = (Predator) so;
				if (pw.highLight)
					highlighted = so;
			}

			if (so instanceof Fish) {
				Fish pw = (Fish) so;
				if (pw.highLight)
					highlighted = so;
			}
		}

		return highlighted;

	}

	private class MyMouseAdapter extends MouseAdapter {

		public void mousePressed(MouseEvent e) {

			for (int i = 0; i < objList.size(); i++) {

				SimulationObject so = objList.get(i);
				if (so instanceof Predator) {
					Predator pw = (Predator) so;

					pw.highLight = false;

				}

				if (so instanceof Fish) {
					Fish pw = (Fish) so;

					pw.highLight = false;

				}

			}

			for (int i = 0; i < objList.size(); i++) {
				if (objList.get(i).checkMouseHit(e)) {

					SimulationObject so = objList.get(i);

					if (so instanceof Predator) {
						Predator pw = (Predator) so;

						pw.highLight = true;

					}

					if (so instanceof Fish) {
						Fish pw = (Fish) so;

						pw.highLight = true;

					}
				}

			}

		}

	}

}
