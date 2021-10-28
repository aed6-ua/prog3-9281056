package model;

public class AWing extends Fighter {

    public AWing(Ship mother) {
        super(mother);
        this.addVelocity(40);
        this.addAttack(5);
        this.addShield(-50);
    }

    @Override
    public Fighter copy() {
        return new AWing(this.getMotherShip());
    }

    @Override
    public char getSymbol() {
        return 'A';
    }

    @Override
    public int getDamage(int n, Fighter enemy) {
        if (enemy.getType().equals("TIEBomber"))
            return super.getDamage(n,enemy)*2;
        else
            return super.getDamage(n,enemy);
    }


}
