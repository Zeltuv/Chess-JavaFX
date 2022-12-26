package tr.zeltuv.chessjavafx.utils;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Util {

    public static boolean contains(double mouseX, double mouseY,
                                   double x, double y,
                                   double width, double height) {
        if (mouseX > x && mouseX < x + width
                && mouseY > y && mouseY < y + height
        )
            return true;

        return false;
    }

    public static double fromPtToPixel(double pt){
        return pt*1.333;
    }

    public static List<String> fromFileToLine(File file){
        try (
            Scanner scanner = new Scanner(file)){

            List<String> strings = new ArrayList<>();

            while (scanner.hasNext()){
                strings.add(scanner.next());
            }

            return strings;
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
