package hexlet.code;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.File;
import java.math.BigInteger;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.util.concurrent.Callable;

public class App {

    @Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 4.7.5",
            description = "Compares two configuration files and shows a difference.")
    static class CheckSum implements Callable<Integer> {

        @Parameters(index = "0", paramLabel = "filepath1", description = "path to first file")
        private File filepath1;
        @Parameters(index = "0", paramLabel = "filepath2", description = "path to second file")
        private File filepath2;
        @Option(names = {"-f", "--format"}, paramLabel = "format", description = "output format [default: stylish]")
        private String format = "stylish";

        @Override
        public Integer call() throws Exception { // your business logic goes here...
            byte[] fileContents = Files.readAllBytes(filepath1.toPath());
            byte[] fileContents2 = Files.readAllBytes((filepath2.toPath()));
            byte[] digest = MessageDigest.getInstance(format).digest(fileContents);
            byte[] digest2 = MessageDigest.getInstance(format).digest(fileContents2);
            System.out.printf("%0" + (digest.length * 2) + "x%n", new BigInteger(1, digest));
            System.out.printf("%0" + (digest2.length * 2) + "x%n", new BigInteger(1, digest2));
            return 0;
        }
    }

        // this example implements Callable, so parsing, error handling and handling user
        // requests for usage help or version help can be done with one line of code.
        public static void main(String[] args) {
            int exitCode = new CommandLine(new CheckSum()).execute(args);
            System.exit(exitCode);
        }

}
