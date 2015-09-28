package com.mastergame.checkers.model;

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
	
	public int toInt()
	{
		return color;
	}
}