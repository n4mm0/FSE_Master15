package com.mastergame.checkers.view;

import java.awt.Color;
import java.awt.GridLayout;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import com.mastergame.checkers.Constants;
import com.mastergame.checkers.controller.Controller;
import com.mastergame.checkers.model.CheckersPiece;
import com.mastergame.checkers.model.Model;
import com.mastergame.checkers.model.PieceColor;

public class BoardPanel extends JPanel implements View 
{
	private final JFrame frame;
	private final Model model;
	private Controller controller;
	
	private final JButton[][] buttons = new JButton[Constants.boardSize][Constants.boardSize];
	
	private JLabel playerText;
	
	private ImageIcon whitePiece;
	private ImageIcon whiteDame;
	private ImageIcon blackPiece;
	private ImageIcon blackDame;
	private ImageIcon blank;
	
	private Border standardBorder;
	private Border selectedBorder;
	private Border highlightedBorder;

	public BoardPanel(Model model, JFrame frame, JLabel playerText) 
	{
		this.frame = frame;
		this.model = model;
		this.playerText = playerText;

		createButtons();
		
		standardBorder = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY);
		selectedBorder = BorderFactory.createMatteBorder(4, 4, 4, 4, Color.CYAN);
		highlightedBorder = BorderFactory.createMatteBorder(4, 4, 4, 4, Color.YELLOW);
		
		model.setView(this);
	}

	@Override
	public Model getModel() {
		return model;
	}

	private void createButtons() 
	{
		setLayout(new GridLayout(Constants.boardSize, Constants.boardSize));
		
		try 
		{
			whitePiece = new ImageIcon(ImageIO.read(new File("assets/white.png")));
			whiteDame = new ImageIcon(ImageIO.read(new File("assets/white_dame.png")));
			blackPiece = new ImageIcon(ImageIO.read(new File("assets/black.png")));
			blackDame = new ImageIcon(ImageIO.read(new File("assets/black_dame.png")));
			blank = new ImageIcon(ImageIO.read(new File("assets/blank.png")));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		for (int y = 0; y < Constants.boardSize; y++)
		{
			for (int x = 0; x < Constants.boardSize; x++)
			{
				if (model.at(x, y) != null)
				{
					add(buttons[x][y] = mkButton(x, y, model.at(x, y).getColor()));
				}
				else
				{
					add(buttons[x][y] = mkButton(x, y, PieceColor.Blank));
				}
				
				if (y % 2 != 0 && x % 2 == 0)
				{
					buttons[x][y].setBackground(new Color(116, 82, 68));
				}
				
				if (y % 2 == 0 && x % 2 != 0)
				{
					buttons[x][y].setBackground(new Color(116, 82, 68));
				}
			}
		}
	}

	private JButton mkButton(int x, int y, PieceColor color) 
	{
		JButton button = new JButton();
		
		switch (color)
		{
			case White: button.setIcon(whitePiece);
						break;
			
			case Black: button.setIcon(blackPiece);
					break;
					
			default:button.setIcon(blank);
					break;
		}
		
		button.setBackground(Color.WHITE);
		//button.setBorder(standardBorder);
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
		for (int y = 0; y < Constants.boardSize; y++)
		{
			for (int x = 0; x < Constants.boardSize; x++)
			{
				if (model.at(x, y) != null)
				{
					CheckersPiece piece = model.at(x,y);
					switch (piece.getColor())
					{
						case White: if (piece.isDame())
										buttons[x][y].setIcon(whiteDame);
									else 
										buttons[x][y].setIcon(whitePiece);
									break;
						
						case Black: if (piece.isDame())
										buttons[x][y].setIcon(blackDame);
									else 
										buttons[x][y].setIcon(blackPiece);
									break;
								
						default:break;
					}
				}	
				else 
				{
					buttons[x][y].setIcon(blank);
				}
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
		buttons[x][y].setBorder(selectedBorder);
	}
	
	@Override
	public void highlightTile(int x, int y)
	{
		buttons[x][y].setBorder(highlightedBorder);
	}
	
	@Override
	public void resetTilesColor()
	{
		for (int y = 0; y < Constants.boardSize; y++)
			for (int x = 0; x < Constants.boardSize; x++)
				buttons[x][y].setBorder(standardBorder);
	}

	@Override
	public void changeCurrentPlayer(int color) 
	{
		switch (color)
		{
			case 1: playerText.setText("White");
					playerText.setForeground(Color.GRAY);
					break;
					
			case -1: playerText.setText("Black");
					playerText.setForeground(Color.BLACK);
					break;		
					
			default:break;
		}
	}
}