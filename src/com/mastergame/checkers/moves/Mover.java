package com.mastergame.checkers.moves;

import com.mastergame.checkers.model.Model;

public class Mover 
{
	private final Model model;
	
	public Mover(Model model)
	{
		this.model = model;
	}
	
	public boolean canPieceCapture(int x, int y)
	{
		return Rules.canPieceCapture(model.getConfiguration(), x, y);
	}
	
	public boolean isMoveLegal(int fromX, int fromY, int toX, int toY)
	{
		return Rules.isMoveLegal(model.getConfiguration(), fromX, fromY, toX, toY);
	}
	
	public boolean isGameOver()
	{
		return Rules.isGameOver(model.getConfiguration());
	}
	
	public void moveAt(int x, int y)
	{
		// Create new configuration and set the new model config after checking if
		// it's legal
	}

}
