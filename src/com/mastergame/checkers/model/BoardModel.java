package com.mastergame.checkers.model;

import com.mastergame.checkers.Constants;
import com.mastergame.checkers.view.View;

public class BoardModel implements Model 
{
	private Configuration configuration;
	private View view;
	private boolean mustCapture = false;

	public BoardModel(Configuration configuration) {
		this.configuration = configuration;
	}

	public CheckersPiece at(int x, int y) {
		return configuration.at(x, y);
	}

	public Configuration getConfiguration() {
		return configuration;
	}

	public void setConfiguration(Configuration configuration) {
		if (this.configuration != configuration) {
			this.configuration = configuration;
			if (view != null)
				view.onConfigurationChange();
		}
	};

	@Override
	public void setView(View view) 
	{
		this.view = view;
	}
	
	@Override
	public void nextTurn()
	{
		//check game over
		if (!HasGameEnded())
		{
			configuration.nextPlayer();
			view.changeCurrentPlayer(configuration.getCurrentPlayer().toInt());
			for (int j = 0; j < Constants.boardSize; j++)
			{
				for (int i = 0; i < Constants.boardSize; i++)
				{
					if (configuration.at(i, j) != null && configuration.at(i, j).getColor() == configuration.getCurrentPlayer() 
						&& configuration.canPieceCapture(i, j))
					{
						mustCapture = true;
						view.notifyPlayerHasToCapture();
						view.highlightHasToCapture(i, j);
						return;
					}
				}
			}
		}
		
		mustCapture = false;
	}

	@Override
	public boolean mustCapture() 
	{
		return mustCapture;
	}

	@Override
	public void resetConfiguration() 
	{
		setConfiguration(new BoardConfiguration());
	}
	
	private boolean HasGameEnded()
	{
		if (configuration.PieceCount(PieceColor.White) == 0)
		{
			view.showGameOverDialog(PieceColor.Black.toInt());
			return true;
		}
		else if (configuration.PieceCount(PieceColor.Black) == 0)
		{
			view.showGameOverDialog(PieceColor.White.toInt());
			return true;
		}
			
		return false;
	}
}
