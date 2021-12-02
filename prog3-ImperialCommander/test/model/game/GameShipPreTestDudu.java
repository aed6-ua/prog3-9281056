package model.game;

import junit.framework.TestCase;
import model.Coordinate;
import model.RandomNumber;
import model.Ship;
import model.Side;
import model.exceptions.NoFighterAvailableException;
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

    public void testLaunch() {
    }

    public void testPatrol() {
    }

    public void testImproveFighter() {
    }
}