package ttt.console;

import ttt.service.IOService;

import java.io.InputStream;
import java.util.Scanner;

public class ConsoleIO implements IOService {

    private final Scanner in;

    public ConsoleIO() {
        this(System.in);
    }

    public ConsoleIO(InputStream in) {
        this.in = new Scanner(in);
    }

    @Override
    public String input() {
        return in.nextLine();
    }

    @Override
    public void output(String message) {
        System.out.println(message);
    }
}
