package ttt.messaging;

import java.util.Formatter;

public class MessageBuilder {
    public String buildMessage(String baseString, String... values) {
        StringBuilder builder = new StringBuilder();
        Formatter formatter = new Formatter(builder);
        formatter.format(baseString, values);
        return builder.toString();
    }
}
