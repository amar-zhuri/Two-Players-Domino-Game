package boardgame.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.tinylog.Logger;

import java.io.IOException;

/**
 * The {@code OpeningScreenController} class manages the user interface and interactions
 * for the opening screen of the board game. It handles player name input and initiates
 * the transition to the main game screen.
 *
 * <p>This controller ensures that both player names are entered before starting the game.
 * It also handles the loading of the main game screen FXML file and passes the player
 * names to the {@link GameController}.</p>
 *
 * <p>Key responsibilities of the {@code OpeningScreenController} include:</p>
 * <ul>
 *   <li>Capturing player names from the input fields.</li>
 *   <li>Validating that both player names are provided.</li>
 *   <li>Loading the main game screen and passing the player names to the {@link GameController}.</li>
 *   <li>Handling potential errors during the loading process and providing user feedback.</li>
 * </ul>
 *
 * <p>Example usage:</p>
 * <pre>{@code
 * // The OpeningScreenController is automatically instantiated and initialized by the JavaFX framework
 * // when the corresponding FXML file is loaded.
 * }</pre>
 *
 * @see GameController
 * @see FXMLLoader
 * @see Parent
 * @see Scene
 * @see Stage
 * @see Alert
 * @see Logger
 */

public class OpeningScreenController {

    @FXML
    private TextField player1Name;

    @FXML
    private TextField player2Name;


    /**
     * Starts the game by validating player names and loading the main game screen.
     *
     * <p>This method is triggered when the user clicks the "Start Game" button. It first
     * checks that both player names are entered. If either name is missing, a warning
     * alert is displayed. If both names are provided, it proceeds to load the main
     * game screen from the {@code GameView.fxml} file.</p>
     *
     * <p>The method performs the following actions:</p>
     * <ol>
     *   <li>Retrieves the text entered in the {@code player1Name} and {@code player2Name} fields.</li>
     *   <li>Validates that both player names are entered. If either name is missing, a warning alert is displayed and the method returns.</li>
     *   <li>Logs a debug message indicating that the main game screen is being loaded.</li>
     *   <li>Uses {@link FXMLLoader} to load the {@code GameView.fxml} file.</li>
     *   <li>If the FXML file is successfully loaded:
     *     <ul>
     *       <li>Retrieves the {@link GameController} instance from the loader.</li>
     *       <li>Passes the player names to the {@code GameController} by calling {@link GameController#setPlayerNames(String, String)}.</li>
     *       <li>Sets the scene to the main game screen and shows the stage.</li>
     *       <li>Logs a debug message indicating that the game has started with the specified players.</li>
     *     </ul>
     *   </li>
     *   <li>If an {@link IOException} occurs during the loading of the FXML file, it is caught and an error alert is displayed to the user. The error is also logged using .</li>
     * </ol>
     *
     * <p>Example usage:</p>
     * <pre>{@code
     * // Called when the user clicks the "Start Game" button
     * startGame();
     * }</pre>
     *
     * @see FXMLLoader
     * @see Parent
     * @see Scene
     * @see Stage
     * @see Alert
     * @see Logger
     */
    @FXML
    private void startGame() {
        String player1 = player1Name.getText();
        String player2 = player2Name.getText();

        // Validate that both player names are entered
        if (player1.isEmpty() || player2.isEmpty()) {
            Logger.warn("Player names not entered. player1: '{}', player2: '{}'", player1, player2);
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Input Error");
            alert.setHeaderText(null);
            alert.setContentText("Both player names must be entered.");
            alert.showAndWait();
            return;
        }

        try {
            Logger.debug("Loading GameView.fxml to start the game.");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/GameView.fxml"));
            Parent root = loader.load();

            // Get the GameController and set player names
            GameController gameController = loader.getController();
            gameController.setPlayerNames(player1, player2);

            // Set the scene to the main game screen
            Stage stage = (Stage) player1Name.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
            Logger.debug("Game started with players: '{}' and '{}'", player1, player2);
        } catch (IOException e) {
            Logger.error(e, "Failed to load GameView.fxml");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Loading Error");
            alert.setHeaderText(null);
            alert.setContentText("Failed to start the game. Please try again.");
            alert.showAndWait();
        }
    }
}
