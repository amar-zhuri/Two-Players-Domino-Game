package boardgame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The {@code GameApplication} class is the main entry point for the JavaFX board game application.
 */
public class GameApplication extends Application {
    /**
     * Starts the JavaFX application by setting up the primary stage.
     *
     * @param stage the primary stage for this application, onto which
     *              the application scene can be set
     * @throws IOException if the {@code OpeningScreen.fxml} file cannot be loaded
     */
    @Override
    public void start(Stage stage) throws IOException {
        // Load the FXML file for the opening screen
        Parent root = FXMLLoader.load(getClass().getResource("/views/OpeningScreen.fxml"));

        // Set the title of the primary stage
        stage.setTitle("JavaFX 2-Player Domino-Board Game");

        // Create and set the scene with the loaded FXML layout
        Scene scene = new Scene(root);
        stage.setScene(scene);

        // Configure the stage to be non-resizable
        stage.setResizable(false);

        // Show the primary stage
        stage.show();
    }
}
