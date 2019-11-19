package ttt.game;

import ttt.service.ClientService;

public class GameRunner {
    private Game game;
    private ClientService userInterface;

    public GameRunner(ClientService userInterface) {
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
