package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Differ {
    public static String generate(String filePath1, String filePath2) throws Exception {
        Map<String, Object> dataOfFile1 = getDataAndParse(filePath1);
        Map<String, Object> dataOfFile2 = getDataAndParse(filePath2);
        Map<String, Object> difference = genDiff(dataOfFile1, dataOfFile2);
        return genToString(difference);
    }

    private static  Map<String, Object> getDataAndParse(String filePath) throws Exception {
        var fullPath = Paths.get(filePath).toAbsolutePath().normalize();
        if (!Files.exists(fullPath)) {
            throw new Exception("File '" + fullPath + "' does not exist");
        }
        String content = Files.readString(fullPath);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(content, new TypeReference<>() {
        });
    }

    public static Map<String, Object> genDiff(Map<String, Object> data1, Map<String, Object> data2) {
        Map<String, Object> result = new LinkedHashMap<>();
        Set<String> keys = new TreeSet<>(data1.keySet());
        keys.addAll(data2.keySet());

        for (String key : keys) {
            if (!data1.containsKey(key)) {
                var addedValue = data2.get(key);
                String addedKey = "  + " + key;
                result.put(addedKey, addedValue);
            } else if (!data2.containsKey(key)) {
                var deletedValue = data1.get(key);
                String deletedKey = "  - " + key;
                result.put(deletedKey, deletedValue);
            } else if (data1.containsKey(key) && data2.containsKey(key)) {
                if (!data1.get(key).equals(data2.get(key))) {
                    String changedKey1 = "  - " + key;
                    String changedKey2 = "  + " + key;
                    result.put(changedKey1, data1.get(key));
                    result.put(changedKey2, data2.get(key));
                } else {
                    String sameKey = "    " + key;
                    result.put(sameKey, data1.get(key));
                }
            }
        }
        return result;
    }

    public static String genToString(Map<String, Object> diffData) {
        StringBuilder result = new StringBuilder("{\n");
        for (Map.Entry<String, Object> entry : diffData.entrySet()) {
            result.append(entry.getKey()).append(": ").append(entry.getValue());
            result.append(("\n"));
        }
        result.append("}");
        return result.toString();
    }
}
