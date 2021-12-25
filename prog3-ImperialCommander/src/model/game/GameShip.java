package model.game;

import model.*;
import model.exceptions.FighterAlreadyInBoardException;
import model.exceptions.FighterNotInBoardException;
import model.exceptions.OutOfBoundsException;
import model.game.exceptions.WrongFighterIdException;
import model.game.score.DestroyedFightersScore;
import model.game.score.WinsScore;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Subclass of Ship that manages the ship in a game.
 * @author Eduard Andrei Duta | NIE: X9281056G
 */
public class GameShip extends Ship {

    private WinsScore winsScore;

    private DestroyedFightersScore destroyedFightersScore;

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

    /**
     * Gets the fighter with specified id assigned.
     * @param id for the fighter to search
     * @return fighter with specified id
     * @throws WrongFighterIdException if there is no fighter with specified id or the fighter with that id
     * is destroyed.
     */
    private Fighter getFighter(int id) throws WrongFighterIdException {
        for (Fighter f:
             this.fleet) {
            if (f.getId() == id) {
                if (!f.isDestroyed()) return f;
            }
        }
        throw new WrongFighterIdException(id);
    }

    /**
     * Gets a list of fighters from the ship's fleet id that are on the board, inside the ship or everywhere.
     * @param where location of the fighters whose ids to return, "board" for fighters on the game
     *              board or "ship" for the fighters inside the ship. Anything else for the fighters either on the
     *              game board or inside the ship.
     * @return the list of ids for the fighters that currently are specified in where.
     */
    public List<Integer> getFightersId(String where) {
        List<Integer> ids = new ArrayList<>();
        if (where.equals("board")) {
            for (Fighter f:
                 this.fleet) {
                if (!Objects.isNull(f.getPosition()) && !f.isDestroyed()) ids.add(f.getId());
            }
            return ids;
        }
        if (where.equals("ship")) {

            for (Fighter f: this.fleet) {
                if (Objects.isNull(f.getPosition()) && !f.isDestroyed()) ids.add(f.getId());
            }
            return ids;
        }
        for (Fighter f:
             this.fleet) {
            if (!f.isDestroyed()) ids.add(f.getId());
        }
        return ids;
    }

    /**
     * Launches fighter with the specified id to the specified c coordinate on the board.
     * @param id of the fighter to launch
     * @param c coordinate on the board to launch to
     * @param b board where the fighter will be launched
     * @throws WrongFighterIdException if the fighter's id is wrong
     * @throws OutOfBoundsException if the coordinate is out of boards bounds
     * @throws FighterAlreadyInBoardException if the fighter is already in the board.
     */
    public void launch(int id, Coordinate c, Board b) throws WrongFighterIdException, OutOfBoundsException, FighterAlreadyInBoardException {
        Objects.requireNonNull(c);
        Objects.requireNonNull(b);
        b.launch(c,this.getFighter(id));
    }

    /**
     * Makes fighter with specified id patrol
     * @param id for the fighter who is going to patrol
     * @param b board where the fighter is going to patrol
     * @throws WrongFighterIdException if the fighter's id is wrong
     * @throws FighterNotInBoardException if the fighter is not on the board.
     */
    public void patrol(int id, Board b) throws WrongFighterIdException, FighterNotInBoardException {
        Objects.requireNonNull(b);
        b.patrol(this.getFighter(id));
    }

    /**
     * Improves the attack and shield of the specified fighter by half the specified quantity. If
     * the fighter is on the board first it removes it from the board. If the quantity is odd then it
     * will improve the attack by the lower integer rounding of the resulting number from
     * halfing the quantity and the shield by the higher rounding (if the quantity is 67 it will add
     * 33 to the attack and 34 to the shield).
     * @param id of the fighter to improve
     * @param qty quantity to improve (half to the attack and half to the shield)
     * @param b board of the game
     * @throws WrongFighterIdException if the specified id is wrong
     */
    public void improveFighter(int id, int qty, Board b) throws WrongFighterIdException {
        Objects.requireNonNull(b);
        Fighter f = this.getFighter(id);
        try {
            b.removeFighter(f);
        } catch (FighterNotInBoardException e) {}
        if ((qty ^ 1) == qty + 1) {
            f.addAttack(qty/2);
            f.addShield(qty/2);
        }
        else {
            f.addAttack(qty/2);
            f.addShield((qty/2)+1);
        }
    }
}
