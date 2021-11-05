package model.fighters;

import model.Fighter;
import model.Ship;
/**
 * TIEIntercpetor type of fighter.
 * @author Eduard Andrei Duta | NIE: X9281056G
 */
public class TIEInterceptor extends Fighter {
    /**
     * Constructor for the TIEInterceptor fighter.
     * @param mother mothership to which the fighter will be assigned.
     */
    public TIEInterceptor(Ship mother) {
        super(mother);
        this.addVelocity(45);
        this.addAttack(5);
        this.addShield(-20);
    }
    /**
     * Copy constructor.
     * @param ti TIEInterceptor to be copied
     */
    private TIEInterceptor(TIEInterceptor ti) {
        super(ti);
    }
    /**
     * Creates a copy of the specified TIEInterceptor.
     * @return a copy of the specified TIEInterceptor.
     */
    @Override
    public Fighter copy() {
        return new TIEInterceptor(this);
    }
    /**
     * Gets the symbol for the specified fighter.
     * @return the character symbol for the specified fighter.
     */
    @Override
    public char getSymbol() {
        return 'i';
    }
    /**
     * Gets the damage dealt by the fighter to an enemy fighter.
     * @param n random number
     * @param enemy to fight
     * @return damage dealt
     */
    @Override
    public int getDamage(int n, Fighter enemy) {
        if (enemy.getType().equals("YWing"))
            return super.getDamage(n,enemy)*2;
        if (enemy.getType().equals("AWing"))
            return super.getDamage(n,enemy)/2;
        return super.getDamage(n,enemy);
    }
}
