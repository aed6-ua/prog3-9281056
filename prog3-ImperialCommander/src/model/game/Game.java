package model.game;

import model.Side;
import model.exceptions.InvalidSizeException;

import java.util.Objects;

import static model.Side.IMPERIAL;
import static model.Side.REBEL;

public class Game {
    /**
     * Game Board
     */
    private GameBoard board;
    /**
     * Rebel player
     */
    private IPlayer rebel;
    /**
     * Imperial player
     */
    private IPlayer imperial;
    /**
     * Board size
     */
    private final int BOARD_SIZE = 10;

    /**
     * Costructor of the Game
     * @param imperial player
     * @param rebel player
     */
    public Game(IPlayer imperial, IPlayer rebel){
        Objects.requireNonNull(imperial);
        Objects.requireNonNull(rebel);
        this.imperial = imperial;
        this.rebel = rebel;
        try {
            this.board = new GameBoard(BOARD_SIZE);
            this.imperial.setBoard(this.board);
            this.rebel.setBoard(this.board);
        } catch (InvalidSizeException e) {
            throw new RuntimeException();
        }
    }

    /**
     * Gets the game board (shallow copy). For test purposes only.
     * @return the game board
     */
    public GameBoard getGameBoard() {
        return this.board;
    }

    /**
     * Game loop.
     * @return
     */
    public Side play() {
        imperial.initFighters();
        rebel.initFighters();
        Side winner;
        while (true) {
            System.out.print("BEFORE IMPERIAL\n");
            System.out.print(this.board.toString());
            System.out.print(this.imperial.showShip());
            System.out.print(this.rebel.showShip());
            System.out.print("\nIMPERIAL("+this.board.numFighters(IMPERIAL)+"): ");
            if (!this.imperial.nextPlay()) {
                winner = REBEL;
                break;
            }
            System.out.print("AFTER IMPERIAL, BEFORE REBEL\n");
            System.out.print(this.board.toString());
            System.out.print(this.imperial.showShip());
            System.out.print(this.rebel.showShip());
            if (this.imperial.isFleetDestroyed()) {
                winner = REBEL;
                break;
            }
            if (this.rebel.isFleetDestroyed()) {
                winner = IMPERIAL;
                break;
            }
            System.out.print("REBEL("+this.board.numFighters(REBEL)+"): \n");
            if (!this.rebel.nextPlay()) {
                winner = IMPERIAL;
                break;
            }
            System.out.print("AFTER REBEL\n");
            System.out.print(this.board.toString());
            System.out.print(this.imperial.showShip());
            System.out.print(this.rebel.showShip());
            this.imperial.purgeFleet();
            this.rebel.purgeFleet();
            if (this.imperial.isFleetDestroyed()) {
                winner = REBEL;
                break;
            }
            if (this.rebel.isFleetDestroyed()) {
                winner = IMPERIAL;
                break;
            }
        }
        this.imperial.purgeFleet();
        this.rebel.purgeFleet();
        return winner;
    }
}
