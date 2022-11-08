package exercise;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.ExecutionException;

class App {

    // BEGIN
    public static final String filesDir = "src/main/resources/";

    public static CompletableFuture<String> unionFiles(
            String source1,
            String source2,
            String destination
    ) throws ExecutionException, InterruptedException {
        CompletableFuture<String> text1 = readFile(source1);
        CompletableFuture<String> text2 = readFile(source2);
        text1.get();
        text2.get();

        CompletableFuture<String> result = text1.thenCombine(text2, (x, y) -> {
            Path pathToResult = Paths.get(destination).toAbsolutePath().normalize();
            String combinedText;
            try {
                if (!Files.exists(pathToResult)) {
                    Files.createFile(pathToResult);
                }
                Files.writeString(pathToResult, x, StandardOpenOption.TRUNCATE_EXISTING);
                Files.writeString(pathToResult, y, StandardOpenOption.APPEND);
                combinedText = Files.readString(pathToResult);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return combinedText;
        });
        return result;
    }

    public static CompletableFuture<String> readFile(String source) {
        return CompletableFuture.supplyAsync(() ->
        {
            String result = "";
            try {
                result = Files.readString(Paths.get(source).toAbsolutePath().normalize());
            } catch (IOException e) {
                System.out.println(e);
            }
            return result;
        });
    }

    public static String getPath(String fileName) {
        return filesDir + fileName;
    }
    // END

    public static void main(String[] args) throws Exception {
        // BEGIN
        CompletableFuture<String> res = unionFiles(getPath("file1.txt"),
                getPath("file2.txt"),
                getPath("result.txt"));
        // END
    }
}

