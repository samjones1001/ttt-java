package ttt.game.messaging;

import java.util.Formatter;

public class MessageBuilder {
    public static String buildMessage(String baseString, String... values) {
        StringBuilder builder = new StringBuilder();
        Formatter formatter = new Formatter(builder);
        formatter.format(baseString, values);
        return builder.toString();
    }
}
