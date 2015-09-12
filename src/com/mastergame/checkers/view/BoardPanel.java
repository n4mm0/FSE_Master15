package com.mastergame.checkers.view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.mastergame.checkers.controller.Controller;
import com.mastergame.checkers.model.Model;
import com.sun.xml.internal.bind.WhiteSpaceProcessor;

@SuppressWarnings("unused")
public class BoardPanel extends JPanel implements View 
{
	private final JFrame frame;
	private final Model model;
	private Controller controller;
	private final int size = 10;
	private final JButton[][] buttons = new JButton[size][size];
	private ImageIcon whitePiece;
	private ImageIcon blackPiece;
	private ImageIcon blank;

	public BoardPanel(Model model, JFrame frame) 
	{
		this.frame = frame;
		this.model = model;

		createButtons();

		model.setView(this);
	}

	@Override
	public Model getModel() {
		return model;
	}

	private void createButtons() 
	{
		setLayout(new GridLayout(size, size));
		
		try 
		{
			whitePiece = new ImageIcon(ImageIO.read(new File("assets/white.png")));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		try 
		{
			blackPiece = new ImageIcon(ImageIO.read(new File("assets/black.png")));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		try 
		{
			blank = new ImageIcon(ImageIO.read(new File("assets/blank.png")));
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		for (int y = 0; y < size; y++)
			for (int x = 0; x < size; x++)
				add(buttons[x][y] = mkButton(x, y, model.at(x, y)));
	}

	private JButton mkButton(int x, int y, int value) 
	{
		JButton button = new JButton();
		
		switch (value)
		{
			case 1: button.setIcon(whitePiece);
					break;
			
			case -1: button.setIcon(blackPiece);
					break;
					
			default:button.setIcon(blank);
					break;
		}
		
		button.setBackground(Color.WHITE);
		button.addActionListener(event -> {
			if (controller != null)
				controller.onClick(x, y);
		});

		return button;
	}

	private static final long serialVersionUID = 1L;

	@Override
	public void setController(Controller controller) {
		this.controller = controller;
	}

	@Override
	public void onConfigurationChange() 
	{
		for (int y = 0; y < 4; y++)
		{
			for (int x = 0; x < 4; x++)
			{
				//buttons[x][y].setText(model.at(x, y) == 0 ?
				//	"" : String.valueOf(model.at(x, y)));
			}
		}
	}

	@Override
	public void showSolvedDialog() {
		//new SolvedDialog(frame, controller).setVisible(true);
	}

	@Override
	public void selectTile(int x, int y)
	{
		buttons[x][y].setBackground(Color.CYAN);
	}
	
	@Override
	public void deselectTile(int x, int y)
	{
		buttons[x][y].setBackground(Color.WHITE);
	}
	
	@Override
	public void highlightTile(int x, int y)
	{
		buttons[x][y].setBackground(Color.YELLOW);
	}
	
	@Override
	public void resetBackgroundColor()
	{
		for (int y = 0; y < size; y++)
			for (int x = 0; x < size; x++)
				buttons[x][y].setBackground(Color.WHITE);
	}
}