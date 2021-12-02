package model.game.exceptions;

/**
 * Exception thrown when the fighter id is not correct.
 * @author Eduard Andrei Duta | NIE: X9281056G
 */
public class WrongFighterIdException extends Exception{
    /**
     * Id that generated the exception
     */
    private int id;

    /**
     * Constructor for the exception
     * @param id the id that generated the exception
     */
    public WrongFighterIdException(int id) {
        super();
        this.id = id;
    }

    /**
     * Message for the exception
     * @return message with exception info
     */
    @Override
    public String getMessage() {return "ERROR: id " + this.id + "is wrong";}
}
