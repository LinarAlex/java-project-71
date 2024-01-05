package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {
    private static String resultJson;
    private static String resultPlain;
    private static String resultStylish;
    private static final String FILE1 = getFilePath("step81.json").toString();
    private static final String FILE2 = getFilePath("step82.json").toString();
    private static final String FILE3 = getFilePath("step81.yml").toString();
    private static final String FILE4 = getFilePath("step82.yml").toString();

    private static Path getFilePath(String fileName) {
        return Paths.get("src", "test", "resources", fileName)
                .toAbsolutePath().normalize();
    }

    private static String getResult(String fileName) throws IOException {
        return Files.readString(getFilePath(fileName));
    }

    @BeforeAll
    public static void generateResult() throws IOException {
        resultJson = getResult("testJson.json");
        resultPlain = getResult("testPlain");
        resultStylish  = getResult("testStylish");
    }

    @Test
    public void test1() throws Exception {
        assertEquals(resultStylish.trim(), Differ.generate(FILE1, FILE2));
    }

    @Test
    public void test2() throws Exception {
        assertEquals(resultStylish, Differ.generate(FILE3, FILE4));
    }

    @Test
    public void testStylish1() throws Exception {
        assertEquals(resultStylish.trim(), Differ.generate(FILE1, FILE2, "stylish"));
    }

    @Test
    public void testStylish2() throws Exception {
        assertEquals(resultStylish, Differ.generate(FILE3, FILE4, "stylish"));
    }

    @Test
    public void testPlain1() throws Exception {
        assertEquals(resultPlain, Differ.generate(FILE1, FILE2, "plain"));
    }

    @Test
    public void testPlain2() throws Exception {
        assertEquals(resultPlain, Differ.generate(FILE3, FILE4, "plain"));
    }

    @Test
    public void testJson1() throws Exception {
        JSONAssert.assertEquals(resultJson, Differ.generate(FILE1, FILE2, "json"), false);
    }

    @Test
    public void testJson2() throws Exception {
        JSONAssert.assertEquals(resultJson, Differ.generate(FILE3, FILE4, "json"), false);
    }
}
