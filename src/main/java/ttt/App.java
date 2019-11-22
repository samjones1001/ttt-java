package ttt;

import ttt.console.Console;
import ttt.console.ConsoleIO;
import ttt.game.Board;
import ttt.game.GameConfig;
import ttt.game.GameRunner;
import ttt.player.Player;
import ttt.service.IOService;

class App {
    public static IOService consoleIO = new ConsoleIO();

    private static final String playerOneName = "Player 1";
    private static final String playerOneMarker = "X";
    private static final String playerTwoName = "Player 2";
    private static final String playerTwoMarker = "O";

    public static void main(String[] args) {
        Console console = new Console(consoleIO);
        GameRunner runner = new GameRunner(console);

        Player player1 = new Player(playerOneName, playerOneMarker, console);
        Player player2 = new Player(playerTwoName, playerTwoMarker, console);

        Board board = new Board();
        GameConfig config = new GameConfig(player1, player2, board);

        runner.run(config);
    }
}
