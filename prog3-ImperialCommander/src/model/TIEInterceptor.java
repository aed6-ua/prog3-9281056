package model;

public class TIEInterceptor extends Fighter{

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
}
