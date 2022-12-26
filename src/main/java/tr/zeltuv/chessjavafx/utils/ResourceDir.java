package tr.zeltuv.chessjavafx.utils;

import javafx.scene.image.Image;

import java.io.*;

public class ResourceDir {

    private String dir;

    public ResourceDir(String dir) {
        this.dir = dir;
    }

    public File getResource(String name){
        return new File(dir,name);
    }
    public Image getImage(String name){
        File file = new File(dir,name);

        Image image;

        try (InputStream inputStream = new FileInputStream(file)){

            image = new Image(inputStream);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return image;
    }
}
