package com.mastergame.checkers;

import java.awt.EventQueue;
import javax.swing.JFrame;

import com.mastergame.checkers.view.CheckersFrame;

public class Main 
{
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				JFrame frame = new CheckersFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}
}
