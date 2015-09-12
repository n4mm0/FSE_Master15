package com.mastergame.checkers.model;

public interface Configuration {
	
	
	int getCurrentPlayer();
	
	/**
	 * Yields the label on the tile at the given coordinates
	 * 
	 * @param x the horizontal coordinate, between 0 and 9
	 * @param y the vertical coordinate, between 0 and 9
	 * @return the label... 
	 * 		0 means that the position is empty
	 * 		1 means there's a black piece
	 * 		2 means there's a white piece
	 */

	int at(int x, int y);

	/**
	 * Yields a new configuration where a tile has been swapped
	 * with another tile. This configuration remains unchanged
	 * 
	 * @param fromX the horizontal coordinate of the first tile, between 0 and 9
	 * @param fromY the vertical coordinate of the first tile, between 0 and 9
	 * @param intoX the horizontal coordinate of the second tile, between 0 and 9
	 * @param intoY the vertical coordinate of the second tile, between 0 and 9
	 * @return the resulting configuration
	 */

	Configuration swap(int fromX, int fromY, int intoX, int intoY);
}
