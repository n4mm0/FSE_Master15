package com.mastergame.checkers.view;

import com.mastergame.checkers.controller.Controller;
import com.mastergame.checkers.model.Model;

public interface View {
	Model getModel();
	void setController(Controller controller);

	// 3: change your display
	void showSolvedDialog();
	void selectTile(int x, int y);
	void deselectTile(int x, int y);
	void highlightTile(int x, int y);
	void resetBackgroundColor();
	
	// 4: I've changed
	void onConfigurationChange();
}