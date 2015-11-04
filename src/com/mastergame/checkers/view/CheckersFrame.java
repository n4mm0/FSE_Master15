package com.mastergame.checkers.view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.mastergame.checkers.model.BoardConfiguration;
import com.mastergame.checkers.model.BoardModel;
import com.mastergame.checkers.controller.CheckersController;
import com.mastergame.checkers.controller.Controller;

public class CheckersFrame extends JFrame 
{
	private final BoardModel model = new BoardModel(new BoardConfiguration());
	private final Controller controller;
	
	private JLabel player;

	public CheckersFrame() 
	{
		setSize(500,500);
		setTitle("Checkers");

		addInformationPanel();
		View view = addTiles();
		controller = new CheckersController(view);


		setIconImage(new ImageIcon("img/puzzle15.jpg").getImage());
	}

	private void addInformationPanel() 
	{
		JPanel panel = new JPanel();

		JLabel playerLabel = new JLabel("Current Player");
		playerLabel.setForeground(Color.BLUE);
		player = new JLabel("White");
		player.setForeground(Color.GRAY);
		panel.add(playerLabel);
		panel.add(player);
		add(panel, BorderLayout.SOUTH);
	}

	private View addTiles() 
	{
		BoardPanel panel = new BoardPanel(model, this, player);
		add(panel, BorderLayout.CENTER);

		return panel;
	}

	private static final long serialVersionUID = 1L;
}