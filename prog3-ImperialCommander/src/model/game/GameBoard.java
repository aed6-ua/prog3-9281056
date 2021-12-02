package model.game;

import model.Board;
import model.Coordinate;
import model.Fighter;
import model.Side;
import model.exceptions.InvalidSizeException;

import java.util.Objects;

/**
 * Subclass of Board used for the game
 * @author Eduard Andrei Duta | NIE: X9281056G
 */
public class GameBoard extends Board {
    /**
     * Constructor for the Board. Creates an empty board of the specified size.
     *
     * @param size of the board
     * @throws InvalidSizeException sizes lower than 5 not supported
     */
    public GameBoard(int size) throws InvalidSizeException {
        super(size);
    }

    /**
     * Gets the number of fighters of specified side currently on the board.
     * @param side of the fighters
     * @return number of fighters of specified side
     */
    public int numFighters(Side side) {
        int count = 0;
        for (Coordinate c:
             this.board.keySet()) {
            if(this.board.get(c).getSide().equals(side)) count++;
        }
        return count;

    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("  ");
        for (int j = 0; j<this.getSize(); j++) {
            sb.append(j);
        }
        sb.append("\n  ");
        for (int i = 0; i<this.getSize(); i++) {
            sb.append("-");
        }

        for (int k = 0; k<this.getSize(); k++) {
            sb.append("\n");
            sb.append(k);
            sb.append("|");
            for (int l = 0; l<this.getSize(); l++) {
                Fighter f = this.board.get(new Coordinate(l,k));
                if (Objects.isNull(f)) {
                    sb.append(" ");
                }
                else {
                    sb.append(f.getSymbol());
                }
            }

        }
        return sb.toString();
    }
}
