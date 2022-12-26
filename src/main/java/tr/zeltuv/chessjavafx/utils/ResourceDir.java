package tr.zeltuv.chessjavafx.utils;

import javafx.scene.image.Image;
import tr.zeltuv.chessjavafx.Game;

import java.io.*;

public class ResourceDir {

    private String dir;

    public ResourceDir(String dir) {
        this.dir = dir;
    }

    public File getResource(String name) {
        File file = new File(name);

        try (
                InputStream inputStream = Game.class.getClassLoader().getResourceAsStream(dir
                        + "/" + name);
                OutputStream outputStream = new FileOutputStream(file);

        ) {
            inputStream.transferTo(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }


        return file;
    }

    public Image getImage(String name) {
        Image image;

        try (InputStream inputStream = Game.class.getClassLoader().getResourceAsStream(dir+"/"+name)) {

            image = new Image(inputStream);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return image;
    }
}
