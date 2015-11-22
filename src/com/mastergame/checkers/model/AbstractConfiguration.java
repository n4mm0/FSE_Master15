package com.mastergame.checkers.model;

import com.mastergame.checkers.Constants;

public abstract class AbstractConfiguration implements Configuration {
	
	@Override
	public String toString() {
		String result = "";
		for (int y = 0; y < Constants.boardSize; y++)
		{
			for (int x = 0; x < Constants.boardSize; x++)
			{
				result += String.format("%2d ", at(x,y));
			}
			result += "\n";
		}

		return result;
	}

	@Override
	public boolean equals(Object other) {
		if (other instanceof Configuration) {
			Configuration otherAsConfiguration = (Configuration) other;
			for (int y = 0; y < Constants.boardSize; y++)
				for (int x = 0; x < Constants.boardSize; x++)
					if (at(x, y) != otherAsConfiguration.at(x, y))
						return false;

			return true;
		}
		else
			return false;
	}

}
