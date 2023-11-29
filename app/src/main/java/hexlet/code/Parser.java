package hexlet.code;

import java.util.Map;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

public class Parser {
    private static ObjectMapper checkFormat(String dataFormat) throws Exception {
        return "json".equals(dataFormat) ? new ObjectMapper() : new ObjectMapper(new YAMLFactory());
    }

    public static Map<String, Object> parse(String content, String dataFormat) throws Exception {
        ObjectMapper objectMapper = checkFormat(dataFormat);
        return objectMapper.readValue(content, new TypeReference<>() { });
    }
}
