package model.game;

import model.game.score.DestroyedFightersScore;
import model.game.score.WinsScore;

/**
 * Interface for the players. Contains methods needed for playing the game.
 * @author Eduard Andrei Duta | NIE: X9281056G
 */
public interface IPlayer {
    /**
     * Assigns specified GameBoard to player attribute board.
     * @param gb GameBoard to assign to the player.
     */
    public void setBoard(GameBoard gb);

    /**
     * Gets player GameShip (shallow copy). For testing purposes only.
     * @return player GameShip.
     */
    public GameShip getGameShip();

    /**
     * Gets a string similar to "7/XWing:4/AWing" and calls addFighters method from player's GameShip.
     */
    public void initFighters();

    /**
     * Calls isFleetDestroyed method from player's GameShip.
     * @return value returned by player's GameShip isFleetDestroyed method.
     */
    public boolean isFleetDestroyed();

    /**
     * Union of ships toString and showFleet.
     * @return String formed by toString method of the ship and showFleet returned string after a newline.
     */
    public String showShip();

    /**
     * Calls purgeFleet method of player's GameShip
     */
    public void purgeFleet();

    /**
     * Does the player's next move from the following:
     * <p>-launch: launches a fighter to the board</p>
     * <p>-patrol: makes a fighter on the board patrol</p>
     * <p>-improve: improves a fighter</p>
     * <p>-exit: exits the game</p>
     * @return true if the player continues the game, false if the player exits.
     */
    public boolean nextPlay();

    /**
     * Gets the wins score
     * @return the WinsScore
     */
    public WinsScore getWinsScore();

    /**
     * Gets the destroyed fighters score
     * @return the DestroyedFightersScore
     */
    public DestroyedFightersScore getDestroyedFightersScore();
}
