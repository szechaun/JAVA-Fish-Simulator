package whateber;

import java.awt.BorderLayout;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class ControlPanel extends JPanel {
	private static final String Fish = null;
	private JLabel status;
	private Container bottom;

	private Container top;
	// panel n button for fish
	private JPanel fishPanel;
	private JButton addFish;
	private JButton maxFish;
	// panel n button for predator
	private JPanel predatorPanel;
	private JButton addPredator;
	private JButton addPredatorSpeedBoost;

	// panel n button for food
	private JPanel foodPanel;
	private JButton oneBigFood;
	// private JButton changeFoodColor;

	// checkbox
	private JCheckBox speedBoostGlobal;
	// combobox
	private JPanel boxPanel;
	private JComboBox box;
	private static String[] simulationState = { "Continue Simulation", "Pause Simulation" };

	private JLabel fishNumLabel;
	private JTextField fishNumTextField;
	private JLabel posLabel;
	private JTextField posTextField;
	private JLabel sizeLabel;
	private JTextField sizeTextField;
	private JLabel speedLabel;
	private JTextField speedTextField;
	private JLabel energyLabel;
	private JTextField energyTextField;
	private Container center;

	JMenuBar menuBar;
	JMenu exit;

	public ControlPanel() {
		super();
		setComponentAttributes();
		setLayout(new BorderLayout());
		add(status, BorderLayout.SOUTH);
		add(top, BorderLayout.NORTH);// top section
		add(center, BorderLayout.CENTER);// section under

	}
	// set button attributes

	private void setComponentAttributes() {
		// new checkbox
		speedBoostGlobal = new JCheckBox("+speed");
		// add speed when checked
		speedBoostGlobal.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (speedBoostGlobal.isSelected()) {
					FishPanel.addFishSpeedBoost();
				} else {
					FishPanel.addFishSpeedReduce();
				}
			}
		});
		// new combo box
		box = new JComboBox(simulationState);
		// start and pause simulation depending on state
		box.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (box.getSelectedIndex() == 0) {
					FishPanel.tContinue();
				} else {
					FishPanel.tStop();
				}
			}
		});
		// setting combo box in a panel
		boxPanel = new JPanel();
		boxPanel.setBorder(BorderFactory.createTitledBorder("Simulation"));
		boxPanel.setLayout(new FlowLayout(6, 6, FlowLayout.LEFT));
		boxPanel.add(box);
		// bottom status bar
		status = new JLabel("Status...");
		bottom = new Container();
		bottom.setLayout(new FlowLayout(FlowLayout.LEFT, 12, 5));
		bottom.add(status);

		// add fish button
		addFish = new JButton("Add Fish");// button adds fish
		addFish.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				FishPanel.addFish();
			}
		});
		// +1 to minimum amount of fish in simulation
		maxFish = new JButton("+1Max Fish in Sim");// button adds fish
		maxFish.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				FishPanel.maxFish();
			}
		});

		// setting what is in fish panel
		fishPanel = new JPanel();
		fishPanel.setBorder(BorderFactory.createTitledBorder("Fish"));
		fishPanel.setLayout(new FlowLayout(6, 6, FlowLayout.LEFT));
		fishPanel.add(addFish);
		fishPanel.add(maxFish);
		fishPanel.add(speedBoostGlobal);
		// food button
		oneBigFood = new JButton("One Giant FOOD");// button adds a giant food
		oneBigFood.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				FishPanel.addBigFood();
			}
		});
		// setting what is in the foodPanel
		foodPanel = new JPanel();
		foodPanel.setBorder(BorderFactory.createTitledBorder("Food"));
		foodPanel.setLayout(new FlowLayout(6, 6, FlowLayout.LEFT));
		foodPanel.add(oneBigFood);

		// predator speedboost
		addPredatorSpeedBoost = new JButton("Speed Boost");
		addPredatorSpeedBoost.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				FishPanel.addPredatorSpeedBoost();
			}
		});

		// add predator button
		addPredator = new JButton("Add Predator");// buttons adds predator
		addPredator.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				FishPanel.addPredator();
			}
		});
		// setting what is in predator panel
		predatorPanel = new JPanel();
		predatorPanel.setBorder(BorderFactory.createTitledBorder("Predator"));
		predatorPanel.setLayout(new FlowLayout(6, 6, FlowLayout.LEFT));
		predatorPanel.add(addPredator);
		predatorPanel.add(addPredatorSpeedBoost);

		// panels to the top
		top = new Container();
		top.setLayout(new FlowLayout(FlowLayout.LEFT));
		top.add(fishPanel);
		top.add(predatorPanel);
		top.add(foodPanel);
		top.add(boxPanel);

		// top.add(changeFoodColor);
		// fish label and textfield
		fishNumLabel = new JLabel("fish:");
		fishNumTextField = new JTextField(4);
		fishNumTextField.setFocusable(false);
		fishNumTextField.setHorizontalAlignment(SwingConstants.CENTER);
		center = new Container();
		center.setLayout(new FlowLayout(FlowLayout.LEFT, 12, 10));
		center.add(fishNumLabel);
		center.add(fishNumTextField);
		// pos.x label and textfield
		posLabel = new JLabel("Pos.x:");
		posTextField = new JTextField(3);
		center.add(posLabel);
		center.add(posTextField);

		// size label and textfield
		sizeLabel = new JLabel("size:");
		sizeTextField = new JTextField(5);
		center.add(sizeLabel);
		center.add(sizeTextField);
		// speed label adn textfield
		speedLabel = new JLabel("speed:");
		speedTextField = new JTextField(5);
		center.add(speedLabel);
		center.add(speedTextField);
		// energy label and textfield
		energyLabel = new JLabel("energy:");
		energyTextField = new JTextField(5);
		center.add(energyLabel);
		center.add(energyTextField);

	}

	public void update(FishPanel p) {

		// create highlight
		SimulationObject highLight;
		status.setText(p.getStatus());
		fishNumTextField.setText(String.format("%d / %d", p.countObject(Fish.class), p.countTotalObj(Fish.class)));

		highLight = p.getHighlightedObject();
		// if highlight is true get highlighted pos size speed and energy
		if (highLight != null) {
			// if instance is predator
			if ((highLight instanceof Predator)) {
				Predator highLightv2 = (Predator) highLight;

				posTextField.setText(String.format("%.1f", highLightv2.getPos().x));
				sizeTextField.setText(String.format("%.2f", (highLightv2.getSize())));
				speedTextField.setText(String.format("%.2f", highLightv2.getSpeed()));
				energyTextField.setText(String.format("%.2f", highLightv2.getEnergy()));
			}
			// if instance is fish
			else if (highLight instanceof Fish) {
				Fish highLightv2 = (Fish) highLight;

				posTextField.setText(String.format("%.1f", highLightv2.getPos().x));
				sizeTextField.setText(String.format("%.2f", highLightv2.getSize()));
				speedTextField.setText(String.format("%.2f", highLightv2.getSpeed()));
				energyTextField.setText(String.format("%.2f", highLightv2.getEnergy()));

			}
		}

	}

}
