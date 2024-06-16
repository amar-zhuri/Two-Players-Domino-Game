/**
 * Provides the model classes for the two-player board game.
 * <p>
 * This package contains the core classes that represent the state and logic of the game,
 * including the game board, player management, and move validation.
 * </p>
 *
 * <h2>Package Overview</h2>
 * <p>
 * The {@code boardgame.model} package is designed to manage the state and logic of a two-player board game.
 * The key classes included in this package are:
 * </p>
 *
 * <ul>
 *   <li>{@link boardgame.model.GameModel GameModel} - Represents the main game model, handling the game state, player turns, and move validation.</li>
 *   <li>{@link boardgame.model.Players Players} - An enumeration representing the players in the game, including methods to determine the opponent.</li>
 *   <li>{@link boardgame.model.Position Position} - Represents a position on the game board with row and column indices.</li>
 * </ul>
 *
 * <h2>Class Details</h2>
 *
 * <h3>GameModel</h3>
 * <p>
 * The {@link boardgame.model.GameModel GameModel} class is the central component of the game model. It implements the {@link game.BasicState BasicState} interface,
 * providing the necessary methods to manage the game's move logic.
 * </p>
 * <p><b>Responsibilities:</b></p>
 * <ul>
 *   <li>Initializes the game board and sets the initial game state.</li>
 *   <li>Provides methods to get the current state of the game board and players.</li>
 *   <li>Validates moves based on the current game state.</li>
 *   <li>Updates the game state based on player actions.</li>
 *   <li>Determines the status of the game (in progress, or if a player has won).</li>
 * </ul>
 * <p>
 * The game board is represented as a 2D array of {@link javafx.beans.property.ReadOnlyObjectWrapper ReadOnlyObjectWrapper} objects,
 * each containing a {@link boardgame.model.Players Players} enum value. The class also tracks the current player, the legality of the last move,
 * and the number of turns taken in the game.
 * </p>
 *
 * <h3>Players</h3>
 * <p>
 * The {@link boardgame.model.Players Players} enumeration represents the possible states of each cell on the game board: empty, occupied by player 1, or occupied by player 2.
 * It also includes a method to determine the opponent of the current player.
 * </p>
 *
 * <h3>Position</h3>
 * <p>
 * The {@link boardgame.model.Position Position} record represents a position on the game board with a row and column index.
 * It is used to pass positions around within the game model and related methods.
 * </p>
 *
 * <h2>Example Usage</h2>
 * <pre>{@code
 * GameModel gameModel = new GameModel();
 * Position position = new Position(0, 0);
 * if (gameModel.isLegalMove(position)) {
 *     gameModel.makeMove(position);
 * }
 * }</pre>
 *
 * <h2>Dependencies</h2>
 * <ul>
 *   <li>{@link game.State State} - Provides the base game state interface.</li>
 *   <li>{@link game.BasicState BasicState} - Extends the game state to support basic moves.</li>
 *   <li>{@link javafx.beans.property.ReadOnlyObjectProperty ReadOnlyObjectProperty} - JavaFX property used for binding the game state to the UI.</li>
 *   <li>{@link org.tinylog.Logger Logger} - Used for logging important events and errors.</li>
 * </ul>
 *
 * @see boardgame.model.GameModel
 * @see boardgame.model.Players
 * @see boardgame.model.Position
 * @see game.State
 * @see game.BasicState
 * @see javafx.beans.property.ReadOnlyObjectWrapper
 */
package boardgame.model;
