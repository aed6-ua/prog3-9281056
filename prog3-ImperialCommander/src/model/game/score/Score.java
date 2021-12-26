package model.game.score;

import model.Side;
/**
 * Score class used to store the side and value of a score
 * @author Eduard Andrei Duta | NIE: X9281056G
 */
public abstract class Score<T> implements Comparable<Score<T>> {
    /**
     * The score
     */
    protected int score;
    /**
     * Side of the player that got this score
     */
    private Side side;

    /**
     * Constructor for the Score, takes the score number and the side.
     * @param side of the score
     */
    public Score(Side side) {
        this.side = side;
        this.score = 0;
    }

    /**
     * Gets the score
     * @return the score
     */
    public int getScore() {
        return this.score;
    }

    /**
     * Compares the score to another score and returns 1, 0 or -1.
     * @param other score to compare to
     * @return 1 if this score is greater, -1 is the specified score is
     * greater and 0 if they are equals.
     */
    public int compareTo(Score<T> other) {
        if (this.score < other.score)
            return 1;
        else if (this.score > other.score)
            return -1;
        return this.side.compareTo(other.side);
    }

    /**
     * Prints a string with the the players side and score
     * @return a string with the the players side and score
     */
    public String toString() {
        return "Player "+this.side+": "+this.score;
    }

    /**
     * Increases the score
     * @param sc takes a class T
     */
    public abstract void score(T sc);
}
