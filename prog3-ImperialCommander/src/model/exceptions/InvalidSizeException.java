package model.exceptions;

/**
 * Exception for when the size of the board is not valid.
 * @author Eduard Andrei Duta | NIE: X9281056G
 */
public class InvalidSizeException extends Exception{
    /**
     * Size value that generated the exception
     */
    private int size;

    /**
     * Constructor for the exception
     * @param size that generated the exception
     */
    public InvalidSizeException(int size){
        super();
        this.size = size;
    }

    /**
     * Message for the exception
     * @return a message with exception info
     */
    @Override
    public String getMessage() {
        return "ERROR: "+size+" is an invalid size";
    }
}
