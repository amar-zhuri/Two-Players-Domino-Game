package tests;

import boardgame.model.GameModel;
import boardgame.model.Players;
import boardgame.model.Position;
import game.State;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameModelTest {

    private GameModel gameModel;

    @BeforeEach
    public void setUp() {
        gameModel = new GameModel();
    }

    @Test
    public void testInitialBoardState() {
        for (int i = 0; i < GameModel.BOARD_SIZE; i++) {
            for (int j = 0; j < GameModel.BOARD_SIZE; j++) {
                assertEquals(Players.NONE, gameModel.getPlayer(i, j));
            }
        }
    }

    @Test
    public void testInitialCurrentPlayer() {
        assertEquals(State.Player.PLAYER_1, gameModel.getNextPlayer());
    }

    @Test
    public void testMakeLegalMovePlayer1() {
        gameModel.makeMove(new Position(0, 0));
        assertEquals(Players.PLAYER_1, gameModel.getPlayer(0, 0));
        assertEquals(Players.PLAYER_1, gameModel.getPlayer(0, 1));
        assertEquals(State.Player.PLAYER_2, gameModel.getNextPlayer());
        assertTrue(gameModel.wasLastMoveLegal());
    }

    @Test
    public void testMakeLegalMovePlayer2() {
        gameModel.makeMove(new Position(0, 0)); // Player 1 move
        gameModel.makeMove(new Position(1, 0)); // Player 2 move
        assertEquals(Players.PLAYER_2, gameModel.getPlayer(1, 0));
        assertEquals(Players.PLAYER_2, gameModel.getPlayer(2, 0));
        assertEquals(State.Player.PLAYER_1, gameModel.getNextPlayer());
        assertTrue(gameModel.wasLastMoveLegal());
    }

    @Test
    public void testMakeIllegalMovePlayer1() {
        gameModel.makeMove(new Position(0, 0));
        gameModel.makeMove(new Position(0, 0)); // Illegal move for Player 2
        assertFalse(gameModel.wasLastMoveLegal());
        assertEquals(State.Player.PLAYER_2, gameModel.getNextPlayer());
    }

    @Test
    public void testMakeIllegalMovePlayer2() {
        gameModel.makeMove(new Position(0, 0));
        gameModel.makeMove(new Position(1, 0));
        gameModel.makeMove(new Position(1, 0)); // Illegal move for Player 1
        assertFalse(gameModel.wasLastMoveLegal());
        assertEquals(State.Player.PLAYER_1, gameModel.getNextPlayer());
    }

    @Test
    public void testIsGameOver() {
        for (int i = 0; i < GameModel.BOARD_SIZE; i ++) {
            for (int j = 0; j < GameModel.BOARD_SIZE; j++) {
                gameModel.makeMove(new Position(i, j));
            }
        }
        assertTrue(gameModel.isGameOver());
    }

    @Test
    public void testGetStatusInProgress() {
        assertEquals(State.Status.IN_PROGRESS, gameModel.getStatus());
    }

    @Test
    public void testGetStatusPlayer1Wins() {
        for (int i = 0; i < GameModel.BOARD_SIZE; i += 2) {
            for (int j = 0; j < GameModel.BOARD_SIZE; j++) {
                gameModel.makeMove(new Position(i, j));
            }
        }
        assertNotEquals(State.Status.PLAYER_2_WINS, gameModel.getStatus());
    }

    @Test
    public void testIsWinner() {
        for (int i = 0; i < GameModel.BOARD_SIZE; i++) {
            for (int j = 0; j < GameModel.BOARD_SIZE; j++) {
                gameModel.makeMove(new Position(i, j));
            }
        }
        assertFalse(gameModel.isWinner(State.Player.PLAYER_2));
        assertTrue(gameModel.isWinner(State.Player.PLAYER_1));
    }

    @Test
    public void testGetNumberOfTurns() {
        gameModel.makeMove(new Position(0, 0));
        gameModel.makeMove(new Position(1, 0));
        assertEquals(2, gameModel.getNumberOfTurns());
    }


    @Test
    public void testToString() {
        String initialBoard =
                "  0 1 2 3 4 5 6 7 \n" +
                        "0 . . . . . . . . \n" +
                        "1 . . . . . . . . \n" +
                        "2 . . . . . . . . \n" +
                        "3 . . . . . . . . \n" +
                        "4 . . . . . . . . \n" +
                        "5 . . . . . . . . \n" +
                        "6 . . . . . . . . \n" +
                        "7 . . . . . . . . \n";
        assertEquals(initialBoard, gameModel.toString());

        gameModel.makeMove(new Position(0, 0));
        String afterMoveBoard =
                "  0 1 2 3 4 5 6 7 \n" +
                        "0 X X . . . . . . \n" +
                        "1 . . . . . . . . \n" +
                        "2 . . . . . . . . \n" +
                        "3 . . . . . . . . \n" +
                        "4 . . . . . . . . \n" +
                        "5 . . . . . . . . \n" +
                        "6 . . . . . . . . \n" +
                        "7 . . . . . . . . \n";
        assertEquals(afterMoveBoard, gameModel.toString());
    }

    @Test
    public void testPlayerProperty() {
        for (int i = 0; i < GameModel.BOARD_SIZE; i++) {
            for (int j = 0; j < GameModel.BOARD_SIZE; j++) {
                assertEquals(Players.NONE, gameModel.playerProperty(i, j).get());
            }
        }

        gameModel.makeMove(new Position(0, 0));
        assertEquals(Players.PLAYER_1, gameModel.playerProperty(0, 0).get());
        assertEquals(Players.PLAYER_1, gameModel.playerProperty(0, 1).get());
    }
}
