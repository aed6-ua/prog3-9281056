package model.exceptions;


@SuppressWarnings("serial")
public class NoFighterAvailableException extends Exception{

    private String type;

    public NoFighterAvailableException(String type){
        super();
        this.type = type;
    }

    @Override
    public java.lang.String getMessage() {
        return "ERROR: no fighter of type "+type+" available";
    }
}
