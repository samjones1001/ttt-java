package acceptance;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

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
}