package model.game;

import junit.framework.TestCase;
import model.*;
import model.exceptions.FighterAlreadyInBoardException;
import model.exceptions.NoFighterAvailableException;
import model.exceptions.OutOfBoundsException;
import model.fighters.TIEBomber;
import model.game.exceptions.WrongFighterIdException;
import org.junit.Before;

import java.util.ArrayList;
import java.util.List;

public class GameShipPreTestDudu extends TestCase {

    GameBoard board;
    GameShip rebelShip, imperialShip;


    @Before
    public void setUp() throws Exception {
        board = new GameBoard(10);
        rebelShip = new GameShip("Alderaan", Side.REBEL);
        imperialShip = new GameShip("Lanzadera T-4a", Side.IMPERIAL);
        RandomNumber.resetRandomCounter();
        rebelShip.addFighters("1/XWing:1/AWing:1/YWing");
        imperialShip.addFighters("1/TIEFighter:1/TIEBomber");
        board.launch(new Coordinate(1,1), rebelShip.getFirstAvailableFighter(""));
        board.launch(new Coordinate(9,0), rebelShip.getFirstAvailableFighter(""));
        board.launch(new Coordinate(5,3), rebelShip.getFirstAvailableFighter(""));
    }

    public void testIsFleetDestroyed() throws NoFighterAvailableException {
        imperialShip.getFirstAvailableFighter("").addShield(-1000);
        imperialShip.getFirstAvailableFighter("").addShield(-1000);
        assertTrue(imperialShip.isFleetDestroyed());
        assertEquals(false,rebelShip.isFleetDestroyed());

    }

    public void testGetFightersId() throws NoFighterAvailableException {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        assertEquals(list,rebelShip.getFightersId("board"));
        List<Integer> list2 = new ArrayList<>();
        list2.add(5);
        imperialShip.getFirstAvailableFighter("").addShield(-1000);
        assertEquals(list2,imperialShip.getFightersId("ship"));
    }

    public void testLaunch() throws OutOfBoundsException, FighterAlreadyInBoardException, WrongFighterIdException, NoFighterAvailableException {
        Fighter f = imperialShip.getFirstAvailableFighter("TIEBomber");
        Coordinate c = new Coordinate(5,5);
        imperialShip.launch(5,c,board);
        assertEquals(f,board.getFighter(c));
    }

    public void testPatrol() {
    }

    public void testImproveFighter() throws NoFighterAvailableException, OutOfBoundsException, FighterAlreadyInBoardException, WrongFighterIdException {
        Fighter f = imperialShip.getFirstAvailableFighter("TIEBomber");
        Coordinate c = new Coordinate(5,5);
        imperialShip.launch(5,c,board);
        imperialShip.improveFighter(5,67,board);
        f.addAttack(33);
        f.addShield(34);
        assertEquals(f,imperialShip.getFirstAvailableFighter("TIEBomber"));
    }
}