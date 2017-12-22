package app.utils;

import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtil {
    public static Path getFilePath(String file){
        return Paths.get(FileUtil.class
                .getResource(file)
                .getPath()
                .replaceFirst("/",""));
    }

}
