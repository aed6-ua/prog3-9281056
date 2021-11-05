package model.fighters;

import model.Fighter;
import model.Ship;

/**
 * TIEBomber type of fighter.
 * @author Eduard Andrei Duta | NIE: X9281056G
 */
public class TIEBomber extends Fighter {
    /**
     * Constructor for the TIEBomber fighter.
     * @param mother mothership to which the fighter will be assigned.
     */
    public TIEBomber(Ship mother) {
        super(mother);
        this.addVelocity(-30);
        this.addAttack(-50);
        this.addShield(35);
    }

    /**
     * Copy constructor.
     * @param tb TIEBomber to be copied
     */
    private TIEBomber(TIEBomber tb) {
        super(tb);
    }

    /**
     * Creates a copy of the specified TIEBomber.
     * @return a copy of the specified TIEBomber.
     */
    @Override
    public Fighter copy() {
        return new TIEBomber(this);
    }

    /**
     * Gets the symbol for the specified fighter.
     * @return the character symbol for the specified fighter.
     */
    @Override
    public char getSymbol() {
        return 'b';
    }
    /**
     * Gets the damage dealt by the fighter to an enemy fighter.
     * @param n random number
     * @param enemy to fight
     * @return damage dealt
     */
    @Override
    public int getDamage(int n, Fighter enemy) {
        if (enemy.getType().equals("XWing") || enemy.getType().equals("YWing"))
            return super.getDamage(n,enemy)/2;
        if (enemy.getType().equals("AWing"))
            return super.getDamage(n,enemy)/3;
        return super.getDamage(n,enemy);
    }
}
