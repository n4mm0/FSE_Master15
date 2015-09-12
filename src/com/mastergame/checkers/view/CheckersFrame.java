package com.mastergame.checkers.view;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.mastergame.checkers.model.BoardConfiguration;
import com.mastergame.checkers.model.BoardModel;
import com.mastergame.checkers.controller.CheckersController;
import com.mastergame.checkers.controller.Controller;

@SuppressWarnings("unused")
public class CheckersFrame extends JFrame {
	private final BoardModel model = new BoardModel(new BoardConfiguration());
	private final Controller controller;

	public CheckersFrame() {
		setTitle("Checkers");

		View view = addTiles();
		controller = new CheckersController(view);
		//controller.randomize();
		addControlButtons();

		setIconImage(new ImageIcon("img/puzzle15.jpg").getImage());

		pack();
	}

	private void addControlButtons() {
		/*JPanel panel = new JPanel();

		JButton randomize = new JButton("Randomize");
		randomize.addActionListener(event -> controller.randomize());
		panel.add(randomize);

		JButton hint = new JButton("Hint");
		hint.addActionListener(event -> controller.giveHint());
		panel.add(hint);

		add(panel, BorderLayout.NORTH);*/
	}

	private View addTiles() {
		BoardPanel panel = new BoardPanel(model, this);
		add(panel, BorderLayout.CENTER);

		return panel;
	}

	private static final long serialVersionUID = 1L;
}