package ttt.console;

import ttt.service.IOService;

import java.util.Scanner;

public class ConsoleIO implements IOService {

    private final Scanner in;

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
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
