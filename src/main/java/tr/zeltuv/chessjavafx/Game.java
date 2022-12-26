package tr.zeltuv.chessjavafx;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import tr.zeltuv.chessjavafx.scene.GameScene;
import tr.zeltuv.chessjavafx.scene.impl.MainMenu;
import tr.zeltuv.chessjavafx.utils.ResourceDir;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Game extends Application {

    public static final int HEIGHT = 8 * 100;
    public static final int WIDTH = 8 * 100;
    public static GameScene CURRENT_SCENE;
    public static List<KeyCode> PRESSED_KEY = new ArrayList<>();
    public static double MOUSE_X = 0;
    public static double MOUSE_Y = 0;
    public static ResourceDir IMAGE_DIR = new ResourceDir("image");
    public static ResourceDir CONFIG_DIR = new ResourceDir("config");

    @Override
    public void start(Stage stage) {

        Canvas canvas = new Canvas(WIDTH, HEIGHT);

        Scene scene = new Scene(new StackPane(canvas), WIDTH, HEIGHT);

        GraphicsContext gc = canvas.getGraphicsContext2D();

        CURRENT_SCENE = new MainMenu();

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(10), event -> {
            update();
            render(gc);
        }));
        timeline.setCycleCount(Animation.INDEFINITE);

        timeline.play();

        stage.setTitle("Chess");
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);

        scene.setOnMouseClicked(event -> {
            CURRENT_SCENE.click(event.getButton());
        });

        scene.setOnMouseMoved(event -> {
            MOUSE_X = event.getSceneX();
            MOUSE_Y = event.getSceneY();
        });

        scene.setOnKeyPressed(event -> {
            if (!PRESSED_KEY.contains(event.getCode()))
                PRESSED_KEY.add(event.getCode());
        });

        scene.setOnKeyReleased(event -> PRESSED_KEY.remove(event.getCode()));
    }



    public static void main(String[] args) {
        launch();
    }

    public void update() {
        CURRENT_SCENE.update();
    }

    public void render(GraphicsContext context) {
        CURRENT_SCENE.render(context);
    }
}