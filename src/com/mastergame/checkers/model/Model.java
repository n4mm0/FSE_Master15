package com.mastergame.checkers.model;

import com.mastergame.checkers.view.View;

public interface Model 
{
	public CheckersPiece at(int x, int y);
	public void resetConfiguration();
	public Configuration getConfiguration();
	public boolean mustCapture();
	public void nextTurn();
	
	public void setConfiguration(Configuration configuration);
	public void setView(View listener);
}