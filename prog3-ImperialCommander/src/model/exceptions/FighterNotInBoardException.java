package model.exceptions;

import model.Fighter;

public class FighterNotInBoardException extends Exception{

    private Fighter f;

    public FighterNotInBoardException(Fighter f){
        super();
        this.f = f;
    }

    @Override
    public String getMessage() {
        return "ERROR: fighter"+f+" is not in board";
    }
}
