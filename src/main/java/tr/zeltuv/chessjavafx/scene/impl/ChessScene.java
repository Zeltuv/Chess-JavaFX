package tr.zeltuv.chessjavafx.scene.impl;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import tr.zeltuv.chessjavafx.Game;
import tr.zeltuv.chessjavafx.chess.ChessBoard;
import tr.zeltuv.chessjavafx.chess.Piece;
import tr.zeltuv.chessjavafx.chess.team.Team;
import tr.zeltuv.chessjavafx.scene.GameScene;
import tr.zeltuv.chessjavafx.utils.Util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ChessScene extends GameScene {

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
        chessBoard.render(context);

    }

    @Override
    public void click(MouseButton mouseButton) {
        switch(mouseButton){
            case PRIMARY -> {
                clickPrimary();
            }
        }
    }

    private void clickPrimary(){
        Piece piece = chessBoard.getPieceOnMouse();

        if(piece==null) {
            chessBoard.removeSelected();
        }

        chessBoard.setSelected(piece);
    }



    /*
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
                            playing = playing == Team.WHITE ? Team.BLACK : Team.WHITE;
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
                                playing = playing == Team.WHITE ? Team.BLACK : Team.WHITE;
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

    }*/
}
