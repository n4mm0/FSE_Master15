package com.mastergame.checkers.controller;

import com.mastergame.checkers.Constants;
import com.mastergame.checkers.model.Model;
import com.mastergame.checkers.view.View;

public class CheckersController implements Controller 
{
	private final View view;
	private final Model model;
	
	private boolean pieceSelected = false;
	private int selectedPieceX = -1;
	private int selectedPieceY = -1;
	private boolean chain = false;

	public CheckersController(View view) 
	{
		this.view = view;
		model = view.getModel();

		view.setController(this);
	}

	@Override
	public void onClick(int x, int y) 
	{
		if (model.at(x, y) != null) //If the tile is not blank
		{
			if (model.at(x, y).getColor() == model.getConfiguration().getCurrentPlayer() && !chain)
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
				int moveLegality = model.at(selectedPieceX, selectedPieceY).Move(model.getConfiguration(), selectedPieceX, selectedPieceY, x, y);

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
		
		//clamp!
		int xIndex = Math.max(0, Math.min(selectedPieceX - 2, selectedPieceX - 2));
		int xMax = Math.max(selectedPieceX, Math.min(Constants.boardSize, selectedPieceX + 3));
		int yIndex = Math.max(0, Math.min(selectedPieceY - 2, selectedPieceY - 2));
		int yMax = Math.max(selectedPieceY, Math.min(Constants.boardSize, selectedPieceY + 3));

		//Highlight legal moves
		for (int j = yIndex; j < yMax; j++)
		{
			for (int i = xIndex; i < xMax ; i++)
			{
				if (model.mustCapture())
				{
					if (model.at(x, y) != null 
						&& model.at(x, y).Move(model.getConfiguration(), x, y, i, j) == 2)
					{
						view.highlightTile(i, j);
					}
				}
				else 
				{
					if (model.at(x, y) != null 
						&& model.at(x, y).Move(model.getConfiguration(), x, y, i, j) == 1)
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
		//Bug, al momento non funziona l'highlight dopo chain
		if (model.getConfiguration().canPieceCapture(toX, toY) && model.mustCapture())
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
