package tr.zeltuv.chessjavafx.utils;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ResourceDirSaver {
    private String dir;

    private ClassLoader classLoader;

    public ResourceDirSaver(String dir, ClassLoader classLoader) {
        this.dir = dir;
        this.classLoader = classLoader;
    }

    public void saveIfNotExist() {
        File folder = new File(dir);
        folder.mkdirs();

        for (String s : getFilesName()) {
            File potentialFile = new File(dir,s);

            if(potentialFile.exists())
                continue;

            saveFile(s);
        }
    }

    private void saveFile(String name) {
        File file = new File(dir, name);

        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (InputStream inputStream = classLoader.getResourceAsStream(dir + "/" + name);
                OutputStream outputStream = new FileOutputStream(file)) {
            inputStream.transferTo(outputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<String> getFilesName() {
        URL url = classLoader.getResource(dir);

        try {
            Path dirPath = Paths.get(url.toURI());
            Stream<Path> pathStream = Files.list(dirPath);
            return pathStream
                    .map(p -> p.getFileName().toString())
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
