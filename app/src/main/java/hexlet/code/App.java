package hexlet.code;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import java.util.concurrent.Callable;

public class App {

    @Command(name = "gendiff", mixinStandardHelpOptions = true, version = "checksum 4.0",
            description = "Compares two configuration files and shows a difference.")
    static class CheckSum implements Callable<Integer> {
        @Override
        public Integer call() {
            return 0;
        }
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new CheckSum()).execute(args);
        System.exit(exitCode);
    }
}
