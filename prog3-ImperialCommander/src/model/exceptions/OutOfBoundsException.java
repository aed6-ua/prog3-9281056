package model.exceptions;


import model.Coordinate;

@SuppressWarnings("serial")
public class OutOfBoundsException extends Exception{

    private Coordinate c;

    public OutOfBoundsException(Coordinate c){
        super();
        this.c = c;
    }

    @Override
    public java.lang.String getMessage() {
        return super.getMessage()+c;
    }
}
