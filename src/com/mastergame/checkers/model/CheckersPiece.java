package com.mastergame.checkers.model;

public class CheckersPiece 
{
	public enum PieceColor
	{
		White(1),
		Black(-1),
		Blank(0);
		
		private int color;
		
		private PieceColor(int color)
		{
			this.color = color;
		}
	}
	
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
		return movement.Move(configuration, fromX, fromY, toX, toY);
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
