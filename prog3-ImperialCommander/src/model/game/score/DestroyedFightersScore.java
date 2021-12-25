package model.game.score;

import model.Fighter;
import model.Side;

import java.util.Objects;

/**
 * Subclass of Board used for the game
 * @author Eduard Andrei Duta | NIE: X9281056G
 */
public class DestroyedFightersScore extends Score<Fighter>{

    /**
     * Constructor for the Score, takes the score number and the side.
     *
     * @param side of the score
     */
    public DestroyedFightersScore(Side side) {
        super(side);
    }

    @Override
    public void score(Fighter sc) {
        if (Objects.nonNull(sc))
            this.score += sc.getValue();
    }

}
