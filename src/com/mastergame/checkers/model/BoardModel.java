package com.mastergame.checkers.model;

import com.mastergame.checkers.view.View;

public class BoardModel implements Model 
{
	private Configuration configuration;
	private ConfigurationChangeListener listener;
	@SuppressWarnings("unused")
	private View view;

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
			if (listener != null)
				listener.onConfigurationChange();
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
}
