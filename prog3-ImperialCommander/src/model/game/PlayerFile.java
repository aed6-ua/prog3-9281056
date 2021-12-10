package model.game;

import model.Coordinate;
import model.Side;
import model.exceptions.FighterAlreadyInBoardException;
import model.exceptions.FighterNotInBoardException;
import model.exceptions.NoFighterAvailableException;
import model.exceptions.OutOfBoundsException;
import model.game.exceptions.WrongFighterIdException;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Objects;

public class PlayerFile implements IPlayer {

    private BufferedReader br;

    /**
     * Player's GameBoard.
     */
    private GameBoard board;

    /**
     * Player's GameShip.
     */
    private GameShip ship;

    /**
     * Constructor for PlayerFile. Creates a GameShip named PlayerFile "SIDE" Ship tih specified side,
     * and assigns specified BufferedReder to the class BufferedReader
     * @param side of the GameShip
     * @param br BufferedReader to assign to the class
     */
    PlayerFile(Side side, BufferedReader br) {
        Objects.requireNonNull(side);
        Objects.requireNonNull(br);
        this.ship = new GameShip("PlayerFile "+side.toString()+" Ship", side);
        this.br = br;
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
     * Reads the string from the BufferedReader.
     */
    @Override
    public void initFighters() {
        try {
            String s = new String(this.br.readLine());
            this.ship.addFighters(s);
        } catch (IOException e) {
            throw new RuntimeException();
        }

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
        return this.ship.toString()+"\n"+this.ship.showFleet();
    }

    /**
     * Calls purgeFleet method of player's GameShip
     */
    @Override
    public void purgeFleet() {
        this.ship.purgeFleet();
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
        try {
            String line = new String(this.br.readLine());
            String[] s = line.split(" ");
            switch (s[0]) {
                case "exit":
                    return false;
                case "improve":
                    if (s.length != 3) {
                        System.err.println("ERROR: wrong line syntax");
                    }
                    else {
                        int id = Integer.parseInt(s[1]);
                        int qty = Integer.parseInt(s[2]);
                        if (qty >= 100) {
                            System.err.println("ERROR: wrong quantity");
                            return true;
                        }
                        try {
                            this.ship.improveFighter(id, qty, this.board);
                        } catch (WrongFighterIdException e) {
                            System.err.println("ERROR: error with the fighter: ");
                            e.printStackTrace();
                        }
                    }
                    return true;
                case "patrol":
                    if (s.length != 2) {
                        System.err.println("ERROR: wrong line syntax");
                    }
                    else {
                        int id = Integer.parseInt(s[1]);
                        try {
                            this.ship.patrol(id, this.board);
                        }
                        catch (FighterNotInBoardException | WrongFighterIdException e) {
                            System.err.println("ERROR: error with the fighter: ");
                            e.printStackTrace();
                        }
                    }
                    return true;
                case "launch":
                    switch (s.length) {
                        case 3:
                            int x = Integer.parseInt(s[1]);
                            int y = Integer.parseInt(s[2]);
                            Coordinate c = new Coordinate(x,y);
                            try {
                                this.ship.launch(this.ship.getFirstAvailableFighter("").getId(),c,this.board);
                            } catch (WrongFighterIdException | NoFighterAvailableException | OutOfBoundsException | FighterAlreadyInBoardException e) {
                                System.err.println("ERROR: error with the fighter: ");
                                e.printStackTrace();
                            }
                            return true;
                        case 4:
                            int a = Integer.parseInt(s[1]);
                            int b = Integer.parseInt(s[2]);
                            Coordinate d = new Coordinate(a, b);
                            try {
                                int id = Integer.parseInt(s[3]);
                                try {
                                    this.ship.launch(id,d,this.board);
                                } catch (WrongFighterIdException | OutOfBoundsException | FighterAlreadyInBoardException e) {
                                    System.err.println("ERROR: error with the launch: ");
                                    e.printStackTrace();
                                }
                            }
                            catch (NumberFormatException e) {
                                try {
                                    this.ship.launch(this.ship.getFirstAvailableFighter(s[3]).getId(), d, this.board);
                                } catch (WrongFighterIdException | NoFighterAvailableException | OutOfBoundsException | FighterAlreadyInBoardException ex) {
                                    System.err.println("ERROR: error with the launch: ");
                                    ex.printStackTrace();
                                }
                            }
                            return true;
                        default:
                            System.err.println("ERROR: wrong launch line syntax");
                    }
                    return true;

            }
        } catch (IOException e) {
            throw new RuntimeException();
        }
        return true;
    }

}
