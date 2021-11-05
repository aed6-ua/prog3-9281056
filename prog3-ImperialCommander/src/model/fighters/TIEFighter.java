package model.fighters;

import model.Fighter;
import model.Ship;
/**
 * TIEFighter type of fighter.
 * @author Eduard Andrei Duta | NIE: X9281056G
 */
public class TIEFighter extends Fighter {
    /**
     * Constructor for the TIEFighter fighter.
     * @param mother mothership to which the fighter will be assigned.
     */
    public TIEFighter(Ship mother) {
        super(mother);
        this.addVelocity(10);
        this.addAttack(5);
        this.addShield(-10);
    }
    /**
     * Copy constructor.
     * @param tf TIEFighter to be copied
     */
    private TIEFighter(TIEFighter tf) {
        super(tf);
    }
    /**
     * Creates a copy of the specified TIEFighter.
     * @return a copy of the specified TIEFighter.
     */
    @Override
    public Fighter copy() {
        return new TIEFighter(this);
    }
    /**
     * Gets the symbol for the specified fighter.
     * @return the character symbol for the specified fighter.
     */
    @Override
    public char getSymbol() {
        return 'f';
    }
}
