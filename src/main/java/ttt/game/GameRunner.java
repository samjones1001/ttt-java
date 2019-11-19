package ttt.game;

import ttt.console.Console;

public class GameRunner {
    private Game game;
    private Console console;

    public GameRunner(Console console) {
        this.console = console;
    }

    public Game getGame() {
        return game;
    }

    public void run(GameConfig config) {
        game = new Game(config.getCurrentPlayer(), config.getOpponent(), config.getBoard(), this.console);
        while (!game.gameOver()) {
            game.playTurn();
        }
    }
}
