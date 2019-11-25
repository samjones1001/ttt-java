package ttt;

import ttt.console.Console;
import ttt.console.ConsoleIO;
import ttt.console.Menu;
import ttt.game.GameConfig;
import ttt.game.GameRunner;
import ttt.service.IOService;

class App {
    public static IOService consoleIO = new ConsoleIO();



    public static void main(String[] args) {
        Console console = new Console(consoleIO);
        GameRunner runner = new GameRunner(console);
        Menu menu = new Menu(console);
        GameConfig config = menu.start();

        runner.run(config);
    }
}
