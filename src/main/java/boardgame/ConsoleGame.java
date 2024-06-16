package boardgame;

import boardgame.model.GameModel;
import boardgame.model.Position;
import game.console.BasicGame;

import java.util.Scanner;

/**
 * The {@code ConsoleGame} class provides a console-based game for playing
 * the two-player game using the {@code GameModel} class.
 */
public class ConsoleGame {

    /**
     * The main method to start the console-based game.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        var state = new GameModel();
        var game = new BasicGame<>(state, ConsoleGame::parseMove);
        game.start();
    }

    /**
     * Converts a string containing the position of a move to a {@code Position}
     * object.
     *
     * @param s a string that should contain two space-separated integers
     * @return the {@code Position} object that was constructed from the string
     * @throws IllegalArgumentException if the format of the string is invalid,
     *                                  i.e., its content is not two integers separated with spaces
     */
    public static Position parseMove(String s) {
        s = s.trim();
        if (!s.matches("\\d+\\s+\\d+")) {
            throw new IllegalArgumentException();
        }
        var scanner = new Scanner(s);
        return new Position(scanner.nextInt(), scanner.nextInt());
    }
}
