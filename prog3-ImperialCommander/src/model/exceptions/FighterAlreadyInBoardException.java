package model.exceptions;

import model.Fighter;
/**
 * Exception for when the fighter is already on the board.
 * @author Eduard Andrei Duta | NIE: X9281056G
 */
public class FighterAlreadyInBoardException extends Exception{
    /**
     * Fighter that generated the exception
     */
    private Fighter f;

    /**
     * Constructor for the exception
     * @param f fighter that generated the exception
     */
    public FighterAlreadyInBoardException(Fighter f) {
        super();
        this.f = f;
    }

    /**
     * Message for the exception
     * @return a message with exception info.
     */
    @Override
    public String getMessage() {
        return "ERROR: fighter "+f+" already in board";
    }
}
