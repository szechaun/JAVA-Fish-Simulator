package whateber;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

public class MenuBar extends JPanel {
	// menu bar
	JMenuBar menuBar;
	// menu
	JMenu addPredator, addEnergyPredator, futureGeneration;
	// menu items
	JMenuItem addOnePredator, addTwoPredator, add100EPredator, add200EPredator, bigFish, bigPredator, bigFood,
			fishSpeedEnergy, changeFoodColor, highlightedBoost, shrinkAll;

	private Container top;

	public MenuBar() {
		setLayout(new BorderLayout());
		setComponentAttributes();
		add(top);
	}

	private void setComponentAttributes() {
		// menu bar items
		menuBar = new JMenuBar();
		JMenu predator = new JMenu("Predator");
		menuBar.add(predator);
		JMenu fish = new JMenu("Fish");
		menuBar.add(fish);
		JMenu food = new JMenu("Food");
		menuBar.add(food);
		JMenu properties = new JMenu("Properties");
		menuBar.add(properties);

		// put menu bar item in top container
		top = new Container();
		top.setLayout(new FlowLayout(FlowLayout.LEFT));
		top.add(menuBar);

		// predator menu
		addPredator = new JMenu("add predators");
		predator.add(addPredator);
		// predator +1
		addOnePredator = new JMenuItem("+1 Predators");
		addOnePredator.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				FishPanel.addPredator();

			}
		});
		addPredator.add(addOnePredator);
		// predator +2
		addTwoPredator = new JMenuItem("+2 Predators");
		addTwoPredator.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				FishPanel.addPredator();
				FishPanel.addPredator();
			}
		});
		addPredator.add(addTwoPredator);
		// predator energy menu
		addEnergyPredator = new JMenu("add energy");
		predator.add(addEnergyPredator);
		// predator +100 energy
		add100EPredator = new JMenuItem("+100 energy");
		add100EPredator.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				FishPanel.addEnergyPredator();
			}
		});
		addEnergyPredator.add(add100EPredator);
		// predator +200 energy
		add200EPredator = new JMenuItem("+200 energy");
		add200EPredator.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				FishPanel.addEnergyPredator();
				FishPanel.addEnergyPredator();
			}
		});
		addEnergyPredator.add(add200EPredator);

		// future generation menu
		futureGeneration = new JMenu("Future Generation");
		properties.add(futureGeneration);
		// generate big fish
		bigFish = new JMenuItem("Bigger fish from now on");
		futureGeneration.add(bigFish);
		bigFish.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				FishPanel.futureBigFish();
			}
		});
		// generate big predator
		bigPredator = new JMenuItem("Bigger predator from now on");
		futureGeneration.add(bigPredator);
		bigPredator.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				FishPanel.futureBigPredator();
			}
		});
		// generate big food
		bigFood = new JMenuItem("Bigger food from now on");
		futureGeneration.add(bigFood);
		bigFood.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				FishPanel.futureBigFood();
			}
		});
		// fish + speed and energy
		fishSpeedEnergy = new JMenuItem("+speed and energy");
		fish.add(fishSpeedEnergy);
		fishSpeedEnergy.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				FishPanel.addFishSpeedBoost();
				FishPanel.addFishEnergy();

			}
		});
		// change food color
		changeFoodColor = new JMenuItem("Change food color");
		food.add(changeFoodColor);
		changeFoodColor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				FishPanel.changeFoodColor();
			}
		});

		// speed boost highlighted fish
		highlightedBoost = new JMenuItem("SuperBoost highlighted fish");
		fish.add(highlightedBoost);
		highlightedBoost.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				FishPanel.speedBoostHighlight();
			}
		});
		//shrink everything in property menu
		shrinkAll = new JMenuItem("SHRINNK EVERYTHING");
		properties.add(shrinkAll);
		shrinkAll.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				FishPanel.shrinkAll();
			}
		});

	}

}
