package ttt.console;

import java.util.Scanner;

public class ConsoleIO implements IOService {

    private final Scanner in;

    private static final String clearScreen = "\033[H\033[2J";

    public ConsoleIO() {
        this.in = new Scanner(System.in);
    }

    @Override
    public String input() {
        return in.nextLine();
    }

    @Override
    public void output(String message) {
        System.out.println(message);
    }

    @Override
    public void clear() {
        System.out.print(clearScreen);
    }
}
