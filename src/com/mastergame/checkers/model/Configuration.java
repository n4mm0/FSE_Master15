package com.mastergame.checkers.model;

public interface Configuration 
{
	/**
	 * Yields the label on the tile at the given coordinates
	 * 
	 * @param x the horizontal coordinate, between 0 and BoardSize
	 * @param y the vertical coordinate, between 0 and BoardSize
	 * @return the label... 
	 * 		0 means that the position is empty
	 * 		-1 means there's a black piece
	 * 		1 means there's a white piece
	 */

	CheckersPiece at(int x, int y);

	PieceColor getCurrentPlayer();
	void nextPlayer();
	boolean canPieceCapture(int x, int y);
	int PieceCount(PieceColor color);
	
	/**
	 * Yields a new configuration where a tile has been swapped
	 * with another tile. This configuration remains unchanged
	 * 
	 * @param fromX the horizontal coordinate of the first tile, between 0 and BoardSize
	 * @param fromY the vertical coordinate of the first tile, between 0 and BoardSize
	 * @param intoX the horizontal coordinate of the second tile, between 0 and BoardSize
	 * @param intoY the vertical coordinate of the second tile, between 0 and BoardSize
	 * @return the resulting configuration
	 */

	Configuration swap(int fromX, int fromY, int intoX, int intoY);
}
