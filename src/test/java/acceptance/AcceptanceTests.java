package acceptance;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ttt.console.Console;
import ttt.game.Board;
import ttt.game.Game;
import ttt.mocks.MockConsoleIO;
import ttt.player.Player;
import ttt.player.UnbeatablePlayer;
import ttt.service.ClientService;
import ttt.service.PlayerService;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

@Tag("acceptance")
public class AcceptanceTests {
    @Test
    public void canPlayAGameUntilTheBoardIsFull() throws IOException {
        var processBuilder = new ProcessBuilder("java", "-jar", "./build/libs/ttt.jar");
        Process process = processBuilder.start();

        var input = new BufferedReader(new InputStreamReader(process.getInputStream()));
        var output = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
        var err = new BufferedReader(new InputStreamReader(process.getErrorStream()));

        var inputs = new String[] {"1", "2", "3", "4", "5", "7", "6", "9", "8"};
        for (int index = 0; index <= inputs.length - 1; index++) {
            output.write(inputs[index]);
            output.newLine();
        }
        output.close();

        String lastLine = "";
        String currentLine;

        while ((currentLine = input.readLine()) != null) {
            lastLine = currentLine;
        }

        input.close();

        assertEquals("Game Over - It's a Tie!", lastLine);
        assertNull(err.readLine());
    }

    @Test
    public void ignoresInvalidAndUnavailableInputs() throws IOException {
        var processBuilder = new ProcessBuilder("java", "-jar", "./build/libs/ttt.jar");
        Process process = processBuilder.start();

        var input = new BufferedReader(new InputStreamReader(process.getInputStream()));
        var output = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
        var err = new BufferedReader(new InputStreamReader(process.getErrorStream()));

        var inputs = new String[] {"-1", "0", "10000", "Not Valid", "1", "1", "1", "2", "3", "4", "5", "7", "6", "9", "8"};
        for (int index = 0; index <= inputs.length - 1; index++) {
            output.write(inputs[index]);
            output.newLine();
        }
        output.close();

        String lastLine = "";
        String currentLine;

        while ((currentLine = input.readLine()) != null) {
            lastLine = currentLine;
        }

        input.close();

        assertEquals("Game Over - It's a Tie!", lastLine);
        assertNull(err.readLine());
    }

    @Test
    public void aGameCanBeWon() throws IOException {
        var processBuilder = new ProcessBuilder("java", "-jar", "./build/libs/ttt.jar");
        Process process = processBuilder.start();

        var input = new BufferedReader(new InputStreamReader(process.getInputStream()));
        var output = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
        var err = new BufferedReader(new InputStreamReader(process.getErrorStream()));

        var inputs = new String[] {"1", "2", "3", "4", "5", "6", "7"};
        for (int index = 0; index <= inputs.length - 1; index++) {
            output.write(inputs[index]);
            output.newLine();
        }
        output.close();

        String lastLine = "";
        String currentLine;

        while ((currentLine = input.readLine()) != null) {
            lastLine = currentLine;
        }

        input.close();

        assertEquals("Game Over - Player 1 Won!", lastLine);
        assertNull(err.readLine());
    }

    @Test
    void takesNoLongerThanASecondForAnUnbeatablePlayerToMakeAMoveOnAnEmpty3x3Grid() {
        MockConsoleIO mockConsoleIO = new MockConsoleIO();
        ClientService console = new Console(mockConsoleIO);
        PlayerService playerOne = new UnbeatablePlayer("Player 1", "X");
        PlayerService playerTwo = new UnbeatablePlayer("Player 2", "O");
        Game game = new Game(playerOne, playerTwo, new Board(), console);

        long startTime = System.currentTimeMillis();
        playerOne.getMove(game);
        long endTime = System.currentTimeMillis();

        assertTrue((endTime - startTime) < 1000000);
    }
}