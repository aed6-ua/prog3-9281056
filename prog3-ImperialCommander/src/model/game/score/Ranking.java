package model.game.score;

import java.util.SortedSet;
import java.util.TreeSet;
/**
 * Ranking of a specific type of score
 * @author Eduard Andrei Duta | NIE: X9281056G
 */
public class Ranking<ScoreType extends Score<?>> {
    /**
     * Ordered unique list of Scores of the ScoreType
     */
    private SortedSet<ScoreType> scoreSet;

    /**
     * Constructor for the Ranking. Initializes the scoreSet.
     */
    public Ranking() {
        scoreSet = new TreeSet<>();
    }

    /**
     * Adds the parameter object to the scoreSet
     * @param st object to add to the scoreSet
     */
    public void addScore(ScoreType st) {
        this.scoreSet.add(st);
    }

    /**
     * Gets the scoreSet
     * @return the scoreSet
     */
    public SortedSet<ScoreType> getSortedRanking() {
        return this.scoreSet;
    }

    /**
     * Gets the first element of scoreSet
     * @return first element of scoreSet
     */
    public ScoreType getWinner() {
        if (this.scoreSet.isEmpty())
            throw new RuntimeException();
        return this.scoreSet.first();
    }

    /**
     * Creates a string with the ranking scores
     * @return a string with the ranking scores
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (ScoreType st:
             this.scoreSet) {
            sb.append("| ");
            sb.append(st.toString());
        }
        sb.append(" |");
        return sb.toString();
    }
}
