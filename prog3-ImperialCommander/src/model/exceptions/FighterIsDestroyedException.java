package model.exceptions;

import model.Fighter;

public class FighterIsDestroyedException extends Exception{

    private Fighter f;

    public FighterIsDestroyedException(Fighter f) {
        super();
        this.f = f;
    }

    @Override
    public String getMessage() {
        return "ERROR: fighter"+f+" is destroyed";
    }
}
