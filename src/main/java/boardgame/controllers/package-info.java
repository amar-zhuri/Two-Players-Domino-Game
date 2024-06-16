/**
 * Provides the controller classes for the two-player board game.
 * <p>
 * This package contains the classes responsible for managing the user interface,
 * handling user interactions, and updating the game state in a two-player board game.
 * </p>
 *
 * <h2>Package Overview</h2>
 * <p>
 * The {@code boardgame.controllers} package is designed to facilitate interaction between
 * the game's user interface and the underlying game logic. The key classes included in
 * this package are:
 * </p>
 *
 * <ul>
 *   <li>{@link boardgame.controllers.GameController GameController} - Manages the main game screen, handles user interactions on the game board, and updates the game state.</li>
 *   <li>{@link boardgame.controllers.GameResultController GameResultController} - Manages the display of game results in a table view, loading results from a file and populating the view.</li>
 *   <li>{@link boardgame.controllers.OpeningScreenController OpeningScreenController} - Manages the opening screen, capturing player names and initiating the game start process.</li>
 * </ul>
 *
 * <h2>Class Details</h2>
 *
 * <h3>GameController</h3>
 * <p>
 * The {@link boardgame.controllers.GameController GameController} class is the primary controller for managing
 * the user interface and interactions during gameplay. It initializes the game board, handles user inputs,
 * updates the game state, and provides visual feedback to players.
 * </p>
 *
 * <h3>GameResultController</h3>
 * <p>
 * The {@link boardgame.controllers.GameResultController GameResultController} class manages the display of game results
 * in a table view. It initializes the table columns, binds them to the properties of the game result objects,
 * and loads the game results from a JSON file.
 * </p>
 *
 * <h3>OpeningScreenController</h3>
 * <p>
 * The {@link boardgame.controllers.OpeningScreenController OpeningScreenController} class handles the opening screen,
 * capturing player names from the input fields and starting the game by loading the main game screen.
 * </p>
 *
 * <h2>Example Usage</h2>
 * <pre>{@code
 * // Creating and initializing the GameController
 * GameController gameController = new GameController();
 * gameController.setPlayerNames("Alice", "Bob");
 * gameController.initialize();
 *
 * // Creating and initializing the GameResultController
 * GameResultController gameResultController = new GameResultController();
 * gameResultController.initialize();
 *
 * // Creating and initializing the OpeningScreenController
 * OpeningScreenController openingScreenController = new OpeningScreenController();
 * openingScreenController.startGame();
 * }</pre>
 *
 * <h2>Dependencies</h2>
 * <ul>
 *   <li>{@link boardgame.model.GameModel GameModel} - The main game model handling game logic and state.</li>
 *   <li>{@link javafx.fxml.FXML FXML} - An annotation used by JavaFX to identify controller methods and fields.</li>
 *   <li>{@link javafx.scene.control.Alert Alert} - A JavaFX class used for showing alert dialogs.</li>
 *   <li>{@link javafx.scene.control.Label Label} - A JavaFX class used for displaying text labels in the UI.</li>
 *   <li>{@link javafx.scene.control.TableView TableView} - A JavaFX class used for displaying tabular data.</li>
 *   <li>{@link javafx.scene.layout.GridPane GridPane} - A JavaFX layout class used for arranging UI components in a grid.</li>
 *   <li>{@link javafx.scene.layout.StackPane StackPane} - A JavaFX layout class used for stacking UI components.</li>
 *   <li>{@link org.tinylog.Logger Logger} - A logging library used for logging debug and error messages.</li>
 *   <li>{@link gameresult.TwoPlayerGameResult TwoPlayerGameResult} - A class representing the result of a two-player game.</li>
 *   <li>{@link gameresult.manager.TwoPlayerGameResultManager TwoPlayerGameResultManager} - A class used for managing game results.</li>
 *   <li>{@link gameresult.manager.json.JsonTwoPlayerGameResultManager JsonTwoPlayerGameResultManager} - A class for managing game results stored in JSON format.</li>
 * </ul>
 *
 * @see boardgame.controllers.GameController
 * @see boardgame.controllers.GameResultController
 * @see boardgame.controllers.OpeningScreenController
 * @see boardgame.model.GameModel
 * @see javafx.fxml.FXML
 * @see javafx.scene.control.Alert
 * @see javafx.scene.control.Label
 * @see javafx.scene.control.TableView
 * @see javafx.scene.layout.GridPane
 * @see javafx.scene.layout.StackPane
 * @see org.tinylog.Logger
 * @see gameresult.TwoPlayerGameResult
 * @see gameresult.manager.TwoPlayerGameResultManager
 * @see gameresult.manager.json.JsonTwoPlayerGameResultManager
 */
package boardgame.controllers;
