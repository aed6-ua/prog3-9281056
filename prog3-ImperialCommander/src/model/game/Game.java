package model.game;

import model.Side;
import model.exceptions.InvalidSizeException;
import model.game.score.DestroyedFightersScore;
import model.game.score.Ranking;
import model.game.score.WinsScore;

import java.util.Objects;

import static model.Side.IMPERIAL;
import static model.Side.REBEL;
/**
 * Game loop class
 * @author Eduard Andrei Duta | NIE: X9281056G
 */
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
     * Method for printing the rankings
     */
    private void showRankings() {
        Ranking<WinsScore> ws = new Ranking<>();
        Ranking<DestroyedFightersScore> dfs = new Ranking<>();
        ws.addScore(this.imperial.getWinsScore());
        ws.addScore(this.rebel.getWinsScore());
        dfs.addScore(this.imperial.getDestroyedFightersScore());
        dfs.addScore(this.rebel.getDestroyedFightersScore());

        System.out.print("RANKING WINS:  "+ws.toString()+"\n");
        System.out.print("RANKING DESTROYED:  "+dfs.toString()+"\n");
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
            showRankings();
            System.out.print("BEFORE IMPERIAL\n");
            System.out.print(this.board.toString()+"\n");
            System.out.print("\n"+this.imperial.showShip()+"\n");
            System.out.print("\n"+this.rebel.showShip()+"\n");
            System.out.print("\nIMPERIAL("+this.board.numFighters(IMPERIAL)+"): ");
            if (!this.imperial.nextPlay()) {
                winner = REBEL;
                break;
            }
            System.out.print("AFTER IMPERIAL, BEFORE REBEL\n");
            System.out.print(this.board.toString()+"\n");
            System.out.print("\n"+this.imperial.showShip()+"\n");
            System.out.print("\n"+this.rebel.showShip()+"\n");
            if (this.imperial.isFleetDestroyed()) {
                winner = REBEL;
                break;
            }
            if (this.rebel.isFleetDestroyed()) {
                winner = IMPERIAL;
                break;
            }
            System.out.print("\n");
            System.out.print("REBEL("+this.board.numFighters(REBEL)+"): ");
            if (!this.rebel.nextPlay()) {
                winner = IMPERIAL;
                break;
            }
            System.out.print("AFTER REBEL\n");
            System.out.print(this.board.toString()+"\n");
            System.out.print("\n"+this.imperial.showShip()+"\n");
            System.out.print("\n"+this.rebel.showShip()+"\n\n");
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
        showRankings();
        return winner;
    }
}
