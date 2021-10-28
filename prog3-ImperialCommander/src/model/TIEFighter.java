package model;

public class TIEFighter extends Fighter{

    public TIEFighter(Ship mother) {
        super(mother);
        this.addVelocity(10);
        this.addAttack(5);
        this.addShield(-10);
    }

    private TIEFighter(TIEFighter tf) {
        super(tf);
    }

    @Override
    public Fighter copy() {
        return new TIEFighter(this);
    }

    @Override
    public char getSymbol() {
        return 'f';
    }
}
