package whateber;

import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

public class FishApp extends JFrame {

	boolean ShowPanel = true;

	public FishApp() {

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		ControlPanel cPanel = new ControlPanel(); // new
		FishPanel mainPanel = new FishPanel(cPanel);
		MenuBar mPanel = new MenuBar();

		setLayout(new BorderLayout()); // new
		add(mainPanel, BorderLayout.CENTER); // edited

		add(cPanel, BorderLayout.SOUTH); // new
		add(mPanel, BorderLayout.NORTH);

		this.pack();
		this.setVisible(true);
	}

	public static void main(String[] args) {
		new FishApp();
	}

}
