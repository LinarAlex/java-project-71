package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;


import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Differ {
    public static String generate(String filePath1, String filePath2) throws Exception {
        Path path = Paths.get(filePath1).toAbsolutePath().normalize();
        Path path2 = Paths.get(filePath2).toAbsolutePath().normalize();
        String content = Files.readString(path);
        String content2 = Files.readString(path2);
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> mapFirst = objectMapper.readValue(content, new TypeReference<>() {
        });
        Map<String, Object> mapSecond = objectMapper.readValue(content2, new TypeReference<>() {
        });
        Set<String> set = new TreeSet<>();
        set.addAll(mapFirst.keySet());
        set.addAll(mapSecond.keySet());
        StringBuilder result = new StringBuilder();
        result.append("{\n");
        for (String key : set) {
            if (mapFirst.containsKey(key) & mapSecond.containsKey(key)) {
                if (mapFirst.get(key).equals(mapSecond.get(key))) {
                    result.append("    ").append(key).append(": ").append(mapFirst.get(key)).append("\n");
                } else {
                    result.append("  ").append("- ").append(key).append(": ").append(mapFirst.get(key)).append("\n");
                    result.append("  ").append("+ ").append(key).append(": ").append(mapSecond.get(key)).append("\n");
                }
            } else {
                if (mapFirst.containsKey(key)) {
                    result.append("  ").append("- ").append(key).append(": ").append(mapFirst.get(key)).append("\n");
                } else {
                    if (mapSecond.containsKey(key)) {
                        result.append("  ").append("+ ").append(key).append(": ").append(mapSecond.get(key)).append("\n");
                    }
                }
            }
        }
        result.append("}");
        return result.toString();
    }
}
