package ttt;

import ttt.console.Console;
import ttt.console.ConsoleIO;
import ttt.game.Board;
import ttt.game.GameConfig;
import ttt.game.GameRunner;
import ttt.player.Player;

class App {
    public static void main(String[] args) {
        Console console = new Console(new ConsoleIO(System.in));
        Player player1 = new Player("Player 1", "X", console);
        Player player2 = new Player("Player 2", "O", console);
        Board board = new Board();
        GameConfig config = new GameConfig(player1, player2, board);
        GameRunner runner = new GameRunner(console);

        runner.run(config);
    }
}
