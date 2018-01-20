package app.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.stream.Stream;

public class FileUtil {
    private static Path getFilePath(String file){
        return Paths.get(FileUtil.class
                .getResource(file)
                .getPath()
                .replaceFirst("/",""));
    }

    public static Stream<String> getLines(String file) throws IOException {
        return Optional.ofNullable(
                    Files.lines(
                            getFilePath(file)))
                    .orElse(Stream.empty());
    }
}
