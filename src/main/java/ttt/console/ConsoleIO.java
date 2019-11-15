package ttt.console;

import ttt.service.IOService;

import java.util.Scanner;

public class ConsoleIO implements IOService {
    @Override
    public String input() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    @Override
    public void output(String message) {
        System.out.println(message);
    }
}
