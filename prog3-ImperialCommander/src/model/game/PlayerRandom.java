package model.game;

import model.RandomNumber;
import model.Side;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class that simulates a player. Makes random plays.
 * @author Eduard Andrei Duta | NIE: X9281056G
 */
public class PlayerRandom implements IPlayer {
    /**
     * Player's ship.
     */
    private GameShip ship;

    /**
     * Player's GameBoard.
     */
    private GameBoard board;

    /**
     * Number of fighters
     */
    private int numFighters;

    /**
     * Constructor for the player's ship depending on the side.
     * @param side for the player's ship
     * @param numFighters sets numFighter attribute.
     */
    public PlayerRandom(Side side, int numFighters) {
        this.ship = new GameShip("PlayerRandom "+side+" Ship",side);
        this.numFighters = numFighters;
    }

    /**
     * Assigns specified GameBoard to player attribute board.
     *
     * @param gb GameBoard to assign to the player.
     */
    @Override
    public void setBoard(GameBoard gb) {

    }

    /**
     * Gets player GameShip (shallow copy). For testing purposes only.
     *
     * @return player GameShip.
     */
    @Override
    public GameShip getGameShip() {
        return null;
    }

    /**
     * Gets a string similar to "7/XWing:4/AWing" and calls addFighters method from player's GameShip.
     */
    @Override
    public void initFighters() {
        List<String> imperial = Arrays.asList("TIEFighter","TIEBomber","TIEInterceptor");
        List<String> rebel = Arrays.asList("XWing","YWing","AWing");
        StringBuilder sb = new StringBuilder();
        if (this.ship.getSide().equals(Side.IMPERIAL)) {
            for (String s:
                 imperial) {
                int n = RandomNumber.newRandomNumber(this.numFighters - 1);
                sb.append(n);
                sb.append("/");
                sb.append(s);
                sb.append(":");
            }
            sb.setLength(sb.length() - 1);
        }
        if (this.ship.getSide().equals(Side.REBEL)) {
            for (String t:
                    rebel) {
                int n = RandomNumber.newRandomNumber(this.numFighters - 1);
                sb.append(n);
                sb.append("/");
                sb.append(t);
                sb.append(":");
            }
            sb.setLength(sb.length() - 1);
        }
        String fighters = sb.toString();
        this.ship.addFighters(fighters);
    }

    /**
     * Calls isFleetDestroyed method from player's GameShip.
     *
     * @return value returned by player's GameShip isFleetDestroyed method.
     */
    @Override
    public boolean isFleetDestroyed() {
        return false;
    }

    /**
     * Union of ships toString and showFleet.
     *
     * @return String formed by toString method of the ship and showFleet returned string after a newline.
     */
    @Override
    public String showShip() {
        return null;
    }

    /**
     * Calls purgeFleet method of player's GameShip
     */
    @Override
    public void purgeFleet() {

    }

    /**
     * Does the player's next move from the following:
     * <p>-launch: launches a fighter to the board</p>
     * <p>-patrol: makes a fighter on the board patrol</p>
     * <p>-improve: improves a fighter</p>
     * <p>-exit: exits the game</p>
     *
     * @return true if the player continues the game, false if the player exits.
     */
    @Override
    public boolean nextPlay() {
        return false;
    }
}
