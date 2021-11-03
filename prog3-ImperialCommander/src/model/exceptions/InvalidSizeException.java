package model.exceptions;

@SuppressWarnings("serial")
public class InvalidSizeException extends Exception{

    private int size;

    public InvalidSizeException(int size){
        super();
        this.size = size;
    }

    @Override
    public String getMessage() {
        return "ERROR: "+size+" is an invalid size";
    }
}
