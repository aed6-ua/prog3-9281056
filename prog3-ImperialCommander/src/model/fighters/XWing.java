package model.fighters;

import model.Fighter;
import model.Ship;

/**
 * XWing type of fighter.
 * @author Eduard Andrei Duta | NIE: X9281056G
 */
public class XWing extends Fighter {
    /**
     * Constructor for the XWing fighter.
     * @param mother mothership to which the fighter will be assigned.
     */
    public XWing(Ship mother){
        super(mother);
        this.addVelocity(10);
        this.addAttack(20);
    }
    /**
     * Copy constructor.
     * @param x XWing to be copied
     */
    private XWing(XWing x) {
        super(x);
    }
    /**
     * Creates a copy of the specified TIEInterceptor.
     * @return a copy of the specified TIEInterceptor.
     */
    @Override
    public Fighter copy() {
        return new XWing(this);
    }
    /**
     * Gets the symbol for the specified fighter.
     * @return the character symbol for the specified fighter.
     */
    @Override
    public char getSymbol() {
        return 'X';
    }
}
