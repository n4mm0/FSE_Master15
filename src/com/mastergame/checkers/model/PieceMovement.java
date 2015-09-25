package com.mastergame.checkers.model;

public interface PieceMovement 
{
	int Move(Configuration configuration, int fromX, int fromY, int toX, int toY);
}
