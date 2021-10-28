package model;

public class TIEBomber extends Fighter{

    public TIEBomber(Ship mother) {
        super(mother);
        this.addVelocity(-30);
        this.addAttack(-50);
        this.addShield(35);
    }

    private TIEBomber(TIEBomber tb) {
        super(tb);
    }

    @Override
    public Fighter copy() {
        return new TIEBomber(this);
    }

    @Override
    public char getSymbol() {
        return 'b';
    }

    @Override
    public int getDamage(int n, Fighter enemy) {
        if (enemy.getType().equals("XWing") || enemy.getType().equals("YWing"))
            return super.getDamage(n,enemy)/2;
        if (enemy.getType().equals("AWing"))
            return super.getDamage(n,enemy)/3;
        return super.getDamage(n,enemy);
    }
}
