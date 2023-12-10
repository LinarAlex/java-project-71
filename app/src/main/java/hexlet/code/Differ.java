package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.List;

public class Differ {
    public static String generate(String filePath1, String filePath2) throws Exception {
        return generate(filePath1, filePath2, "stylish");
    }

    public static String generate(String filePath1, String filePath2, String format) throws Exception {
        Map<String, Object> dataOfFile1 = getDataAndParse(filePath1);
        Map<String, Object> dataOfFile2 = getDataAndParse(filePath2);
        List<Map<String, Object>> difference = GenDifference.genDiff(dataOfFile1, dataOfFile2);
        return Formatter.formatStyle(difference, format);
    }

    private static  Map<String, Object> getDataAndParse(String filePath) throws Exception {
        var fullPath = Paths.get(filePath).toAbsolutePath().normalize();
        String content = Files.readString(fullPath);
        String dataFormat = getDataFormat(filePath);
        return Parser.parse(content, dataFormat);
    }

    private static String getDataFormat(String filePath) {
        int index = filePath.lastIndexOf('.');
        return index > 0
                ? filePath.substring(index + 1)
                : "";
    }
}
