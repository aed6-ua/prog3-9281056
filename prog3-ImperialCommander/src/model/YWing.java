package model;

public class YWing extends Fighter{

    public YWing(Ship mother) {
        super(mother);
        this.addVelocity(-20);
        this.addAttack(-10);
        this.addShield(30);
    }

    private YWing(YWing y) {
        super(y);
    }

    @Override
    public Fighter copy() {
        return new YWing(this);
    }

    @Override
    public char getSymbol() {
        return 'Y';
    }
}
