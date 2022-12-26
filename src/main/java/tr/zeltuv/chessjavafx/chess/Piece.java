package tr.zeltuv.chessjavafx.chess;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import tr.zeltuv.chessjavafx.Game;
import tr.zeltuv.chessjavafx.chess.team.Team;
import tr.zeltuv.chessjavafx.node.GameNode;
import tr.zeltuv.chessjavafx.scene.impl.ChessScene;

import java.util.ArrayList;
import java.util.List;

import static tr.zeltuv.chessjavafx.chess.ChessBoard.BLUE_SELECTED_TILE;

public abstract class Piece implements GameNode {

    private static int WIDTH = 100;
    private static int HEIGHT = 100;

    private ChessBoard chessBoard;
    private char id;
    private int x;
    private int y;
    private Image blackImage;
    private Image whiteImage;
    private Team team;
    private boolean canFly;
    private int[][] paths;

    private List<Piece> preys = new ArrayList<>();

    public Piece(ChessBoard chessBoard, char id, int x, int y, String blackImage,String whiteImage, Team team, boolean canFly) {
        this.chessBoard = chessBoard;
        this.id = id;
        this.x = x;
        this.y = y;
        this.whiteImage = Game.IMAGE_DIR.getImage(whiteImage);
        this.blackImage = Game.IMAGE_DIR.getImage(blackImage);
        this.team = team;
        this.canFly = canFly;
    }

    public boolean canFly() {
        return canFly;
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }

    public Team getTeam() {
        return team;
    }

    public char getId() {
        return id;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getBlackImage() {
        return blackImage;
    }

    public void setWhiteImage(Image whiteImage) {
        this.whiteImage = whiteImage;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public abstract List<Piece> getPreys();

    public abstract int[][] getDirections();

    public void move(int x, int y) {
        setX(x);
        setY(y);
    }


    public void recalculate(){
        paths = getDirections();
        preys = getPreys();
    }

    @Override
    public void update() {
    }

    @Override
    public void render(GraphicsContext context) {
        if(team == Team.WHITE) {
            context.drawImage(whiteImage, x * WIDTH, y * HEIGHT, WIDTH, HEIGHT);
        }else{
            context.drawImage(blackImage, x * WIDTH, y * HEIGHT, WIDTH, HEIGHT);
        }

        if (chessBoard.getSelected() == this) {
            context.setFill(BLUE_SELECTED_TILE);
            context.fillRect(getX() * WIDTH
                    , getY() * HEIGHT,
                    WIDTH, HEIGHT);
        }
    }
}
