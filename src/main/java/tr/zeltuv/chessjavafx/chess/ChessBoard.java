package tr.zeltuv.chessjavafx.chess;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import tr.zeltuv.chessjavafx.Game;
import tr.zeltuv.chessjavafx.chess.piece.Pawn;
import tr.zeltuv.chessjavafx.chess.team.Team;
import tr.zeltuv.chessjavafx.node.GameNode;
import tr.zeltuv.chessjavafx.utils.Util;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessBoard implements GameNode {

    private Color WHITE_COLOR = Color.rgb(173, 222, 208);
    private Color BLACK_COLOR = Color.rgb(40, 79, 67);

    public static Color BLUE_SELECTED_TILE = Color.rgb(0, 255, 255, 0.5);
    public static Color ATTACK_TILE = Color.rgb(255, 30, 5, 0.5);

    public static Map<Character, Class<? extends Piece>> PIECES_CHAR = new HashMap<>() {{
        put('p', Pawn.class);
    }};

    private Team playingTeam = Team.WHITE;

    private Piece[][] grid = new Piece[8][8];

    private Piece selected = null;

    public ChessBoard(List<String> boardTiles) {
        placePieces(boardTiles);

        calculate();
    }

    public void calculate() {
        getAllPieces().forEach(Piece::recalculate);
    }

    public List<Piece> getAllPieces() {
        List<Piece> pieces = new ArrayList<>();

        for (Piece[] pieces1 : grid) {
            for (Piece piece : pieces1) {
                if (piece != null)
                    pieces.add(piece);
            }
        }

        return pieces;
    }

    public void placePieces(List<String> boardTiles) {
        for (int i = 0; i < boardTiles.size(); i++) {
            String row = boardTiles.get(i);

            Piece[] pieces = new Piece[8];

            if (row.contains("_")) {
                grid[i] = pieces;
                continue;
            }

            char[] chars = row.toCharArray();

            for (int x = 0; x < chars.length; x++) {
                char c = chars[x];

                Team team = Team.BLACK;
                Class<? extends Piece> pieceClass = PIECES_CHAR.get(c);

                if (Character.toUpperCase(c) == c) {
                    team = Team.WHITE;
                    pieceClass = PIECES_CHAR.get(Character.toLowerCase(c));
                }

                Piece piece = createPiece(pieceClass, x, i, team);
                pieces[x] = piece;
            }

            grid[i] = pieces;
        }

    }

    public Piece createPiece(Class<? extends Piece> clazz, int x, int y, Team team) {
        try {
            Constructor<? extends Piece> constructor = clazz.getConstructor(
                    ChessBoard.class,
                    int.class,
                    int.class,
                    Team.class
            );

            Piece piece = constructor.newInstance(
                    this,
                    x,
                    y,
                    team
            );

            return piece;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    public Piece getPieceOnMouse() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (Util.contains(
                        Game.MOUSE_X,
                        Game.MOUSE_Y,
                        j * 100,
                        i * 100,
                        100,
                        100
                )) {
                    return grid[i][j];
                }
            }
        }
        return null;
    }

    public int[] getCoordinateOnMouse() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (Util.contains(
                        Game.MOUSE_X,
                        Game.MOUSE_Y,
                        j * 100,
                        i * 100,
                        100,
                        100
                )) {
                    return new int[]{
                            j,i
                    };
                }
            }
        }
        return null;
    }

    @Override
    public void update() {

    }

    @Override
    public void render(GraphicsContext context) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (i % 2 == 0) {
                    if (j % 2 == 0) {
                        context.setFill(WHITE_COLOR);
                    } else {
                        context.setFill(BLACK_COLOR);
                    }
                } else {
                    if (j % 2 == 0) {
                        context.setFill(BLACK_COLOR);
                    } else {
                        context.setFill(WHITE_COLOR);
                    }
                }
                context.fillRect(i * 100, j * 100,
                        (i + 1) * 100,
                        (j + 1) * 100
                );
            }
        }

        for (Piece[] pieces : grid) {
            for (Piece piece : pieces) {
                if (piece != null)
                    piece.render(context);
            }
        }
    }


    public Piece getSelected() {
        return selected;
    }

    public void removeSelected() {
        selected = null;
    }

    public void setSelected(Piece selected) {
        this.selected = selected;
    }

    public Piece lookupForPiece(int x, int y) {
        if(x <0 || x>8)
            return null;

        if(y <0 || y>8)
            return null;

        return grid[y][x];
    }

    public Piece[][] getGrid() {
        return grid;
    }

    public void switchPlayingTeam() {
        playingTeam = playingTeam==Team.WHITE?Team.BLACK:Team.WHITE;
    }

    public Team getPlayingTeam() {
        return playingTeam;
    }
}
