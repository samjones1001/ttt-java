package ttt.game;

public class GameRunner {
    private Game game;

    public Game getGame() {
        return game;
    }

    public void run(GameConfig config) {
        game = new Game(config.getCurrentPlayer(), config.getOpponent(), config.getBoard());
    }
}
