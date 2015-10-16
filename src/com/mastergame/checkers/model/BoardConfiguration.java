package com.mastergame.checkers.model;

import com.mastergame.checkers.Constants;

public class BoardConfiguration extends AbstractConfiguration 
{
	private final CheckersPiece[][] tiles;
	private PieceColor currentPlayer = PieceColor.White; //1: WHITE -1: BLACK

	private BoardConfiguration(BoardConfiguration other) {
		this.currentPlayer = other.currentPlayer;
		this.tiles = new CheckersPiece[Constants.boardSize][];
		for (int y = 0; y < Constants.boardSize; y++)
			this.tiles[y] = other.tiles[y].clone();
	}

	public BoardConfiguration() {
		this.tiles = new CheckersPiece[Constants.boardSize][Constants.boardSize];
		
		for (int y = 0; y < Constants.boardSize / 2 - 1; y++)
		{
			for (int x = 0; x < Constants.boardSize; x++)
			{
				if (y % 2 == 0)
				{
					if (x % 2 != 0)
					{
						set(x, y, new CheckersPiece(PieceColor.White, new PawnMovement()));
					}
				}
				else 
				{
					if (x % 2 == 0)
					{
						set(x, y, new CheckersPiece(PieceColor.White, new PawnMovement()));
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
						set(x ,y, new CheckersPiece(PieceColor.Black, new PawnMovement()));
					}
				}
				else 
				{
					if (x % 2 == 0)
					{
						set(x ,y, new CheckersPiece(PieceColor.Black, new PawnMovement()));
					}
				}
			}
		}
	}

	@Override
	public CheckersPiece at(int x, int y) {
		return tiles[y][x];
	}

	private void set(int x, int y, CheckersPiece piece) {
		tiles[y][x] = piece;
	}

	@Override
	public Configuration swap(int fromX, int fromY, int intoX, int intoY) {
		BoardConfiguration result = new BoardConfiguration(this);

		CheckersPiece intoPiece = at(intoX, intoY);
		CheckersPiece fromPiece = at(fromX, fromY);
		result.set(intoX, intoY, fromPiece);
		result.set(fromX, fromY, intoPiece);
		
		int distX = Math.abs(intoX - fromX);
		int distY = Math.abs(intoY - fromY);
		int directionX = Integer.signum(intoX - fromX);
		int directionY = Integer.signum(intoY - fromY);
		
		//If we captured a piece
		if (distX == 2 && distY == 2)
		{
			//result.set(fromX + directionX, fromY + directionY, new CheckersPiece(PieceColor.Blank, null));
			result.set(fromX + directionX, fromY + directionY, null);
		}
		
		if ((currentPlayer == PieceColor.White && intoY == Constants.boardSize - 1) || (currentPlayer == PieceColor.Black && intoY == 0))
		{
			fromPiece.becomeDame();
		}
		
		return result;
	}

	@Override
	public PieceColor getCurrentPlayer() 
	{
		return currentPlayer;
	}

	@Override
	public void nextPlayer() 
	{
		if (currentPlayer == PieceColor.White)
			currentPlayer = PieceColor.Black;
		else currentPlayer = PieceColor.White;
	}

	@Override
	public boolean canPieceCapture(int x, int y) 
	{
		if ((x - 2 >= 0 && y + currentPlayer.toInt()*2 >= 0 && y + currentPlayer.toInt()*2 < Constants.boardSize)
				&& at(x - 1, y + currentPlayer.toInt()) != null
				&& at(x - 1, y + currentPlayer.toInt()).getColor() != currentPlayer
				&& at(x - 2, y + currentPlayer.toInt()*2) == null)
				return true;
			if ((x + 2 < Constants.boardSize && y + currentPlayer.toInt()*2 >= 0 && y + currentPlayer.toInt()*2 < Constants.boardSize)
				&& at(x + 1, y + currentPlayer.toInt()) != null
				&& at(x + 1, y + currentPlayer.toInt()).getColor() != currentPlayer
				&& at(x + 2, y + currentPlayer.toInt()*2) == null)
				return true;
			return false;
	}
}
