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
    private static String file1;
    private static String file2;
    private static String file3;
    private static String file4;

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
        file1 = getFilePath("step81.json").toString();
        file2 = getFilePath("step82.json").toString();
        file3 = getFilePath("step81.yml").toString();
        file4 = getFilePath("step82.yml").toString();
    }

    @Test
    public void test1() throws Exception {
        assertEquals(resultStylish.trim(), Differ.generate(file1, file2));
    }

    @Test
    public void test2() throws Exception {
        assertEquals(resultStylish, Differ.generate(file3, file4));
    }

    @Test
    public void testStylish1() throws Exception {
        assertEquals(resultStylish.trim(), Differ.generate(file1, file2, "stylish"));
    }

    @Test
    public void testStylish2() throws Exception {
        assertEquals(resultStylish, Differ.generate(file3, file4, "stylish"));
    }

    @Test
    public void testPlain1() throws Exception {
        assertEquals(resultPlain, Differ.generate(file1, file2, "plain"));
    }

    @Test
    public void testPlain2() throws Exception {
        assertEquals(resultPlain, Differ.generate(file3, file4, "plain"));
    }

    @Test
    public void testJson1() throws Exception {
        JSONAssert.assertEquals(resultJson, Differ.generate(file1, file2, "json"), false);
    }

    @Test
    public void testJson2() throws Exception {
        JSONAssert.assertEquals(resultJson, Differ.generate(file3, file4, "json"), false);
    }
}
