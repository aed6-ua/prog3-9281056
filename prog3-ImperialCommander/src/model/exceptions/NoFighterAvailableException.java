package model.exceptions;


/**
 * Excepetion for whe there is no fighter available.
 * @author Eduard Andrei Duta | NIE: X9281056G
 */
public class NoFighterAvailableException extends Exception{
    /**
     * Type of fighter that generated the exception
     */
    private String type;

    /**
     * Constructor for the exception
     * @param type of fighter that generated the exception (can be null if no fighter at all is available)
     */
    public NoFighterAvailableException(String type){
        super();
        this.type = type;
    }

    /**
     * Message for the exception
     * @return a message with exception info
     */
    @Override
    public java.lang.String getMessage() {
        return "ERROR: no fighter of type "+type+" available";
    }
}
