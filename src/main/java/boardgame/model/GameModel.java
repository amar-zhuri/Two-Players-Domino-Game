package boardgame.model;

import game.State;
import game.BasicState;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import org.tinylog.Logger;

/**
 * The {@code GameModel} class represents the state and logic of a two-player board game.
 * It manages the game board, tracks player turns, and validates moves.
 * This class implements the {@link BasicState} interface, providing the necessary
 * methods to manage the game's move logic.
 *
 * <p>The game board is represented as a 2D array of {@link ReadOnlyObjectWrapper} objects,
 * each containing a {@link Players} enum value. The class also tracks the current player,
 * the legality of the last move, and the number of turns taken in the game.</p>
 *
 * <p>Key responsibilities of the {@code GameModel} include:</p>
 * <ul>
 *   <li>Initializing the game board and setting the initial game state.</li>
 *   <li>Providing methods to get the current state of the game board and players.</li>
 *   <li>Validating moves based on the current game state.</li>
 *   <li>Updating the game state based on player actions.</li>
 *   <li>Determining the status of the game (in progress, or if a player has won).</li>
 * </ul>
 *
 * <p>Example usage:</p>
 * <pre>{@code
 * GameModel gameModel = new GameModel();
 * }</pre>
 *
 * @see BasicState
 * @see ReadOnlyObjectWrapper
 * @see Players
 * @see State
 */
public class GameModel implements BasicState<Position> {
    /**
     * The size of the game board (number of rows and columns).
     */
    public static final int BOARD_SIZE = 8;
    private final ReadOnlyObjectWrapper<Players>[][] board;
    private Players currentPlayer = Players.PLAYER_1;
    private boolean lastMoveLegal = true;
    private int numberOfTurns = 0;

    /**
     * Constructs a new {@code GameModel} instance, initializing the game board.
     * <p>
     * The game board is represented as a 2D array of {@link ReadOnlyObjectWrapper} objects,
     * each containing a {@link Players} enum value. During initialization, every cell on the board
     * is set to {@link Players#NONE}, indicating that the cell is empty and available for a move.
     * <p>
     * The constructor also sets the first player to move to {@link Players#PLAYER_1}.
     * </p>
     * <p>
     * Example usage:
     * <pre>
     *     GameModel gameModel = new GameModel();
     * </pre>
     */
    @SuppressWarnings("unchecked")
    public GameModel() {
        // Initialize the board as a 2D array of ReadOnlyObjectWrapper<Players>
        board = new ReadOnlyObjectWrapper[BOARD_SIZE][BOARD_SIZE];

        // Iterate over each cell in the board and set its initial state to NONE
        for (var i = 0; i < BOARD_SIZE; i++) {
            for (var j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = new ReadOnlyObjectWrapper<>(Players.NONE);
            }
        }
    }

    /**
     * Returns a read-only property representing the player occupying the square
     * at the specified position on the board.
     *
     * @param row the row index of the square
     * @param col the column index of the square
     * @return a {@link ReadOnlyObjectProperty} of the player occupying the square
     */
    public ReadOnlyObjectProperty<Players> playerProperty(int row, int col) {
        return board[row][col].getReadOnlyProperty();
    }

    /**
     * Returns the player occupying the square at the specified position on the board.
     *
     * @param row the row index of the square
     * @param col the column index of the square
     * @return the {@link Players} occupying the square
     */
    public Players getPlayer(int row, int col) {
        return board[row][col].get();
    }

    /**
     * Returns the player who should make the next move.
     *
     * @return the next player as a {@link State.Player} enum
     */
    @Override
    public State.Player getNextPlayer() {
        return currentPlayer == Players.PLAYER_1 ? State.Player.PLAYER_1 : State.Player.PLAYER_2;
    }

    /**
     * Determines if the game is over. The game is considered over if there are
     * no legal moves left for the current player.
     *
     * @return {@code true} if the game is over, otherwise {@code false}
     */
    @Override
    public boolean isGameOver() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (isLegalMove(new Position(i, j))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Returns the current status of the game.
     *
     * @return the current status of the game as a {@link Status} enum
     */
    @Override
    public Status getStatus() {
        if (isGameOver()) {
            return (currentPlayer == Players.PLAYER_1) ? Status.PLAYER_2_WINS : Status.PLAYER_1_WINS;
        }
        return Status.IN_PROGRESS;
    }

    /**
     * Checks if it is legal to make a move to the specified position.
     * <p>
     * For {@link Players#PLAYER_1}, a move is considered legal if the specified position
     * and the adjacent position to the right are both empty.
     * For {@link Players#PLAYER_2}, a move is considered legal if the specified position
     * and the adjacent position below are both empty.
     * </p>
     *
     * @param position the position on the board
     * @return {@code true} if the move is legal, otherwise {@code false}
     */
    @Override
    public boolean isLegalMove(Position position) {
        int row = position.row();
        int col = position.col();
        if (currentPlayer == Players.PLAYER_1) { // Horizontal move
            return col < BOARD_SIZE - 1 && board[row][col].get() == Players.NONE && board[row][col + 1].get() == Players.NONE;
        } else { // Vertical move
            return row < BOARD_SIZE - 1 && board[row][col].get() == Players.NONE && board[row + 1][col].get() == Players.NONE;
        }
    }

    /**
     * Makes a move at the specified position if it is legal. Updates the board and the current player.
     * <p>
     * For {@link Players#PLAYER_1}, the move is made by placing a horizontal domino at the specified position.
     * For {@link Players#PLAYER_2}, the move is made by placing a vertical domino at the specified position.
     * The current player is then switched to the opponent.
     * </p>
     *
     * @param position the position on the board
     */
    @Override
    public void makeMove(Position position) {
        int row = position.row();
        int col = position.col();
        if (isLegalMove(position)) {
            Players playerSquare = currentPlayer;
            lastMoveLegal = true;

            try {
                if (currentPlayer == Players.PLAYER_1) { // Horizontal move
                    board[row][col].set(playerSquare);
                    board[row][col + 1].set(playerSquare);
                } else { // Vertical move
                    board[row][col].set(playerSquare);
                    board[row + 1][col].set(playerSquare);
                }

                numberOfTurns++;
                currentPlayer = currentPlayer.opponent();
            } catch (Exception e) {
                Logger.error(e, "Error making move at row={}, col={}", row, col);
                lastMoveLegal = false;
            }

        } else {
            lastMoveLegal = false;

        }
    }

    /**
     * Checks if the last move made was legal.
     *
     * @return {@code true} if the last move was legal, otherwise {@code false}
     */
    public boolean wasLastMoveLegal() {
        return lastMoveLegal;
    }

    /**
     * Returns the number of turns taken in the game so far.
     *
     * @return the number of turns taken
     */
    public int getNumberOfTurns() {
        return numberOfTurns;
    }

    /**
     * Returns a string representation of the game board, providing a visual layout
     * of the current state of the game. This method is useful for debugging and
     * logging purposes, as it allows the developer to see the arrangement of pieces
     * on the board.
     *
     * <p>The string representation includes:</p>
     * <ul>
     *   <li>Column headers at the top, indicating the column indices.</li>
     *   <li>Row headers on the left, indicating the row indices.</li>
     *   <li>The state of each cell on the board, represented by:
     *     <ul>
     *       <li>{@code .} for an empty cell ({@link Players#NONE})</li>
     *       <li>{@code X} for a cell occupied by Player 1 ({@link Players#PLAYER_1})</li>
     *       <li>{@code O} for a cell occupied by Player 2 ({@link Players#PLAYER_2})</li>
     *     </ul>
     *   </li>
     * </ul>
     *
     * <p>Example usage:</p>
     * <pre>{@code
     * GameModel gameModel = new GameModel();
     * System.out.println(gameModel.toString());
     * }</pre>
     *
     * @return a string representation of the game board
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        // Print column headers
        sb.append("  ");
        for (int col = 0; col < BOARD_SIZE; col++) {
            sb.append(col).append(" ");
        }
        sb.append("\n");

        // Print each row with row header
        for (int row = 0; row < BOARD_SIZE; row++) {
            sb.append(row).append(" ");
            for (int col = 0; col < BOARD_SIZE; col++) {
                switch (board[row][col].get()) {
                    case NONE -> sb.append(". ");
                    case PLAYER_1 -> sb.append("X ");
                    case PLAYER_2 -> sb.append("O ");
                }
            }
            sb.append("\n");
        }

        return sb.toString();
    }

}
