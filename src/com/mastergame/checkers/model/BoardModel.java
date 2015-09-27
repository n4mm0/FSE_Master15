package com.mastergame.checkers.model;

import com.mastergame.checkers.Constants;
import com.mastergame.checkers.view.View;

public class BoardModel implements Model 
{
	private Configuration configuration;
	private ConfigurationChangeListener listener;
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
	public void nextTurn()
	{
		configuration.nextPlayer();
		for (int j = 0; j < Constants.boardSize; j++)
		{
			for (int i = 0; i < Constants.boardSize; i++)
			{
				if (configuration.at(i, j) != null && configuration.at(i, j).getColor() == configuration.getCurrentPlayer() 
					//&& Rules.canPieceCapture(configuration, i, j, configuration.at(i, j).getColor()))
					&& configuration.canPieceCapture(i, j))
				{
					mustCapture = true;
					return;
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
}
