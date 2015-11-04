package com.mastergame.checkers.view;

import com.mastergame.checkers.controller.Controller;
import com.mastergame.checkers.model.Model;

public interface View {
	Model getModel();
	void setController(Controller controller);

	// 3: change your display
	void showSolvedDialog();
	void changeCurrentPlayer(int color);
	void selectTile(int x, int y);
	void highlightTile(int x, int y);
	void resetTilesColor();
	
	// 4: I've changed
	void onConfigurationChange();
}