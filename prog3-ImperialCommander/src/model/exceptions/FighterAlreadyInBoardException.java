package model.exceptions;

import model.Fighter;

public class FighterAlreadyInBoardException extends Exception{

    private Fighter f;

    public FighterAlreadyInBoardException(Fighter f) {
        super();
        this.f = f;
    }

    @Override
    public String getMessage() {
        return "ERROR: fighter "+f+" already in board";
    }
}
