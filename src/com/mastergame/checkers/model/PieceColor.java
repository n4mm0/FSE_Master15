package com.mastergame.checkers.model;

import com.mastergame.checkers.Constants;

public enum PieceColor
{
	White(Constants.whiteCode),
	Black(Constants.blackCode),
	Blank(Constants.blankCode);
	
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