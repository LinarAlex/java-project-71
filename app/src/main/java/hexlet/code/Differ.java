package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class Differ {
    public static String generate(String filePath1, String filePath2) throws Exception {
        Map<String, Object> dataOfFile1 = getDataAndParse(filePath1);
        Map<String, Object> dataOfFile2 = getDataAndParse(filePath2);
        List<Map<String, Object>> difference = genDiff(dataOfFile1, dataOfFile2);
        return difference.toString();
    }

    public static String generate(String filePath1, String filePath2, String format) throws Exception {
        Map<String, Object> dataOfFile1 = getDataAndParse(filePath1);
        Map<String, Object> dataOfFile2 = getDataAndParse(filePath2);
        List<Map<String, Object>> difference = genDiff(dataOfFile1, dataOfFile2);
        return Formatter.formatStyle(difference, format);
    }

    private static  Map<String, Object> getDataAndParse(String filePath) throws Exception {
        var fullPath = Paths.get(filePath).toAbsolutePath().normalize();
        if (!Files.exists(fullPath)) {
            throw new Exception("File '" + fullPath + "' does not exist");
        }
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

    public static List<Map<String, Object>> genDiff(Map<String, Object> map1, Map<String, Object> map2) {
        List<Map<String, Object>> result = new ArrayList<>();
        Set<String> keysSet = new TreeSet<>(map1.keySet());
        keysSet.addAll(map2.keySet());
        for (String key :  keysSet) {
            Map<String, Object> map = new LinkedHashMap<>();
            if (map1.containsKey(key) && !map2.containsKey(key)) {
                map.put("key", key);
                map.put("oldValue", map1.get(key));
                map.put("status", "removed");
            } else if (!map1.containsKey(key) && map2.containsKey(key)) {
                map.put("key", key);
                map.put("newValue", map2.get(key));
                map.put("status", "added");
            } else if (!Objects.equals(map1.get(key), map2.get(key))) {
                map.put("key", key);
                map.put("oldValue", map1.get(key));
                map.put("newValue", map2.get(key));
                map.put("status", "updated");
            } else {
                map.put("key", key);
                map.put("oldValue", map1.get(key));
                map.put("status", "unchanged");
            }
            result.add(map);
        }
        return result;
    }
}
