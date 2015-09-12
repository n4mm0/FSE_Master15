package com.mastergame.checkers.controller;

import com.mastergame.checkers.model.Model;
import com.mastergame.checkers.moves.Mover;
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
			if (model.at(x, y) == model.getConfiguration().getCurrentPlayer())
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
						view.resetBackgroundColor();
						SelectPiece(x, y);
					}
					else 
					{
						//view.deselectTile(selectedPieceX, selectedPieceY);
						view.resetBackgroundColor();
						pieceSelected = false;
					}
				}
			}
		}
		else 
		{
			if (pieceSelected)
			{
				
				//se la mossa richiesta è legale allora muovi!
			//new Rules().IsMoveLegal(selectedPieceX, selectedPieceY, x, y)
			
			/*mover.moveAt(x, y);
			if (mover.isSolved())
				view.showSolvedDialog();*/
				
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
		if (!mover.canPieceCapture(x, y))
		{
			for (int offset = -1; offset < 2; offset+=2)
			{
				if (mover.isMoveLegal(x, y, x + offset, y + model.getConfiguration().getCurrentPlayer()))
				{
					view.highlightTile(x + offset, y + model.getConfiguration().getCurrentPlayer());
				}
			}
		}
		else 
		{
			
		}
	}
}
