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
	
	//Returns: 0 = not legal, 1 = movement; 2 = capture
	public static int isMoveLegal(Model model, int fromX, int fromY, int toX, int toY)
	{
		int distX = Math.abs(toX - fromX);
		int distY = Math.abs(toY - fromY);
		int directionX = Integer.signum(toX - fromX);
		int directionY = Integer.signum(toY - fromY);
		
		if ((fromX>=0 && fromX<Constants.boardSize)
			&& (fromY>=0 && fromY<Constants.boardSize)
			&& (toX>=0 && toX<Constants.boardSize)
			&& (toY>=0 && toY<Constants.boardSize)
			&& directionY == model.getConfiguration().getCurrentPlayer().toInt())
		{
			if (distX == 1 && distY == 1 && model.at(toX, toY).getColor().toInt() == 0)
			{
				return 1;
			}
			else 
			{
				if (distX == 2 
					&& distY == 2 
					&& model.at(toX, toY).getColor().toInt() == 0 
					&& model.at(fromX + directionX, fromY + directionY).getColor().toInt() == -model.getConfiguration().getCurrentPlayer().toInt())
				{
					return 2;
				}
			}
		}
		
		return 0;
	}
	
	public static boolean canPieceCapture(Configuration configuration, int x, int y, int currentPlayer)
	{
		if ((x - 2 >= 0 && y + currentPlayer*2 >= 0 && y + currentPlayer*2 < Constants.boardSize)
			&& configuration.at(x - 1, y + currentPlayer).getColor().toInt() == -currentPlayer
			&& configuration.at(x - 2, y + currentPlayer*2).getColor().toInt() == 0)
			return true;
		if ((x + 2 < Constants.boardSize && y + currentPlayer*2 >= 0 && y + currentPlayer*2 < Constants.boardSize)
				&& configuration.at(x + 1, y + currentPlayer).getColor().toInt() == -currentPlayer
				&& configuration.at(x + 2, y + currentPlayer*2).getColor().toInt() == 0)
				return true;
		return false;
	}
	
	public static boolean isGameOver(Configuration configuration)
	{
		return false;
	}
}
