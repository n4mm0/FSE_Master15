package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.mastergame.checkers.model.BoardConfiguration;
import com.mastergame.checkers.model.CheckersPiece;
import com.mastergame.checkers.model.Configuration;
import com.mastergame.checkers.model.PieceColor;

public class PawnTest 
{
	
	Configuration configuration;

	@Before
	public void createConfiguration()
	{
		configuration = new BoardConfiguration();
	}
	
	//White pawn movement test
	@Test
	public void TestPawnMove1() 
	{
		CheckersPiece piece = configuration.at(3, 2);
		assertTrue(piece.Move(configuration, 3, 2, 4, 3) == 1);
	}
	
	
	//Black pawn illegal movement test
	@Test
	public void TestPawnMove2() 
	{
		CheckersPiece piece = configuration.at(5, 0);
		assertFalse(piece.Move(configuration, 5, 0, 6, 0) == 1);
	}

}
