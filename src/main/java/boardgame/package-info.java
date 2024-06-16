/**
 * Provides the main entry points and configuration for the two-player board game application.
 * <p>
 * This package contains classes that serve as entry points for both the console and JavaFX
 * versions of the game. It includes the main application classes, setup configurations, and
 * necessary utility functions for starting the game.
 * </p>
 *
 * <h2>Package Overview</h2>
 * <p>
 * The {@code boardgame} package is designed to initialize and start the two-player board game
 * application, whether it's run as a console application or a JavaFX graphical application.
 * The key classes included in this package are:
 * </p>
 *
 * <ul>
 *   <li>{@link boardgame.ConsoleGame ConsoleGame} - Represents the console-based version of the game, handling user inputs via the console.</li>
 *   <li>{@link boardgame.GameApplication GameApplication} - The main entry point for the JavaFX-based graphical version of the game.</li>
 *   <li>{@link boardgame.Main Main} - The main entry point that launches the JavaFX application.</li>
 * </ul>
 *
 * <h2>Class Details</h2>
 *
 * <h3>ConsoleGame</h3>
 * <p>
 * The {@link boardgame.ConsoleGame ConsoleGame} class represents the console version of the game. It extends
 * the {@link game.console.Game Game} class and handles the game logic, user inputs, and state updates via the console.
 * </p>
 *
 * <h3>GameApplication</h3>
 * <p>
 * The {@link boardgame.GameApplication GameApplication} class is the main entry point for the JavaFX version of the game.
 * It sets up the primary stage and loads the opening screen FXML file.
 * </p>
 *
 * <h3>Main</h3>
 * <p>
 * The {@link boardgame.Main Main} class serves as the main entry point for launching the JavaFX application.
 * It calls the {@link javafx.application.Application#launch(Class, String...)} method to start the JavaFX runtime.
 * </p>
 *
 * <h2>Example Usage</h2>
 * <pre>{@code
 * // Starting the console version of the game
 * ConsoleGame.main(args);
 *
 * // Starting the JavaFX version of the game
 * Main.main(args);
 * }</pre>
 *
 * <h2>Dependencies</h2>
 * <ul>
 *   <li>{@link boardgame.model.GameModel GameModel} - The main game model handling game logic and state.</li>
 *   <li>{@link javafx.application.Application Application} - The base class for JavaFX applications.</li>
 *   <li>{@link javafx.fxml.FXMLLoader FXMLLoader} - A class used to load FXML files for defining the UI layout.</li>
 *   <li>{@link javafx.scene.Parent Parent} - The base class for all nodes that have children in the scene graph.</li>
 *   <li>{@link javafx.scene.Scene Scene} - The container for all content in a scene graph.</li>
 *   <li>{@link javafx.stage.Stage Stage} - The top-level container for a JavaFX application.</li>
 * </ul>
 *
 * @see boardgame.ConsoleGame
 * @see boardgame.GameApplication
 * @see boardgame.Main
 * @see boardgame.model.GameModel
 * @see javafx.application.Application
 * @see javafx.fxml.FXMLLoader
 * @see javafx.scene.Parent
 * @see javafx.scene.Scene
 * @see javafx.stage.Stage
 */
package boardgame;
