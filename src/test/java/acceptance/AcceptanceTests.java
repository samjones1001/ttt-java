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

        for (int index = 1; index <= 9; index++) {
            output.write(Integer.toString(index));
            output.newLine();
        }
        output.close();

        String lastLine = "";
        String currentLine;

        while ((currentLine = input.readLine()) != null) {
            lastLine = currentLine;
        }

        input.close();

        assertEquals("Game Over", lastLine);
        assertNull(err.readLine());
    }

    @Test
    public void ignoresInvalidInputs() throws IOException {
        var processBuilder = new ProcessBuilder("java", "-jar", "./build/libs/ttt.jar");
        Process process = processBuilder.start();

        var input = new BufferedReader(new InputStreamReader(process.getInputStream()));
        var output = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
        var err = new BufferedReader(new InputStreamReader(process.getErrorStream()));

        var inputs = new String[] {"-1", "0", "10000", "Not Valid", "1", "1", "1", "2", "3", "4", "5", "6", "7", "8", "9"};

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

        assertEquals("Game Over", lastLine);
        assertNull(err.readLine());
    }
}