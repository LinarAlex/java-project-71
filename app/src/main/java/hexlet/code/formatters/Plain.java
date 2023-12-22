package hexlet.code.formatters;

import java.util.Collection;
import java.util.List;
import java.util.Map;


public class Plain {
    public static String formatPlain(List<Map<String, Object>> differences) {
        StringBuilder result = new StringBuilder();
        for (Map<String, Object> diffs : differences) {
            switch (diffs.get("status").toString()) {
                case "removed" -> result.append("Property ").append("'")
                        .append(diffs.get("key")).append("'").append(" was removed").append("\n");
                case "added" -> result.append("Property ").append(complexValue(diffs.get("key")))
                        .append(" was added with value: ")
                        .append(complexValue(diffs.get("value")))
                        .append("\n");
                case "updated" ->
                        result.append("Property ").append(complexValue(diffs.get("key")))
                                .append(" was updated. From ")
                                .append(complexValue(diffs.get("value1"))).append(" to ")
                                .append(complexValue(diffs.get("value2")))
                                .append("\n");

                default -> result.append("");
            }

        }
        return result.toString().trim();

    }

    public static String complexValue(Object data) {
        if (data instanceof Object[] || data instanceof Collection || data instanceof Map) {
            return "[complex value]";
        } else if (data instanceof String) {
            return "'" + data + "'";
        } else if (data == null) {
            return null;
        }
        return data.toString();
    }
}
