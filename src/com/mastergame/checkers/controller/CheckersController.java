package com.mastergame.checkers.controller;

import com.mastergame.checkers.Constants;
import com.mastergame.checkers.model.Model;
import com.mastergame.checkers.moves.Mover;
import com.mastergame.checkers.moves.Rules;
import com.mastergame.checkers.view.View;

public class CheckersController implements Controller 
{
	private final View view;
	private final Model model;
	private final Mover mover;
	
	private boolean pieceSelected = false;
	private int selectedPieceX = -1;
	private int selectedPieceY = -1;

	public CheckersController(View view) 
	{
		this.view = view;
		model = view.getModel();
		mover = new Mover(model);

		view.setController(this);
	}

	@Override
	public void onClick(int x, int y) 
	{
		if (model.at(x, y) != 0) //If the tile is not blank
		{
			if (model.at(x, y) == model.getCurrentPlayer())
			{
				if (!pieceSelected)
				{
					SelectPiece(x, y);
				}
				else
				{
					if (x != selectedPieceX || y != selectedPieceY)
					{
						//view.deselectTile(selectedPieceX, selectedPieceY);
						view.resetTilesColor();
						SelectPiece(x, y);
					}
					else 
					{
						//view.deselectTile(selectedPieceX, selectedPieceY);
						view.resetTilesColor();
						pieceSelected = false;
					}
				}
			}
		}
		else 
		{
			if (pieceSelected)
			{
				if (Rules.isMoveLegal(model, selectedPieceX, selectedPieceY, x, y))
				{
					model.setConfiguration(model.getConfiguration().swap(selectedPieceX, selectedPieceY, x, y));
					view.resetTilesColor();
					model.nextTurn();
				}
			}
			
		}
	}
	
	private void SelectPiece(int x, int y)
	{
		pieceSelected = true;
		selectedPieceX = x;
		selectedPieceY = y;
		view.selectTile(x, y);
		
		//Highlight legal moves
		if (!Rules.canPieceCapture(model.getConfiguration(), x, y))
		{
			for (int j = 0; j < Constants.boardSize; j++)
			{
				for (int i = 0; i < Constants.boardSize; i++)
				{
					if (Rules.isMoveLegal(model, x, y, i, j))
					{
						view.highlightTile(i, j);
					}
				}
			}
		}
		else 
		{
			//To-do
		}
	}
}
