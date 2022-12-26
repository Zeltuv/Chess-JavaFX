package tr.zeltuv.chessjavafx.scene.impl;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import tr.zeltuv.chessjavafx.Game;
import tr.zeltuv.chessjavafx.chess.ChessBoard;
import tr.zeltuv.chessjavafx.chess.Piece;
import tr.zeltuv.chessjavafx.chess.team.TeamColor;
import tr.zeltuv.chessjavafx.chess.impl.Horse;
import tr.zeltuv.chessjavafx.chess.impl.Pion;
import tr.zeltuv.chessjavafx.chess.impl.Rook;
import tr.zeltuv.chessjavafx.scene.GameScene;
import tr.zeltuv.chessjavafx.utils.Util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ChessScene extends GameScene {



    private List<Piece> pieceList = new ArrayList<>();

    private Color WHITE_COLOR = Color.rgb(173, 222, 208);
    private Color BLACK_COLOR = Color.rgb(40, 79, 67);

    public static Color BLUE_SELECTED_TILE = Color.rgb(0, 255, 255, 0.5);
    public static Color ATTACK_TILE = Color.rgb(255, 30, 5, 0.5);

    private ChessBoard chessBoard;

    public ChessScene() {
        super("inGame");

        chessBoard = new ChessBoard(getBoardTiles());

    }

    @Override
    public void display() {

    }

    @Override
    public void update() {

    }

    public List<String> getBoardTiles(){
        File file = Game.CONFIG_DIR.getResource("board.txt");

        return Util.fromFileToLine(file);
    }

    @Override
    public void render(GraphicsContext context) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (i % 2 == 0) {
                    if (j % 2 == 0) {
                        context.setFill(white);
                    } else {
                        context.setFill(black);
                    }
                } else {
                    if (j % 2 == 0) {
                        context.setFill(black);
                    } else {
                        context.setFill(white);
                    }
                }
                context.fillRect(i * 100, j * 100,
                        (i + 1) * 100,
                        (j + 1) * 100
                );
            }
        }

        for (Piece piece : pieceList) {
            piece.render(context);
        }

        if (selected != null) {
            List<Piece> canBeKilled = new ArrayList<>();

            if (!selected.canFly())
                for (int i = 0; i < selected.getPossiblities().length; i++) {
                    int possibleX = selected.getX()+selected.getPossiblities()[i][0];
                    int possibleY = selected.getY()+selected.getPossiblities()[i][1];

                    Piece piece = pieceList.stream().filter(piece1 -> piece1.getX() == (possibleX) &&
                            piece1.getY() == (possibleY)).findFirst().orElse(null);

                    if (piece == null)
                        continue;


                    Piece firstPx = null;

                    for (int j = possibleX; j < 8; j++) {
                        if(firstPx != null)
                            continue;

                        int finalJ = j;
                        firstPx = pieceList.stream().filter(piece1 -> piece1.getX() == finalJ &&
                                piece1.getY() == possibleY).findFirst().orElse(null);

                    }

                    Piece firstMx = null;

                    for (int j = possibleX; j >=0 ; j--) {
                        if(firstMx != null)
                            continue;

                        int finalJ = j;
                        firstMx = pieceList.stream().filter(piece1 -> piece1.getX() == finalJ &&
                                piece1.getY() == possibleY).findFirst().orElse(null);
                    }


                    Piece firstPy = null;

                    for (int j = possibleY; j < 8; j++) {
                        if(firstPy != null)
                            continue;

                        int finalJ = j;
                        firstPy = pieceList.stream().filter(piece1 -> piece1.getX() == possibleX &&
                                piece1.getY() ==finalJ).findFirst().orElse(null);

                    }

                    Piece firstMy = null;

                    for (int j = possibleY; j >=0 ; j--) {
                        if(firstMy != null)
                            continue;

                        int finalJ = j;
                        firstMy = pieceList.stream().filter(piece1 -> piece1.getX() == possibleX &&
                                piece1.getY() == finalJ).findFirst().orElse(null);
                    }

                    if(firstMx != null)
                        canBeKilled.add(firstMx);

                    if(firstMy != null)
                        canBeKilled.add(firstMy);

                    if(firstPx != null)
                        canBeKilled.add(firstPx);

                    if(firstPy != null)
                        canBeKilled.add(firstPy);


                }


            for (int i = 0; i < selected.getPossiblities().length; i++) {

                int finalI = i;

                Piece piece = pieceList.stream().filter(piece1 -> piece1.getX() == (selected.getPossiblities()[finalI][0] + selected.getX()) &&
                        piece1.getY() == (selected.getPossiblities()[finalI][1] + selected.getY())).findFirst().orElse(null);

                if (piece != null) {
                    continue;
                }

                context.setFill(hover);
                context.fillRect((selected.getPossiblities()[i][0] + selected.getX()) * 100,
                        (selected.getPossiblities()[i][1] + selected.getY()) * 100,
                        100,
                        100
                );
            }


            for (int i = 0; i < selected.getAttackPossiblities().length; i++) {


                int finalI = i;

                Piece piece = pieceList.stream().filter(piece1 -> piece1.getX() == (selected.getAttackPossiblities()[finalI][0] + selected.getX()) &&
                        piece1.getY() == (selected.getAttackPossiblities()[finalI][1] + selected.getY())).findFirst().orElse(null);

                if (piece != null && piece.getTeam() != selected.getTeam() && canBeKilled.contains(piece)) {
                    context.setFill(attack);
                    context.fillRect((selected.getAttackPossiblities()[i][0] + selected.getX()) * 100,
                            (selected.getAttackPossiblities()[i][1] + selected.getY()) * 100,
                            100,
                            100);
                }
            }
        }
    }

    @Override
    public void click(MouseButton mouseButton) {
        if (selected != null) {
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
                        int finalJ = j;
                        int finalI = i;
                        Piece piece = pieceList.stream().filter(piece_ -> piece_.getX() == finalJ && piece_.getY() == finalI).findFirst().orElse(null);

                        if (piece == null) {

                            boolean possible = false;

                            for (int k = 0; k < selected.getPossiblities().length; k++) {

                                if (
                                        j == selected.getX() + selected.getPossiblities()[k][0]
                                                &&
                                                i == selected.getY() + selected.getPossiblities()[k][1]

                                ) {
                                    possible = true;
                                }
                            }

                            if (!possible) {
                                selected.setSelected(false);
                                selected = null;
                                return;
                            }

                            selected.move(new int[]{
                                    j, i
                            });
                            playing = playing == TeamColor.WHITE ? TeamColor.BLACK : TeamColor.WHITE;
                            selected.setSelected(false);
                            selected = null;
                            return;
                        } else {
                            boolean possible = false;

                            for (int k = 0; k < selected.getAttackPossiblities().length; k++) {

                                if (
                                        j == selected.getX() + selected.getAttackPossiblities()[k][0]
                                                &&
                                                i == selected.getY() + selected.getAttackPossiblities()[k][1]
                                                && selected.getTeam() != piece.getTeam()
                                ) {
                                    possible = true;
                                }
                            }

                            if (possible) {
                                pieceList.remove(piece);
                                selected.move(new int[]{
                                        j, i
                                });
                                playing = playing == TeamColor.WHITE ? TeamColor.BLACK : TeamColor.WHITE;
                                selected.setSelected(false);
                                selected = null;
                                return;
                            }

                        }

                        selected.setSelected(false);
                        selected = null;
                    }
                }
            }
            return;
        }

        for (Piece piece : pieceList) {
            if (Util.contains(
                    Game.MOUSE_X,
                    Game.MOUSE_Y,
                    piece.getX() * piece.getWidth(),
                    piece.getY() * piece.getHeight(),
                    piece.getWidth(),
                    piece.getHeight()
            )) {
                if (piece.getTeam() == playing) {
                    selected = piece;
                    selected.setSelected(true);
                } else {
                    break;
                }
            }
        }
    }
}
