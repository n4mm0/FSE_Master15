package com.mastergame.checkers.model;

import com.mastergame.checkers.Constants;

public class PawnMovement implements PieceMovement {

	@Override
	public int Move(Configuration configuration, int fromX, int fromY, int toX, int toY) 
	{
		int distX = Math.abs(toX - fromX);
		int distY = Math.abs(toY - fromY);
		int directionX = Integer.signum(toX - fromX);
		int directionY = Integer.signum(toY - fromY);
		
		if ((fromX>=0 && fromX<Constants.boardSize)
			&& (fromY>=0 && fromY<Constants.boardSize)
			&& (toX>=0 && toX<Constants.boardSize)
			&& (toY>=0 && toY<Constants.boardSize)
			&& directionY == configuration.getCurrentPlayer())
		{
			if (distX == 1 && distY == 1 && configuration.at(toX, toY) == 0)
			{
				return 1;
			}
			else 
			{
				if (distX == 2 
					&& distY == 2 
					&& configuration.at(toX, toY) == 0 
					&& configuration.at(fromX + directionX, fromY + directionY) == -configuration.getCurrentPlayer())
				{
					return 2;
				}
			}
		}
		
		return 0;
	}

}
