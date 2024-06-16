package boardgame.model;

/**
 * Enumeration representing the players and empty state in the game.
 */
public enum Players {
    NONE,
    PLAYER_1,
    PLAYER_2;

    /**
     * Returns the opponent of the current player.
     *
     * @return the opponent player
     */
    public Players opponent() {
        return this == PLAYER_1 ? PLAYER_2 : PLAYER_1;
    }
}
