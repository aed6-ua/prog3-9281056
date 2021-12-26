package model.game.score;

import model.Side;

import java.util.Objects;

/**
 * Score for the wins
 * @author Eduard Andrei Duta | NIE: X9281056G
 */
public class WinsScore extends Score<Integer>{
    /**
     * Calls the super constructor passing the side
     * @param side of the score
     */
    public WinsScore(Side side) {
        super(side);
    }

    @Override
    public void score(Integer w) {
        if (!Objects.isNull(w))
            if (w.equals(1))
                this.score++;
    }
}
