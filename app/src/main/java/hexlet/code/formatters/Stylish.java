package hexlet.code.formatters;

import java.util.Map;
import java.util.List;
public class Stylish {
    public static String formatStylish(List<Map<String, Object>> difference) {
        StringBuilder result = new StringBuilder("{\n");
        for (Map<String, Object> diffs : difference) {
            switch (diffs.get("status").toString()) {
                case "removed" -> result.append("  - ").append(diffs.get("key")).append(": ")
                        .append(diffs.get("oldValue")).append("\n");
                case "added" -> result.append("  + ").append(diffs.get("key")).append(": ")
                        .append(diffs.get("newValue")).append("\n");
                case "unchanged" -> result.append("    ").append(diffs.get("key")).append(": ")
                        .append(diffs.get("oldValue")).append("\n");
                default -> {
                    result.append("  - ").append(diffs.get("key")).append(": ")
                            .append(diffs.get("oldValue")).append("\n");
                    result.append("  + ").append(diffs.get("key")).append(": ")
                            .append(diffs.get("newValue")).append("\n");
                }
            }
        }
        result.append("}");
        return result.toString();
    }
}
