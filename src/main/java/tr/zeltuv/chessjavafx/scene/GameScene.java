package tr.zeltuv.chessjavafx.scene;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseButton;
import tr.zeltuv.chessjavafx.node.GameNode;

import java.util.ArrayList;
import java.util.List;

public abstract class GameScene {

    private String id;

    private List<GameNode> gameNodeList = new ArrayList<>();

    public GameScene(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public abstract void display();

    public abstract void update();

    public abstract void render(GraphicsContext context);

    public abstract void click(MouseButton mouseButton);
}
