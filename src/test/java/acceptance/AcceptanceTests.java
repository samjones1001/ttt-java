package acceptance;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@Tag("acceptance")
public class AcceptanceTests {
    @Test
    public void playsAGameUntilTheBoardIsFull() throws IOException {
        var processBuilder = new ProcessBuilder("java", "-jar", "./build/libs/ttt.jar");
        Process process = processBuilder.start();

        var input = new BufferedReader(new InputStreamReader(process.getInputStream()));
        var output = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
        var err = new BufferedReader(new InputStreamReader(process.getErrorStream()));



        output.write("1");
        output.newLine();
        output.write("2");
        output.newLine();
        output.write("3");
        output.newLine();
        output.write("4");
        output.newLine();
        output.write("5");
        output.newLine();
        output.write("6");
        output.newLine();
        output.write("7");
        output.newLine();
        output.write("8");
        output.newLine();
        output.write("9");
        output.newLine();
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