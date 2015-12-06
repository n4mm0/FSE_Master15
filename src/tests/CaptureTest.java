package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.mastergame.checkers.model.BoardConfiguration;
import com.mastergame.checkers.model.CheckersPiece;
import com.mastergame.checkers.model.Configuration;
import com.mastergame.checkers.model.PieceColor;

public class CaptureTest 
{
	Configuration configuration;
	
	@Before 
	public void createConfiguration()
	{
		configuration = new BoardConfiguration();
		
		configuration = configuration.swap(3, 2, 4, 3);
		configuration = configuration.swap(2, 5, 3, 4);
	}
	
	@Test
	public void CaptureTest()
	{
		assertTrue(configuration.at(4, 3).Move(configuration, 4, 3, 2, 5) == 2);
	}

}
