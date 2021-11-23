package model.exceptions;


import model.Coordinate;

/**
 * Exception for when the value of the coordinate is out of bounds.
 * @author Eduard Andrei Duta | NIE: X9281056G
 */
public class OutOfBoundsException extends Exception{
    /**
     * Coordinate that generated the exception
     */
    private Coordinate c;

    /**
     * Constructor for the exception
     * @param c coordinate that generated the exception
     */
    public OutOfBoundsException(Coordinate c){
        super();
        this.c = c;
    }

    /**
     * Message for the exception
     * @return a message with exception info
     */
    @Override
    public java.lang.String getMessage() {
        return "ERROR: coordinate "+super.getMessage()+c+"out of board";
    }
}
