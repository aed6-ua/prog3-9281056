package model.fighters;

import model.Fighter;
import model.Ship;

public class TIEInterceptor extends Fighter {

    public TIEInterceptor(Ship mother) {
        super(mother);
        this.addVelocity(45);
        this.addAttack(5);
        this.addShield(-20);
    }

    private TIEInterceptor(TIEInterceptor ti) {
        super(ti);
    }

    @Override
    public Fighter copy() {
        return new TIEInterceptor(this);
    }

    @Override
    public char getSymbol() {
        return 'i';
    }

    @Override
    public int getDamage(int n, Fighter enemy) {
        if (enemy.getType().equals("YWing"))
            return super.getDamage(n,enemy)*2;
        if (enemy.getType().equals("AWing"))
            return super.getDamage(n,enemy)/2;
        return super.getDamage(n,enemy);
    }
}
