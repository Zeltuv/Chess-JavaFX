package tr.zeltuv.chessjavafx.node;

import javafx.scene.canvas.GraphicsContext;

public interface GameNode {

    public void update();

    public void render(GraphicsContext context);
}
