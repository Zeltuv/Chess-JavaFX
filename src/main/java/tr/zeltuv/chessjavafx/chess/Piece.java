package tr.zeltuv.chessjavafx.chess;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import tr.zeltuv.chessjavafx.Game;
import tr.zeltuv.chessjavafx.chess.team.Team;
import tr.zeltuv.chessjavafx.node.GameNode;

import java.util.ArrayList;
import java.util.List;

import static tr.zeltuv.chessjavafx.chess.ChessBoard.ATTACK_TILE;
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
    private int[][] paths;

    private List<Piece> preys = new ArrayList<>();

    public Piece(ChessBoard chessBoard, char id, int x, int y, String blackImage, String whiteImage, Team team) {
        this.chessBoard = chessBoard;
        this.id = id;
        this.x = x;
        this.y = y;
        this.whiteImage = Game.IMAGE_DIR.getImage(whiteImage);
        this.blackImage = Game.IMAGE_DIR.getImage(blackImage);
        this.team = team;
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

    public int[][] getPaths() {
        return paths;
    }

    public List<Piece> getPreys() {
        return preys;
    }

    public int[][] getPathToRender() {
        List<int[]> pathList = new ArrayList<>();

        for (int i = 0; i < getPaths().length; i++) {
            int x = getPaths()[i][0];
            int y = getPaths()[i][1];

            boolean hasPrey = false;

            for (Piece prey : getPreys()) {
                if (
                        (prey.getX() == x)
                                && (prey.getY() == y)
                )
                    hasPrey = true;
            }

            if (!hasPrey) {
                pathList.add(new int[]{x, y});
            }

        }

        int[][] path = new int[pathList.size()][];

        for (int i = 0; i < pathList.size(); i++) {
            path[i] = pathList.get(i);
        }

        return path;
    }

    public abstract List<Piece> calculatePreys();

    public abstract int[][] calculatePaths();

    public boolean move(int x, int y) {
        boolean isInPath = false;
        Piece prey = null;

        for (int[] path : getPaths()) {
            int xPath = path[0];
            int yPath = path[1];

            if ((x == xPath) && (y == yPath)) {
                isInPath = true;
                break;
            }
        }

        for (Piece preys : getPreys()) {
            if (
                    (preys.getX() == x) &&
                            (preys.getY() == y)
            ) {
                prey = preys;
            }
        }

        if (!isInPath && prey == null) {
            return false;
        }

        Piece[][] grid = chessBoard.getGrid();

        grid[this.y][this.x] = null;

        if (prey != null) {
            int preyX = prey.getX();
            int preyY = prey.getY();

            setX(preyX);
            setY(preyY);

            grid[preyY][preyX] = this;
        } else {
            setX(x);
            setY(y);


            grid[y][x] = this;
        }


        chessBoard.calculate();
        chessBoard.removeSelected();

        return true;
    }

    public void recalculate() {
        List<int[]> list = new ArrayList<>();

        for (int[] ints : calculatePaths()) {
            int x = ints[0];
            int y = ints[1];

            if (
                    (x < 8 && x >= 0) &&
                            (y < 8 && y >= 0)
            ) {
                if (getChessBoard().lookupForPiece(x, y) == null)
                    list.add(new int[]{x, y});
            }
        }

        paths = new int[list.size()][];

        for (int i = 0; i < list.size(); i++) {
            paths[i] = list.get(i);
        }

        preys = new ArrayList<>();

        for (Piece piece : calculatePreys()) {
            if (piece == null)
                continue;

            preys.add(piece);
        }
    }

    @Override
    public void update() {
    }

    @Override
    public void render(GraphicsContext context) {
        if (team == Team.WHITE) {
            context.drawImage(whiteImage, x * WIDTH, y * HEIGHT, WIDTH, HEIGHT);
        } else {
            context.drawImage(blackImage, x * WIDTH, y * HEIGHT, WIDTH, HEIGHT);
        }

        if (chessBoard.getSelected() == this) {
            drawSelectedHighlight(context);
            drawPreyHighlight(context);
        }
    }

    public void drawSelectedHighlight(GraphicsContext context) {
        drawHighlight(context, BLUE_SELECTED_TILE, getX(), getY());

        for (int[] direction : getPaths()) {
            int x = direction[0];
            int y = direction[1];

            drawHighlight(context, BLUE_SELECTED_TILE, x, y);

        }
    }

    public void drawPreyHighlight(GraphicsContext context) {
        for (Piece piece : getPreys()) {
            int x = piece.x;
            int y = piece.y;

            drawHighlight(context, ATTACK_TILE, x, y);

        }
    }

    private void drawHighlight(GraphicsContext context, Color color, int x, int y) {
        context.setFill(color);
        context.fillRect(x * WIDTH
                , y * HEIGHT,
                WIDTH, HEIGHT);
    }
}
