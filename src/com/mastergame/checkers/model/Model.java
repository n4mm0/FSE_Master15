package com.mastergame.checkers.model;

import com.mastergame.checkers.view.View;

public interface Model 
{
	public int at(int x, int y);
	public Configuration getConfiguration();
	
	public void setConfiguration(Configuration configuration);
	public void setView(View listener);
}
