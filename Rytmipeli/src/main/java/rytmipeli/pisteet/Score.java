/*
 * 
 */
package rytmipeli.pisteet;

import javafx.beans.property.SimpleIntegerProperty;

public class Score implements Comparable<Score> {

    private final String playerName;
    private final SimpleIntegerProperty playerScore;

    /**
     * Konstruktori piste-oliolle.
     *
     * @param playerName Pelaajan playerName
     * @param playerScore pelaajan savuttama pistemäärä.
     */
    public Score(String playerName, SimpleIntegerProperty playerScore) {
        this.playerName = playerName;
        this.playerScore = playerScore;
    }

    public String getName() {
        return playerName;
    }

    public Integer getPlayerScoreFactory() {
        return playerScore.get();
    }

    @Override
    public int compareTo(Score otherPlayerScore) {
        return Integer.compare(this.getPlayerScoreFactory(), otherPlayerScore.getPlayerScoreFactory());
    }

    @Override
    public String toString() {
        return " " + this.playerName + " " + this.playerScore;
    }
}
