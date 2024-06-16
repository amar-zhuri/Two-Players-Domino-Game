# Two-Player Domino Board Game

## Project Description

The Two-Player Domino Board Game is an engaging strategy game designed for two players. This game is played on a board reminiscent of a chessboard and involves placing dominoes in a tactical manner. The objective of the game is to be the last player able to make a legal move. The game is implemented as a JavaFX application, providing both a graphical user interface (GUI) and a console-based version for gameplay.


### Game Rules

- The board is an 8x8 grid, similar to a chessboard.
- Each player takes turns placing a domino on the board. A domino occupies two squares.
- Player 1 always places their domino horizontally.
- Player 2 always places their domino vertically.
- A domino can be placed on only two adjacent empty squares.
- The player who cannot make a move loses the game.

### Objectives

The primary objective of the game is to strategically place dominoes on the board in such a way that forces the opponent into a position where they cannot make a legal move. The player who successfully prevents the other player from making a move wins the game.

### Features

- **User Interface**: The game includes a graphical user interface built with JavaFX, allowing for an intuitive and visually appealing user experience.
- **Console Version**: For those who prefer a more classic approach, a console version of the game is also available.
- **Game State Management**: The game effectively manages the state, ensuring that all moves are validated and the game progresses correctly.
- **Game Results**: The results of each game are saved and can be viewed within the application.

### Technical Overview

This project utilizes JavaFX for the GUI, ensuring a rich and interactive user experience. The game logic is encapsulated in the model, which maintains the state of the game, validates moves, and determines the game outcome. Controllers are used to manage user interactions and update the game state accordingly. The game results are managed and stored in a JSON file, making it easy to persist and retrieve past game outcomes.

## Game Structure

This project is a JavaFX application for a two-player domino board game. It includes a graphical user interface (GUI) and a console-based version for gameplay. The game consists of the following components:

- **Model**: Represents the game logic and state, handling the core functionality such as move validation and turn management.
- **Controllers**: Manage user interactions and update the game state. They are responsible for initializing the game board, handling user inputs, and updating the visual elements of the game.
- **View**: Defined using FXML files, it provides the layout and styling for the game's user interface.
- **Main Application**: Entry point for the JavaFX application.

## How to Run the Game

### Prerequisites

- Java Development Kit (JDK) 11 or higher
- JavaFX SDK

### Steps to Run

1. Clone the repository.
2. Open the project in your preferred Java IDE (e.g., IntelliJ IDEA, Eclipse).
3. Ensure that the JavaFX SDK is properly configured in your IDE.
4. Run the `Main` class located in the `boardgame` package to start the JavaFX application.

### Console Version

To run the console version of the game, execute the `ConsoleGame` class located in the `boardgame` package.

## Game Components

### Controllers

- **GameController**: Manages the main game screen, handles user interactions on the game board, and updates the game state.
- **GameResultController**: Manages the display of game results in a table view, loading results from a file and populating the view.
- **OpeningScreenController**: Manages the opening screen, capturing player names and initiating the game start process.

### Model

- **GameModel**: Represents the main game model, handling the game state, player turns, and move validation.
- **Players**: Enumeration representing the players in the game.

### View

The view components are defined in FXML files located in the `resources/views` directory:
- `OpeningScreen.fxml`
- `GameView.fxml`
- `GameResultView.fxml`

### Main Application

- **GameApplication**: Main entry point for the JavaFX application.
- **Main**: Launches the JavaFX application.

## Example Game Sequence

The following sequence of moves represents a game that ends after the last move:

```markdown
## An example game

1. PLAYER_1: 0 0
2. PLAYER_2: 2 1
3. PLAYER_1: 0 2
4. PLAYER_2: 1 1
5. PLAYER_1: 2 2
6. PLAYER_2: 4 3
7. PLAYER_1: 2 4
8. PLAYER_2: 3 3
9. PLAYER_1: 4 4
10. PLAYER_2: 6 5
11. PLAYER_1: 4 6
12. PLAYER_2: 5 5
13. PLAYER_1: 6 6
14. PLAYER_2: 7 7

PLAYER_1 wins
