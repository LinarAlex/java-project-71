package hexlet.code;

import java.util.List;
import java.util.Map;
import hexlet.code.formatters.Json;
import hexlet.code.formatters.Stylish;
import hexlet.code.formatters.Plain;


public class Formatter {
    public static String formatStyle(List<Map<String, Object>> difference, String format) throws Exception {
        switch (format) {
            case "stylish":
                return Stylish.formatStylish(difference);
            case "plain":
                return Plain.formatPlain(difference);
            case "json":
                return Json.formatJson(difference);
            default:
                System.out.println("Format " + format + " is not correct");
        }
        return Stylish.formatStylish(difference);
    }
}
