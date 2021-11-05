package model.exceptions;

import model.Fighter;
/**
 * Exception for whe the fighter is not on the board.
 * @author Eduard Andrei Duta | NIE: X9281056G
 */
public class FighterNotInBoardException extends Exception{
    /**
     * Fighter that generated the exception
     */
    private Fighter f;

    /**
     * Constructor for the exception
     * @param f fighter that generated the exception
     */
    public FighterNotInBoardException(Fighter f){
        super();
        this.f = f;
    }

    /**
     * Message for the exception
     * @return a message with exception info
     */
    @Override
    public String getMessage() {
        return "ERROR: fighter"+f+" is not in board";
    }
}
