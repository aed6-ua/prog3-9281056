package model.game;

import model.Coordinate;
import model.RandomNumber;
import model.Side;
import model.exceptions.FighterAlreadyInBoardException;
import model.exceptions.OutOfBoundsException;
import model.game.exceptions.WrongFighterIdException;
import model.game.score.DestroyedFightersScore;
import model.game.score.WinsScore;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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
        Objects.requireNonNull(side);
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
        Objects.requireNonNull(gb);
        this.board = gb;
    }

    /**
     * Gets player GameShip (shallow copy). For testing purposes only.
     *
     * @return player GameShip.
     */
    @Override
    public GameShip getGameShip() {
        return this.ship;
    }

    /**
     * Gets a string similar to "7/XWing:4/AWing" and calls addFighters method from player's GameShip.
     */
    @Override
    public void initFighters() {
        List<String> imperial = Arrays.asList("TIEFighter","TIEBomber","TIEInterceptor");
        List<String> rebel = Arrays.asList("XWing","YWing","AWing");
        StringBuilder sb = null;
        if (this.ship.getSide().equals(Side.IMPERIAL)) {
            sb = initFightersStringBuilder(imperial);
        }
        if (this.ship.getSide().equals(Side.REBEL)) {
            sb = initFightersStringBuilder(rebel);
        }
        String fighters = sb.toString();
        if (!fighters.isEmpty())
        this.ship.addFighters(fighters);
    }

    /**
     * Method to build the string for initFighters using a list of possible types of fighters.
     * For internal use of initFighters
     * @param list list of possible fighters
     * @return string builder with the resulting string
     */
    private StringBuilder initFightersStringBuilder(List<String> list) {
        StringBuilder sb = new StringBuilder();
        for (String s:
             list) {
            int n = RandomNumber.newRandomNumber(this.numFighters);
            if (n != 0) {
                sb.append(n);
                sb.append("/");
                sb.append(s);
                sb.append(":");
            }
        }
        if (sb.length()>0)
        sb.setLength(sb.length() - 1);
        return sb;
    }

    /**
     * Calls isFleetDestroyed method from player's GameShip.
     *
     * @return value returned by player's GameShip isFleetDestroyed method.
     */
    @Override
    public boolean isFleetDestroyed() {
        return this.ship.isFleetDestroyed();
    }

    /**
     * Union of ships toString and showFleet.
     *
     * @return String formed by toString method of the ship and showFleet returned string after a newline.
     */
    @Override
    public String showShip() {
        String s;
        s = this.ship.toString();
        if (!this.ship.showFleet().isEmpty()) s += "\n"+this.ship.showFleet();
        return s;
    }

    /**
     * Calls purgeFleet method of player's GameShip
     */
    @Override
    public void purgeFleet() { this.ship.purgeFleet();}

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

        int option = RandomNumber.newRandomNumber(100);
        if (option == 99) {
            return false;
        }
        else {
            List<Integer> idList = new ArrayList<>();
            if (option>=85 && option<=98) {
                idList = this.ship.getFightersId("");
                if (idList.isEmpty()) {
                    System.out.print("ERROR:PlayerRandom "+this.ship.getSide()+" Ship:no fighters in board or ship\n");
                    return true;
                }
                int n = RandomNumber.newRandomNumber(idList.size());
                try {
                    this.ship.improveFighter(idList.get(n),option,this.board);
                } catch (WrongFighterIdException e) {
                    throw new RuntimeException();
                }
            }
            else if (option>=25 && option<85) {
                idList = this.ship.getFightersId("ship");
                if (idList.isEmpty()) {
                    System.out.print("ERROR:PlayerRandom "+this.ship.getSide()+" Ship:no fighters in ship\n");
                    return true;
                }
                int n = RandomNumber.newRandomNumber(idList.size());
                int x = RandomNumber.newRandomNumber(this.board.getSize());
                int y = RandomNumber.newRandomNumber(this.board.getSize());
                Coordinate c = new Coordinate(x,y);
                try {
                    this.ship.launch(idList.get(n),c,this.board);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            else if (option>=0 && option<=24) {
                idList = this.ship.getFightersId("board");
                if (idList.isEmpty()) {
                    System.out.print("ERROR:PlayerRandom "+this.ship.getSide()+" Ship:no fighters in board\n");
                    return true;
                }
                int n = RandomNumber.newRandomNumber(idList.size());
                try {
                    this.ship.patrol(idList.get(n),this.board);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return true;
    }

    /**
     * Gets the wins score
     *
     * @return
     */
    @Override
    public WinsScore getWinsScore() {
        return this.ship.getWinsScore();
    }

    /**
     * Gets the destroyed fighters score
     *
     * @return
     */
    @Override
    public DestroyedFightersScore getDestroyedFightersScore() {
        return this.ship.getDestroyedFightersScore();
    }
}
