package ttt.console;

import ttt.game.Game;
import ttt.game.GameConfig;
import ttt.game.UserInterface;

public class ConsoleGameRunner {
    private Game game;
    private UserInterface userInterface;

    public ConsoleGameRunner(UserInterface userInterface) {
        this.userInterface = userInterface;
    }

    public Game getGame() {
        return game;
    }

    public void run(GameConfig config) {
        game = new Game(config.getCurrentPlayer(), config.getOpponent(), config.getBoard(), this.userInterface);
        while (!game.gameOver()) {
            game.playTurn();
        }
    }
}
