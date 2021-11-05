package model.fighters;


import model.Fighter;
import model.Ship;

/**
 * AWing type of fighter.
 * @author Eduard Andrei Duta | NIE: X9281056G
 */
public class AWing extends Fighter {

    /**
     * Constructor for the AWing type of fighter
     * @param mother mothership to which the fighter will be assigned.
     */
    public AWing(Ship mother) {
        super(mother);
        this.addVelocity(40);
        this.addAttack(5);
        this.addShield(-50);
    }

    /**
     * Copy constructor
     * @param a fighter to copy
     */
    private AWing(AWing a) { super(a); }

    /**
     * Copies an AWing fighter.
     * @return a copy of the AWing fighter.
     */
    @Override
    public Fighter copy() {
        return new AWing(this);
    }

    /**
     * Gets the symbol for the specified fighter.
     * @return the character symbol for the specified fighter.
     */
    @Override
    public char getSymbol() {
        return 'A';
    }

    /**
     * Gets the damage dealt by the fighter to an enemy fighter.
     * @param n random number
     * @param enemy to fight
     * @return damage dealt
     */
    @Override
    public int getDamage(int n, Fighter enemy) {
        if (enemy.getType().equals("TIEBomber"))
            return super.getDamage(n,enemy)*2;
        else
            return super.getDamage(n,enemy);
    }


}
