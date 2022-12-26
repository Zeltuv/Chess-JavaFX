package tr.zeltuv.chessjavafx.node.impl;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import tr.zeltuv.chessjavafx.Game;
import tr.zeltuv.chessjavafx.node.GameNode;
import tr.zeltuv.chessjavafx.utils.Util;

public class Button implements GameNode {

    private int x;
    private int y;
    private int width;
    private int height;
    private Color buttonColor;
    private Color buttonHoverColor;
    private Color textColor;
    private Font font;
    private String text;
    private boolean hovered = false;

    public Button(String text, int x, int y, int width, int height, Color buttonColor, Color buttonHoverColor, Color textColor, Font font) {
        this.x = x;
        this.text = text;
        this.y = y;
        this.width = width;
        this.height = height;
        this.buttonColor = buttonColor;
        this.buttonHoverColor = buttonHoverColor;
        this.textColor = textColor;
        this.font = font;
    }

    public void setX(int x) {
        this.x = x;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Color getButtonColor() {
        return buttonColor;
    }

    public Color getButtonHoverColor() {
        return buttonHoverColor;
    }

    public Color getTextColor() {
        return textColor;
    }

    public Font getFont() {
        return font;
    }

    @Override
    public void update() {
        if (Util.contains(
                Game.MOUSE_X,
                Game.MOUSE_Y,
                x,
                y,
                width,
                height
        )) {
            hovered = true;
        }else{
            hovered = false;
        }
    }

    @Override
    public void render(GraphicsContext context) {
        if (hovered) {
            context.setFill(buttonHoverColor);
        } else {
            context.setFill(buttonColor);
        }

        context.fillRect(x, y, width, height);
        context.setFill(textColor);
        context.setFont(font);
        context.setTextAlign(TextAlignment.CENTER);
        context.fillText(text,
                x + ((double) width / 2),
                y + (((double) height / 2) + ((double) Util.fromPtToPixel(font.getSize()) / 3))
        );
    }

    public interface ButtonCallback {
        void execute();
    }
}
