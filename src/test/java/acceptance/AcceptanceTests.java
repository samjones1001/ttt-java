package acceptance;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@Tag("acceptance")
public class AcceptanceTests {
    @Test
    public void printsToScreen() throws IOException {
        var processBuilder = new ProcessBuilder("java", "-jar", "./build/libs/ttt.jar");
        Process process = processBuilder.start();

        var input = new BufferedReader(new InputStreamReader(process.getInputStream()));
        var err = new BufferedReader(new InputStreamReader(process.getErrorStream()));

        assertEquals("fjlkdjfksldjf", input.readLine());
        assertNull(err.readLine());
    }
}