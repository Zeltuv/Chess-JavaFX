package tr.zeltuv.chessjavafx.scene.impl;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import tr.zeltuv.chessjavafx.Game;
import tr.zeltuv.chessjavafx.node.impl.Button;
import tr.zeltuv.chessjavafx.scene.GameScene;
import tr.zeltuv.chessjavafx.utils.Util;

public class MainMenu extends GameScene {

    public MainMenu() {
        super("mainMenu");
    }

    private final Button button = new Button("Click To Play",
            (Game.WIDTH / 2) - 200,
            (Game.HEIGHT / 2) - 50,
            400,
            100,
            Color.rgb(77, 201, 156),
            Color.rgb(54, 145, 112),
            Color.rgb(197, 199, 198),
            new Font(40)
    );


    @Override
    public void display() {
    }

    @Override
    public void update() {
        button.update();
    }

    @Override
    public void click(MouseButton mouseButton) {
        if(Util.contains(
                Game.MOUSE_X,
                Game.MOUSE_Y,
                button.getX(),
                button.getY(),
                button.getWidth(),
                button.getHeight()
        )){
            Game.CURRENT_SCENE = new ChessScene();
        }
    }

    @Override
    public void render(GraphicsContext context) {
        context.setFill(Color.rgb(59, 59, 59));
        context.fillRect(0,0,Game.WIDTH,Game.HEIGHT);

        context.setFill(Color.rgb(219, 219, 219));
        context.setFont(new Font(40));
        context.setTextAlign(TextAlignment.CENTER);
        context.fillText("Chess game released by Zeltuv",Game.WIDTH/2,Game.HEIGHT/6);

        button.render(context);
    }
}
