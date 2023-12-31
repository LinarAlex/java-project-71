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
        String expected = resultStylish;
        String path1 = String.valueOf(getFilePath("step81.json"));
        String path2 = String.valueOf(getFilePath("step82.json"));
        assertEquals(expected, Differ.generate(path1, path2));
    }

    @Test
    public void test2() throws Exception {
        String expected = resultStylish;
        String path3 = String.valueOf(getFilePath("step81.yml"));
        String path4 = String.valueOf(getFilePath("step82.yml"));
        assertEquals(expected, Differ.generate(path3, path4));
    }

    @Test
    public void testStylish1() throws Exception {
        String expected = resultStylish;
        String path1 = String.valueOf(getFilePath("step81.json"));
        String path2 = String.valueOf(getFilePath("step82.json"));
        assertEquals(expected, Differ.generate(path1, path2, "stylish"));
    }

    @Test
    public void testStylish2() throws Exception {
        String expected = resultStylish;
        String path3 = String.valueOf(getFilePath("step81.yml"));
        String path4 = String.valueOf(getFilePath("step82.yml"));
        assertEquals(expected, Differ.generate(path3, path4, "stylish"));
    }

    @Test
    public void testPlain1() throws Exception {
        String expected = resultPlain;
        String path1 = String.valueOf(getFilePath("step81.json"));
        String path2 = String.valueOf(getFilePath("step82.json"));
        assertEquals(expected, Differ.generate(path1, path2, "plain"));
    }

    @Test
    public void testPlain2() throws Exception {
        String expected = resultPlain;
        String path3 = String.valueOf(getFilePath("step81.yml"));
        String path4 = String.valueOf(getFilePath("step82.yml"));
        assertEquals(expected, Differ.generate(path3, path4, "plain"));
    }

    @Test
    public void testJson1() throws Exception {
        String expected = resultJson;
        String path1 = String.valueOf(getFilePath("step81.json"));
        String path2 = String.valueOf(getFilePath("step82.json"));
        JSONAssert.assertEquals(expected, Differ.generate(path1, path2, "json"), false);
    }

    @Test
    public void testJson2() throws Exception {
        String expected = resultJson;
        String path3 = String.valueOf(getFilePath("step81.yml"));
        String path4 = String.valueOf(getFilePath("step82.yml"));
        JSONAssert.assertEquals(expected, Differ.generate(path3, path4, "json"), false);
    }
}
