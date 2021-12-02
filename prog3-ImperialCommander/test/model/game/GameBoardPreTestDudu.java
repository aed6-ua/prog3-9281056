package model.game;

import junit.framework.TestCase;
import model.*;
import model.exceptions.FighterAlreadyInBoardException;
import model.exceptions.NoFighterAvailableException;
import model.exceptions.OutOfBoundsException;
import org.junit.Before;

public class GameBoardPreTestDudu extends TestCase {

    GameBoard board;
    Ship rebelShip, imperialShip;

    @Before
    public void setUp() throws Exception {
        board = new GameBoard(10);
        rebelShip = new Ship("Alderaan", Side.REBEL);
        imperialShip = new Ship("Lanzadera T-4a", Side.IMPERIAL);
        RandomNumber.resetRandomCounter();
        rebelShip.addFighters("1/XWing:1/AWing:1/YWing");
        imperialShip.addFighters("1/TIEFighter:1/TIEBomber");
        board.launch(new Coordinate(1,1), rebelShip.getFirstAvailableFighter(""));
        board.launch(new Coordinate(9,0), rebelShip.getFirstAvailableFighter(""));
        board.launch(new Coordinate(5,3), rebelShip.getFirstAvailableFighter(""));
        board.launch(new Coordinate(2,1), imperialShip.getFirstAvailableFighter(""));
        board.launch(new Coordinate(9,9), imperialShip.getFirstAvailableFighter(""));
    }

    public void testNumFighters() throws NoFighterAvailableException, OutOfBoundsException, FighterAlreadyInBoardException {
        assertEquals(2,board.numFighters(Side.IMPERIAL));
        assertEquals(3,board.numFighters(Side.REBEL));
    }

    public void testTestToString() {
        assertEquals("  0123456789\n  ----------\n0|         A\n1| Xf       \n2|          \n" +
                "3|     Y    \n4|          \n5|          \n6|          \n7|          \n" +
                "8|          \n9|         b",board.toString());
    }
}