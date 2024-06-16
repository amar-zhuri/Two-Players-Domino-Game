package boardgame.controllers;

import boardgame.model.GameModel;
import boardgame.model.Position;
import game.State;
import javafx.application.Platform;
import javafx.beans.binding.ObjectBinding;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.tinylog.Logger;
import gameresult.*;
import gameresult.manager.*;
import gameresult.manager.json.*;

import java.io.IOException;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.ZonedDateTime;

/**
 * The {@code GameController} class serves as the primary controller for managing
 * the user interface and interactions in a two-player board game. It is responsible
 * for initializing the game board, handling user inputs, updating the game state,
 * and providing visual feedback to the players through the user interface.
 *
 * <p>The controller utilizes the {@link GameModel} to manage the game's logic and state,
 * ensuring that all moves are validated and the game progresses correctly. Player
 * interactions are captured through mouse events on the game board, which are then
 * processed to update the game state.</p>
 *
 * <p>The {@code GameController} class is designed to work with JavaFX, a software platform
 * for creating and delivering desktop applications. The class includes methods to
 * initialize the game board, set player names, update the current player label, handle
 * mouse click events on the game board squares, and manage the end-of-game scenario.</p>
 *
 * <p>Key responsibilities of the {@code GameController} include:</p>
 * <ul>
 *   <li>Setting up the game board by creating and styling squares, and binding event handlers for user interactions.</li>
 *   <li>Handling mouse click events to determine the position clicked, validate moves, update the game state, and provide visual feedback.</li>
 *   <li>Updating the label to indicate the current player's turn based on the game state.</li>
 *   <li>Managing game-over scenarios by determining the winner, saving the game result, and opening a game result view.</li>
 * </ul>
 *
 * <p>The controller also integrates with the {@link TwoPlayerGameResultManager} to save game results and display them in a result view.</p>
 *
 * <p>Example usage:</p>
 * <pre>{@code
 * // Creating an instance of GameController and setting player names
 * GameController gameController = new GameController();
 * gameController.setPlayerNames("Alice", "Bob");
 *
 * // Initializing the game board
 * gameController.initialize();
 * }</pre>
 *
 * <p>This class is annotated with {@link FXML} to indicate that it is a JavaFX controller.</p>
 *
 * <p>Detailed explanation of key methods:</p>
 * <ul>
 *   <li>{@link #initialize()}: Initializes the game board with squares and sets up event handlers.</li>
 *   <li>{@link #createSquare(int, int)}: Creates and styles a square on the game board at the specified row and column indices, and sets up event handlers for mouse interactions.</li>
 *   <li>{@link #setPlayerNames(String, String)}: Sets the names of the players and updates the current player label.</li>
 *   <li>{@link #updateCurrentPlayerLabel()}: Updates the label to indicate whose turn it is based on the game state.</li>
 *   <li>{@link #handleMouseClick(MouseEvent)}: Handles mouse click events on the game board squares, validates moves, updates the game state, and manages game-over scenarios.</li>
 *   <li>{@link #openGameResultView()}: Opens a new window to display game results when the game is over.</li>
 *   <li>{@link #saveGameResult()}: Saves the game result to a file using the {@link TwoPlayerGameResultManager}.</li>
 * </ul>
 *
 * @see GameModel
 * @see TwoPlayerGameResultManager
 * @see MouseEvent
 * @see FXML
 */
public class GameController {

    private final GameModel model = new GameModel();
    private final TwoPlayerGameResultManager resultManager = new JsonTwoPlayerGameResultManager(Paths.get("game_results.json"));
    @FXML
    private GridPane board;
    private String player1Name;
    private String player2Name;
    @FXML
    private Label currentPlayerLabel;
    private ZonedDateTime startTime;

    /**
     * Initializes the game controller and sets up the game board with the necessary
     * squares and event handlers. This method is automatically called after the FXML
     * file has been loaded. It configures the visual elements and prepares the game
     * board for user interactions.
     * <p>
     * The initialization process involves several key steps:
     * <ol>
     *   <li>Logging the start of the initialization process for debugging purposes.</li>
     *   <li>Iterating through each row and column of the {@link GridPane} representing the game board.</li>
     *   <li>Creating a square for each cell in the grid using the {@link #createSquare(int, int)} method.</li>
     *   <li>Adding the created square to the {@link GridPane} at the appropriate position.</li>
     *   <li>Logging the successful completion of the game board initialization.</li>
     *   <li>Handling any exceptions that occur during the initialization process by logging an error message.</li>
     * </ol>
     * </p>
     * <p>
     * Each square on the game board is styled and equipped with event handlers to manage user interactions,
     * such as mouse hover and click events. These interactions are crucial for updating the game state and
     * providing visual feedback to the players.
     * </p>
     * <p>
     * Detailed explanation of the steps:
     * <ul>
     *   <li><b>Logging Initialization Start:</b> The method begins by logging a debug message indicating that the game board initialization is starting. This helps in tracking the application flow and is useful for debugging.</li>
     *   <li><b>Iterating Through Grid Cells:</b> The method uses nested loops to iterate through each row and column of the grid. This ensures that every cell on the game board is processed.</li>
     *   <li><b>Creating Squares:</b> For each cell, the {@link #createSquare(int, int)} method is called. This method creates and styles a {@link StackPane} representing a square on the game board.</li>
     *   <li><b>Adding Squares to Grid:</b> The created square is added to the {@link GridPane} at the specified row and column indices using the  method.</li>
     *   <li><b>Logging Successful Initialization:</b> After all squares have been added to the grid, a debug message is logged indicating that the game board has been successfully initialized.</li>
     *   <li><b>Exception Handling:</b> If any exception occurs during the initialization process, it is caught and an error message is logged using . This helps in identifying and troubleshooting issues during initialization.</li>
     * </ul>
     * </p>
     *
     * <p>
     * Example usage:
     * <pre>{@code
     * // Automatically called after FXML file is loaded
     * gameController.initialize();
     * }</pre>
     * </p>
     *
     * @see GridPane
     * @see StackPane
     * @see Logger
     * @see #createSquare(int, int)
     */
    @FXML
    private void initialize() {
        Logger.debug("Initializing game board.");
        try {
            for (var i = 0; i < board.getRowCount(); i++) {
                for (int j = 0; j < board.getColumnCount(); j++) {
                    var square = createSquare(i, j);
                    board.add(square, j, i);
                }
            }
            Logger.debug("Game board initialized.");
        } catch (Exception e) {
            Logger.error(e, "Error during game board initialization");
        }
    }


    /**
     * Creates a square on the game board at the specified row and column indices.
     * Each square is styled based on its position and equipped with event handlers
     * for mouse enter, mouse exit, and mouse click events to enhance the interactive
     * experience of the game.
     * <p>
     * The square's background color alternates based on its position to create a
     * checkerboard pattern, enhancing visual clarity and aesthetics. Event handlers
     * are attached to change the background color when the mouse hovers over the square
     * and to handle move logic when the square is clicked.
     * </p>
     * <p>
     * Detailed steps:
     * <ol>
     *   <li>Log the creation of the square at the specified row and column for debugging purposes.</li>
     *   <li>Create a {@link StackPane} to represent the square and add a CSS style class for consistent styling.</li>
     *   <li>Set the initial background color of the square based on its position to achieve a checkerboard pattern.</li>
     *   <li>Attach a mouse enter event handler to change the background color when the mouse hovers over the square, providing visual feedback.</li>
     *   <li>Attach a mouse exit event handler to revert the background color when the mouse leaves the square, maintaining the checkerboard pattern.</li>
     *   <li>Create a {@link Rectangle} piece to be placed on the square and bind its fill property to the game's model to reflect the current state.</li>
     *   <li>Add the created piece to the square.</li>
     *   <li>Attach a mouse click event handler to the square to handle move logic and update the game state when the square is clicked.</li>
     *   <li>Return the fully configured {@link StackPane} representing the square.</li>
     * </ol>
     * </p>
     *
     * <p>
     * Example usage:
     * <pre>{@code
     * StackPane square = createSquare(0, 0);
     * board.add(square, 0, 0);
     * }</pre>
     * </p>
     *
     * @param row the row index of the square
     * @param col the column index of the square
     * @return a {@link StackPane} representing the square
     * @see StackPane
     * @see Rectangle
     * @see Color
     * @see Logger
     */
    private StackPane createSquare(int row, int col) {
        Logger.debug("Creating square at ({}, {})", row, col);
        var square = new StackPane();
        square.getStyleClass().add("square");
        if ((row + col) % 2 == 0) {
            square.setStyle("-fx-background-color: white;");
        } else {
            square.setStyle("-fx-background-color: gray;");
        }
        square.setOnMouseEntered(event -> {
            if ((row + col) % 2 == 0) {
                square.setStyle("-fx-background-color: #ffffe0;");
            } else {
                square.setStyle("-fx-background-color: #b0f3b0;");
            }
        });
        square.setOnMouseExited(event -> {
            if ((row + col) % 2 == 0) {
                square.setStyle("-fx-background-color: white;");
            } else {
                square.setStyle("-fx-background-color: grey;");
            }
        });
        var piece = new Rectangle(60, 60);
        piece.fillProperty().bind(new ObjectBinding<Paint>() {
            {
                super.bind(model.playerProperty(row, col));
            }

            @Override
            protected Paint computeValue() {
                return switch (model.getPlayer(row, col)) {
                    case NONE -> Color.TRANSPARENT;
                    case PLAYER_1 -> Color.BLUE;
                    case PLAYER_2 -> Color.RED;
                };
            }
        });
        square.getChildren().add(piece);
        square.setOnMouseClicked(this::handleMouseClick);
        return square;
    }


    /**
     * Sets the names of the players and updates the label to indicate whose turn it is.
     * This method should be called before the game starts to ensure that player names
     * are correctly displayed during gameplay.
     *
     * <p>This method performs the following actions:</p>
     * <ol>
     *   <li>Assigns the provided names to the instance variables {@code player1Name} and {@code player2Name}.</li>
     *   <li>Logs the player names using the {@link Logger} to provide traceability and facilitate debugging.</li>
     *   <li>Calls the {@link #updateCurrentPlayerLabel()} method to refresh the label displaying the current player's turn.</li>
     * </ol>
     *
     * <p>It is crucial to call this method before the game starts to ensure that the UI accurately reflects
     * the names of the players. Failing to do so may result in an incomplete or incorrect display of player information.</p>
     *
     * <p>Example usage:</p>
     * <pre>{@code
     * GameController gameController = new GameController();
     * gameController.setPlayerNames("Alice", "Bob");
     * }</pre>
     *
     * @param player1 the name of player 1
     * @param player2 the name of player 2
     */
    public void setPlayerNames(String player1, String player2) {
        this.player1Name = player1;
        this.player2Name = player2;
        Logger.info("Player names set: {} (Player 1), {} (Player 2)", player1Name, player2Name);
        updateCurrentPlayerLabel();
        startTime = ZonedDateTime.now();
    }

    /**
     * Updates the label displaying the current player's turn based on the game model's state.
     * This method is called whenever the turn changes to ensure that the UI accurately reflects
     * the current state of the game.
     *
     * <p>The method performs the following actions:</p>
     * <ol>
     *   <li>Determines the current player by checking the next player to move in the game model.
     *       It compares the next player against {@link State.Player#PLAYER_1}.</li>
     *   <li>Assigns the name of the current player to the {@code currentPlayer} variable.
     *       If the next player is {@code PLAYER_1}, it sets {@code currentPlayer} to {@code player1Name}.
     *       Otherwise, it sets {@code currentPlayer} to {@code player2Name}.</li>
     *   <li>Updates the {@link Label} control {@code currentPlayerLabel} to display the current player's turn.
     *       The text is set to the current player's name followed by "'s turn".</li>
     *   <li>Logs the name of the current player using the {@link Logger} to provide traceability and facilitate debugging.</li>
     * </ol>
     *
     * <p>This method ensures that the user interface is always up-to-date with the latest game state,
     * providing players with clear and accurate information about whose turn it is. It is typically
     * invoked after each move to reflect the change in turn.</p>
     *
     * <p>Example usage:</p>
     * <pre>{@code
     * // After a move has been made, update the current player label
     * updateCurrentPlayerLabel();
     * }</pre>
     *
     * @see State.Player
     * @see GameModel#getNextPlayer()
     * @see Label#setText(String)
     */
    private void updateCurrentPlayerLabel() {
        String currentPlayer = model.getNextPlayer() == State.Player.PLAYER_1 ? player1Name : player2Name;
        currentPlayerLabel.setText(currentPlayer + "'s turn");
        Logger.info("Current player is: {}", currentPlayer);
    }

    /**
     * Handles mouse click events on the game board squares. When a square is clicked,
     * this method determines the square's position, attempts to make a move, and updates
     * the game state accordingly.
     *
     * <p>If the move is legal, the game state is updated, the current player label is refreshed,
     * and a check is performed to determine if the game is over. If the game is over, an alert
     * is displayed to announce the winner, and the game result is saved. If the move is illegal,
     * an error alert is shown to inform the player.</p>
     *
     * <p>When the game ends, the game result is saved by calling the {@link #saveGameResult()} method,
     * and the game result view is opened by calling the {@link #openGameResultView()} method.</p>
     *
     * <p>Detailed flow:</p>
     * <ul>
     *   <li>Retrieve the source of the event and determine the row and column of the clicked square.</li>
     *   <li>Log the position of the clicked square.</li>
     *   <li>Attempt to make a move by calling {@link GameModel#makeMove(Position)} with the position.</li>
     *   <li>Check if the move was legal by calling {@link GameModel#wasLastMoveLegal()}.</li>
     *   <li>If the move is legal:
     *     <ul>
     *       <li>Log the move with the current player's name.</li>
     *       <li>Check if the game is over by calling {@link GameModel#isGameOver()}.</li>
     *       <li>If the game is over:
     *         <ul>
     *           <li>Determine the winner based on the next player.</li>
     *           <li>Log the game result and save it by calling {@link #saveGameResult()}.</li>
     *           <li>Display an information alert announcing the winner.</li>
     *           <li>Open the game result view by calling {@link #openGameResultView()} when the alert is closed.</li>
     *         </ul>
     *       </li>
     *       <li>If the game is not over, update the current player label by calling {@link #updateCurrentPlayerLabel()}.</li>
     *     </ul>
     *   </li>
     *   <li>If the move is illegal:
     *     <ul>
     *       <li>Log a warning about the illegal move.</li>
     *       <li>Display an error alert informing the player about the illegal move.</li>
     *     </ul>
     *   </li>
     * </ul>
     *
     * <p>Exception Handling:</p>
     * <p>If an exception occurs while processing the mouse click event, it is caught and logged.
     * The application does not show an alert for the error, but it logs the details to help with debugging.</p>
     *
     * <p>Example usage:</p>
     * <pre>{@code
     * // Attach this method as an event handler for mouse clicks on game board squares
     * square.setOnMouseClicked(this::handleMouseClick);
     * }</pre>
     *
     * @param event the mouse click event
     * @see MouseEvent
     * @see GameModel#makeMove(Position)
     * @see GameModel#wasLastMoveLegal()
     * @see GameModel#isGameOver()
     * @see #saveGameResult()
     * @see #openGameResultView()
     * @see #updateCurrentPlayerLabel()
     * @see Alert
     * @see Logger
     */
    @FXML
    private void handleMouseClick(MouseEvent event) {
        var square = (StackPane) event.getSource();
        var row = GridPane.getRowIndex(square);
        var col = GridPane.getColumnIndex(square);
        Logger.info("Player clicked on square ({}, {})", row, col);

        try {
            model.makeMove(new Position(row, col));

            if (model.wasLastMoveLegal()) {
                Logger.info("Move made by {}: row={}, col={}", model.getNextPlayer().opponent() == State.Player.PLAYER_1 ? player1Name : player2Name, row, col);

                if (model.isGameOver()) {
                    String winner;
                    if (model.isWinner(State.Player.PLAYER_1)) {
                        winner = player1Name;
                    } else if (model.isWinner(State.Player.PLAYER_2)) {
                        winner = player2Name;
                    } else {
                        winner = "No one"; // If for some reason, no winner is determined
                    }
                    Logger.info("Game over. Winner: {}", winner);
                    saveGameResult();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Game Over");
                    alert.setHeaderText(null);
                    alert.setContentText("Game Over! " + winner + " wins!");
                    alert.showAndWait().ifPresent(response -> openGameResultView());
                } else {
                    updateCurrentPlayerLabel();
                }
            } else {
                Logger.warn("Illegal move attempted at row={}, col={}", row, col);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Illegal Move");
                alert.setHeaderText(null);
                alert.setContentText("Illegal move attempted at row=" + row + ", col=" + col);
                alert.showAndWait();
            }
        } catch (Exception e) {
            Logger.error(e, "Error handling mouse click at row={}, col={}", row, col);
        }
    }

    /**
     * Opens a new window to display the game results. This method is typically called
     * when the game ends to show the players the results of the game.
     *
     * <p>The method performs the following steps:</p>
     * <ol>
     *   <li>Logs a debug message indicating that the game result view is being opened.</li>
     *   <li>Attempts to load the FXML file for the game result view using {@link FXMLLoader}.</li>
     *   <li>If the FXML file is successfully loaded:
     *     <ul>
     *       <li>Creates a {@link Parent} node from the loaded FXML file.</li>
     *       <li>Creates a new {@link Stage} to display the game result view.</li>
     *       <li>Sets the title of the stage to "Game Results".</li>
     *       <li>Creates a new {@link Scene} with the root node and sets it on the stage.</li>
     *       <li>Sets an event handler for the stage's close request to log a debug message and exit the application.</li>
     *       <li>Shows the stage to display the game result view to the players.</li>
     *     </ul>
     *   </li>
     *   <li>If an {@link IOException} occurs during the loading of the FXML file, it is caught and an error message is logged using .</li>
     * </ol>
     *
     * <p>Example usage:</p>
     * <pre>{@code
     * // Called when the game is over to display the game results
     * openGameResultView();
     * }</pre>
     *
     * @see FXMLLoader
     * @see Parent
     * @see Stage
     * @see Scene
     * @see Platform#exit()
     * @see Logger
     */
    private void openGameResultView() {
        Logger.debug("Opening game result view");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/GameResultView.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Game Results");
            stage.setScene(new Scene(root));
            stage.setMinWidth(635);
            stage.setOnCloseRequest(event -> {
                Logger.debug("Game result window closed. Terminating application.");
                Platform.exit();
            });
            stage.show();
        } catch (IOException e) {
            Logger.error(e, "Failed to open game result view");
        }
    }

    /**
     * Saves the game result by creating a {@link TwoPlayerGameResult} object with the current
     * game state and storing it using the {@link TwoPlayerGameResultManager}. This method
     * is typically called when the game ends to preserve the game outcome for future reference.
     *
     * <p>The method performs the following steps:</p>
     * <ol>
     *   <li>Logs a debug message indicating that the game result saving process has started.</li>
     *   <li>Creates a {@link TwoPlayerGameResult} object using the builder pattern. The object is populated with:
     *     <ul>
     *       <li>The name of player 1 ({@code player1Name}).</li>
     *       <li>The name of player 2 ({@code player2Name}).</li>
     *       <li>The current game status obtained from the model via {@link GameModel#getStatus()}.</li>
     *       <li>The number of turns taken in the game obtained from the model via {@link GameModel#getNumberOfTurns()}.</li>
     *       <li>The current date and time using {@link ZonedDateTime#now()}.</li>
     *       <li>The duration of the game calculated from the number of turns using {@link Duration#ofSeconds(long)}. Note that this is an example duration and can be adjusted as needed.</li>
     *     </ul>
     *   </li>
     *   <li>Attempts to add the created game result to the {@link TwoPlayerGameResultManager}.</li>
     *   <li>If the game result is successfully saved, logs a debug message indicating the success.</li>
     *   <li>If an {@link IOException} occurs while saving the game result, it is caught and an error message is logged using .</li>
     * </ol>
     *
     * <p>Example usage:</p>
     * <pre>{@code
     * // Called when the game is over to save the game result
     * saveGameResult();
     * }</pre>
     *
     * @see TwoPlayerGameResult
     * @see TwoPlayerGameResultManager
     * @see GameModel#getStatus()
     * @see GameModel#getNumberOfTurns()
     * @see ZonedDateTime#now()
     * @see Duration#ofSeconds(long)
     * @see Logger
     */
    private void saveGameResult() {
        ZonedDateTime endTime = ZonedDateTime.now();
        Duration duration = Duration.between(startTime, endTime);

        Logger.debug("Saving game result");
        TwoPlayerGameResult result = TwoPlayerGameResult.builder().player1Name(player1Name).player2Name(player2Name).status(model.getStatus()).numberOfTurns(model.getNumberOfTurns()).created(endTime).duration(duration).build();
        try {
            resultManager.add(result);
            Logger.debug("Game result saved successfully.");
        } catch (IOException e) {
            Logger.error(e, "Failed to save game result");
        }
    }
}
