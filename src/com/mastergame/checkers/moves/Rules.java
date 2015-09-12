package com.mastergame.checkers.moves;

import com.mastergame.checkers.model.Configuration;
import com.mastergame.checkers.model.Model;
import com.mastergame.checkers.Constants;

public final class Rules 
{
	private Model model;
	
	public Rules(Model model)
	{
		this.model = model;
	}
	
	public static Configuration moveAt(Configuration configuration, int fromX, int fromY, int toX, int toY)
	{
		return null;
	}
	
	//Check is the move is legal
	public static boolean isMoveLegal(Model model, int fromX, int fromY, int toX, int toY)
	{
		int distX = Math.abs(toX - fromX);
		int distY = Math.abs(toY - fromY);
		int directionX = Integer.signum(toX - fromX);
		int directionY = Integer.signum(toY - fromY);
		
		if ((fromX>=0 && fromX<Constants.boardSize)
			&& (fromY>=0 && fromY<Constants.boardSize)
			&& (toX>=0 && toX<Constants.boardSize)
			&& (toY>=0 && toY<Constants.boardSize)
			&& directionY == model.getCurrentPlayer())
		{
			if (distX == 1 && distY == 1 && model.at(toX, toY) == 0)
			{
				return true;
			}
			else 
			{
				if (distX == 2 
					&& distY == 2 
					&& model.at(toX, toY) == 0 
					&& model.at(fromX + directionX, fromY + directionY) == -model.getCurrentPlayer())
				{
					return true;
				}
			}
		}
		
		return false;
	}
	
	public static boolean canPieceCapture(Configuration configuration, int x, int y)
	{
		return false;
	}
	
	public static boolean isGameOver(Configuration configuration)
	{
		return false;
	}
}
