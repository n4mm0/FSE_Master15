package com.mastergame.checkers.model;

public class CheckersPiece 
{
	private PieceColor color;
	
	private PieceMovement movement;
	
	private boolean dame = false;
	
	public CheckersPiece(PieceColor color, PieceMovement movement)
	{
		this.color = color;
		this.movement = movement;
	}
	
	public int Move(Configuration configuration, int fromX, int fromY, int toX, int toY)
	{
		if (movement != null)
		{
			return movement.CheckMove(configuration, fromX, fromY, toX, toY);
		}
		else return 0;
	}
	
	public PieceColor getColor()
	{
		return color;
	}
	
	public boolean isDame()
	{
		return dame;
	}
	
	public void becomeDame()
	{
		dame = true;
	}
}
