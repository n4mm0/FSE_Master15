package com.mastergame.checkers.view;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.mastergame.checkers.Constants;
import com.mastergame.checkers.controller.Controller;

public class GameOverDialog extends JDialog
{
	public GameOverDialog(Controller controller, int winner)
	{
		JLabel winnerLabel = new JLabel();
		switch (winner)
		{
			case Constants.whiteCode:
				winnerLabel.setText("White wins!");
				break;
				
			case Constants.blackCode:
				winnerLabel.setText("Black wins!");
				break;
				
			default:break;
		}
		add(winnerLabel);
		
		JButton ok = new JButton("OK!");
		ok.addActionListener(event ->
		{
			controller.resetGame();
			setVisible(false);
		});
		
		JPanel panel = new JPanel();
		panel.add(ok);
		add(panel, BorderLayout.SOUTH);

		setVisible(false);
		
		pack();
	}

	private static final long serialVersionUID = 1L;
}
