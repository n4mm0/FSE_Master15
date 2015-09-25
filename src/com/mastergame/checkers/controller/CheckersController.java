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
	private boolean chain = false;

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
			if (model.at(x, y) == model.getConfiguration().getCurrentPlayer() && !chain)
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
				int moveLegality = Rules.isMoveLegal(model, selectedPieceX, selectedPieceY, x, y);
				if (model.mustCapture())
				{
					if (moveLegality == 2)
					{
						Move(selectedPieceX, selectedPieceY, x, y);
					}
				}
				else
				{
					if (moveLegality == 1)
					{
						Move(selectedPieceX, selectedPieceY, x, y);
					}
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
		for (int j = 0; j < Constants.boardSize; j++)
		{
			for (int i = 0; i < Constants.boardSize; i++)
			{
				if (model.mustCapture())
				{
					if (Rules.isMoveLegal(model, x, y, i, j) == 2)
					{
						view.highlightTile(i, j);
					}
				}
				else 
				{
					if (Rules.isMoveLegal(model, x, y, i, j) == 1)
					{
						view.highlightTile(i, j);
					}
				}
			}
		}
	}
	
	private void Move(int fromX, int fromY, int toX, int toY)
	{
		model.setConfiguration(model.getConfiguration().swap(fromX, fromY, toX, toY));
		//Chain check, true if the piece captured before and can capture again
		if (Rules.canPieceCapture(model.getConfiguration(), toX, toY, model.getConfiguration().getCurrentPlayer()) && model.mustCapture())
		{
			view.resetTilesColor();
			SelectPiece(toX, toY);
			chain = true;
		}
		else 
		{
			chain = false;
			pieceSelected = false;
			view.resetTilesColor();
			model.nextTurn();
		}
	}
}
