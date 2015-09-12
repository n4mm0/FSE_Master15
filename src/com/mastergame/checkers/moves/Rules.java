package com.mastergame.checkers.moves;

import com.mastergame.checkers.model.Configuration;
import com.mastergame.checkers.model.Constants;

public final class Rules 
{	
	//Check is the move is legal
	public static boolean isMoveLegal(Configuration configuration, int fromX, int fromY, int toX, int toY)
	{
		if ((fromX>=0 && fromX<Constants.boardSize)
			&& (fromY>=0 && fromY<Constants.boardSize)
			&& (toX>=0 && toX<Constants.boardSize)
			&& (toY>=0 && toY<Constants.boardSize))
		{
			if (configuration.at(toX, toY) == 0)
			{
				return true;
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
