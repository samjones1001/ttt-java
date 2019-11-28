package ttt;

import ttt.console.Console;
import ttt.console.ConsoleIO;
import ttt.console.Menu;
import ttt.game.GameConfig;
import ttt.console.ConsoleGameRunner;
import ttt.console.IOService;
import ttt.game.UserInterface;

class App {
    public static IOService consoleIO = new ConsoleIO();

    public static void main(String[] args) {
        UserInterface console = new Console(consoleIO);
        ConsoleGameRunner runner = new ConsoleGameRunner(console);
        Menu menu = new Menu(console);
        GameConfig config = menu.start();

        runner.run(config);
    }
}
