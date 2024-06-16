package boardgame.controllers;


import gameresult.manager.TwoPlayerGameResultManager;
import gameresult.manager.json.JsonTwoPlayerGameResultManager;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.tinylog.Logger;
import gameresult.*;
import gameresult.manager.*;
import gameresult.manager.json.*;

import java.io.IOException;
import java.nio.file.Paths;

/**
 * The {@code GameResultController} class manages the user interface and interactions
 * for displaying the game results in a table view. It is responsible for loading the
 * game results from a file and populating the table with the results.
 *
 * <p>This controller is associated with a JavaFX {@link TableView} that displays
 * the results of the game. Each column in the table corresponds to a property of
 * the {@link TwoPlayerGameResult} class, such as player names, game status, number
 * of turns, creation date, and game duration.</p>
 *
 * <p>The {@code GameResultController} class performs the following key functions:</p>
 * <ul>
 *   <li>Initializes the table columns and binds them to the properties of {@link TwoPlayerGameResult}.</li>
 *   <li>Loads the game results from a JSON file using {@link JsonTwoPlayerGameResultManager} and populates the table.</li>
 * </ul>
 *
 * <p>Example usage:</p>
 * <pre>{@code
 * // The GameResultController is automatically instantiated and initialized by the JavaFX framework
 * // when the corresponding FXML file is loaded.
 * }</pre>
 *
 * @see TwoPlayerGameResult
 * @see TwoPlayerGameResultManager
 * @see JsonTwoPlayerGameResultManager
 * @see TableView
 * @see TableColumn
 * @see Logger
 */
public class GameResultController {

    private final TwoPlayerGameResultManager resultManager = new JsonTwoPlayerGameResultManager(Paths.get("game_results.json"));
    @FXML
    private TableView<TwoPlayerGameResult> resultTable;
    @FXML
    private TableColumn<TwoPlayerGameResult, String> player1Column;
    @FXML
    private TableColumn<TwoPlayerGameResult, String> player2Column;
    @FXML
    private TableColumn<TwoPlayerGameResult, String> statusColumn;
    @FXML
    private TableColumn<TwoPlayerGameResult, Integer> turnsColumn;
    @FXML
    private TableColumn<TwoPlayerGameResult, String> dateColumn;
    @FXML
    private TableColumn<TwoPlayerGameResult, String> durationColumn;

    /**
     * Initializes the game result controller and sets up the table columns with
     * the appropriate cell value factories. This method is automatically called
     * after the FXML file has been loaded.
     *
     * <p>The initialization process involves:</p>
     * <ol>
     *   <li>Setting the cell value factories for each table column to bind them to the properties of {@link TwoPlayerGameResult}.</li>
     *   <li>Calling the {@link #loadGameResults()} method to load the game results from the file and populate the table.</li>
     * </ol>
     */
    @FXML
    private void initialize() {
        player1Column.setCellValueFactory(new PropertyValueFactory<>("player1Name"));
        player2Column.setCellValueFactory(new PropertyValueFactory<>("player2Name"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        turnsColumn.setCellValueFactory(new PropertyValueFactory<>("numberOfTurns"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("created"));
        durationColumn.setCellValueFactory(new PropertyValueFactory<>("duration"));

        loadGameResults();
    }

    /**
     * Loads the game results from the JSON file and populates the table view.
     *
     * <p>This method performs the following actions:</p>
     * <ol>
     *   <li>Logs a debug message indicating the start of the game results loading process.</li>
     *   <li>Attempts to retrieve all game results from the {@link TwoPlayerGameResultManager} and set them in the table view.</li>
     *   <li>If the game results are successfully loaded, logs an info message indicating success.</li>
     *   <li>If an {@link IOException} occurs while loading the game results, it is caught and an error message is logged using .</li>
     *   <li>If any other unexpected exception occurs, it is caught and an error message is logged to help with debugging.</li>
     * </ol>
     *
     * <p>Example usage:</p>
     * <pre>{@code
     * // Called during the initialization of the controller to load and display game results
     * loadGameResults();
     * }</pre>
     *
     * @see TwoPlayerGameResultManager#getAll()
     * @see TableView#setItems(javafx.collections.ObservableList)
     * @see Logger
     */
    private void loadGameResults() {
        Logger.debug("Loading game results from file.");
        try {
            resultTable.getItems().setAll(resultManager.getAll());
            Logger.info("Game results loaded successfully.");
        } catch (IOException e) {
            Logger.error(e, "Failed to load game results");

        } catch (Exception e) {
            Logger.error(e, "Unexpected error occurred while loading game results");

        }
    }
}

