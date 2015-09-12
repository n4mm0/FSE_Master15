package com.mastergame.checkers.model;

import com.mastergame.checkers.Constants;
import com.mastergame.checkers.moves.Rules;
import com.mastergame.checkers.view.View;

public class BoardModel implements Model 
{
	private Configuration configuration;
	private ConfigurationChangeListener listener;
	private View view;
	private int currentPlayer = 1; //1: WHITE -1: BLACK
	private boolean mustCapture = false;

	public BoardModel(Configuration configuration) {
		this.configuration = configuration;
	}

	public int at(int x, int y) {
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

	public void setConfigurationChangeListener(BoardModel.ConfigurationChangeListener listener) {
		this.listener = listener;
	}

	public interface ConfigurationChangeListener {
		public void onConfigurationChange();
	}

	@Override
	public void setView(View view) 
	{
		this.view = view;
	}

	@Override
	public int getCurrentPlayer() 
	{
		return currentPlayer;
	}
	
	@Override
	public void nextTurn()
	{
		currentPlayer = -currentPlayer;
		for (int i = 0; i < Constants.boardSize; ++i)
		{
			for (int j = 0; j < Constants.boardSize; ++j)
			{
				if (configuration.at(i, j) != 0 && Rules.canPieceCapture(configuration, i, j))
				{
					mustCapture = true;
					return;
				}
			}
		}
		mustCapture = false;
	}
}
