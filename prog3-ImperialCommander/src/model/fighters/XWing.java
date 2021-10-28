package model.fighters;

import model.Fighter;
import model.Ship;

/**
 * The type X wing.
 */
public class XWing extends Fighter {

    public XWing(Ship mother){
        super(mother);
        this.addVelocity(10);
        this.addAttack(20);
    }

    private XWing(XWing x) {
        super(x);
    }

    @Override
    public Fighter copy() {
        return new XWing(this);
    }

    @Override
    public char getSymbol() {
        return 'X';
    }
}
