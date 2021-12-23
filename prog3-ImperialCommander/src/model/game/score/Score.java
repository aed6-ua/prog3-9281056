package model.game.score;

import model.Side;

public abstract class Score<T> implements Comparable<Score<T>> {

    protected int score;

    private Side side;

    public Score(Side side) {
        this.side = side;
        this.score = 0;
    }

    public int getScore() {
        return this.score;
    }

    public int compareTo(Score<T> other) {
        if (this.score < other.score)
            return 1;
        else if (this.score > other.score)
            return -1;
        return 0;
    }

    public String toString() {
        return "Player "+this.side+": "+this.score;
    }

    public abstract void score(Score<T> sc);

    public abstract void score(Integer w);
}
