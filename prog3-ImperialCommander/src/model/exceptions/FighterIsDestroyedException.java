package model.exceptions;

import model.Fighter;
/**
 * Exception for when the fighter is already destroyed.
 * @author Eduard Andrei Duta | NIE: X9281056G
 */
public class FighterIsDestroyedException extends Exception{
    /**
     * Fighter that generated the exception
     */
    private Fighter f;

    /**
     * Constructor for the exception
     * @param f fighter that generated the exception
     */
    public FighterIsDestroyedException(Fighter f) {
        super();
        this.f = f;
    }

    /**
     * Message for the exception
     * @return message with exception info
     */
    @Override
    public String getMessage() {
        return "ERROR: fighter"+f+" is destroyed";
    }
}
