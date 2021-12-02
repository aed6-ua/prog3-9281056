package model.game;

import model.Fighter;
import model.Ship;
import model.Side;
import model.game.exceptions.WrongFighterIdException;

/**
 * Subclass of Ship that manages the ship in a game.
 * @author Eduard Andrei Duta | NIE: X9281056G
 */
public class GameShip extends Ship {
    /**
     * Constructor of Ship. Creates a ship with the specified name and side with wins and losses
     * initialized at 0 and an empty fleet.
     *
     * @param name of the ship
     * @param side of the ship
     */
    public GameShip(String name, Side side) {
        super(name, side);
    }

    /**
     * Checks if the whole fleet of the ship is destroyed.
     * @return true if the whole ship is destroyed, false if there is at least
     * one fighter not destroyed in the fleet.
     */
    public boolean isFleetDestroyed() {
        for (Fighter f:
             this.fleet) {
            if (!f.isDestroyed()) {
                return false;
            }
        }
        return true;
    }

    private Fighter getFighter(int id) throws WrongFighterIdException {
        for (Fighter f:
             this.fleet) {
            if (f.getId() == id) {
                if (!f.isDestroyed()) return f;
            }
        }
        throw new WrongFighterIdException(id);
    }
}
