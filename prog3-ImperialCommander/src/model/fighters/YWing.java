package model.fighters;

import model.Fighter;
import model.Ship;
/**
 * YWing type of fighter.
 * @author Eduard Andrei Duta | NIE: X9281056G
 */
public class YWing extends Fighter {
    /**
     * Constructor for the YWing fighter.
     * @param mother mothership to which the fighter will be assigned.
     */
    public YWing(Ship mother) {
        super(mother);
        this.addVelocity(-20);
        this.addAttack(-10);
        this.addShield(30);
    }
    /**
     * Copy constructor.
     * @param y TIEInterceptor to be copied
     */
    private YWing(YWing y) {
        super(y);
    }
    /**
     * Creates a copy of the specified TIEInterceptor.
     * @return a copy of the specified TIEInterceptor.
     */
    @Override
    public Fighter copy() {
        return new YWing(this);
    }
    /**
     * Gets the symbol for the specified fighter.
     * @return the character symbol for the specified fighter.
     */
    @Override
    public char getSymbol() {
        return 'Y';
    }
    /**
     * Gets the damage dealt by the fighter to an enemy fighter.
     * @param n random number
     * @param enemy to fight
     * @return damage dealt
     */
    @Override
    public int getDamage(int n, Fighter enemy) {
        if (enemy.getType().equals("TIEFighter") || enemy.getType().equals("TIEInterceptor"))
            return super.getDamage(n,enemy)/3;
        if (enemy.getType().equals("TIEBomber"))
            return super.getDamage(n,enemy)/2;
        return super.getDamage(n,enemy);
    }
}
