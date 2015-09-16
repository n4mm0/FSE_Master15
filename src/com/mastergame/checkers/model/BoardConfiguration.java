package com.mastergame.checkers.model;

import com.mastergame.checkers.Constants;

public class BoardConfiguration extends AbstractConfiguration 
{
	private final int[][] tiles;

	private BoardConfiguration(int[][] tiles) {
		this.tiles = new int[Constants.boardSize][];
		for (int y = 0; y < Constants.boardSize; y++)
			this.tiles[y] = tiles[y].clone();
	}

	public BoardConfiguration() {
		this.tiles = new int[Constants.boardSize][Constants.boardSize];
		
		for (int y = 0; y < Constants.boardSize / 2 - 1; y++)
		{
			for (int x = 0; x < Constants.boardSize; x++)
			{
				if (y % 2 == 0)
				{
					if (x % 2 != 0)
					{
						set(x,y,1);
					}
				}
				else 
				{
					if (x % 2 == 0)
					{
						set(x,y,1);
					}
				}
			}
		}
		
		for (int y = Constants.boardSize / 2 + 1; y < Constants.boardSize; y++)
		{
			for (int x = 0; x < Constants.boardSize; x++)
			{
				if (y % 2 == 0)
				{
					if (x % 2 != 0)
					{
						set(x,y,-1);
					}
				}
				else 
				{
					if (x % 2 == 0)
					{
						set(x,y,-1);
					}
				}
			}
		}
	}

	@Override
	public int at(int x, int y) {
		return tiles[y][x];
	}

	private void set(int x, int y, int value) {
		tiles[y][x] = value;
	}

	@Override
	public Configuration swap(int fromX, int fromY, int intoX, int intoY) {
		BoardConfiguration result = new BoardConfiguration(tiles);

		int intoValue = at(intoX, intoY);
		int fromValue = at(fromX, fromY);
		result.set(intoX, intoY, fromValue);
		result.set(fromX, fromY, intoValue);
		
		int distX = Math.abs(intoX - fromX);
		int distY = Math.abs(intoY - fromY);
		int directionX = Integer.signum(intoX - fromX);
		int directionY = Integer.signum(intoY - fromY);
		
		//If we captured a piece
		if (distX == 2 && distY == 2)
		{
			result.set(fromX + directionX, fromY + directionY, 0);
		}
		
		return result;
	}
}
