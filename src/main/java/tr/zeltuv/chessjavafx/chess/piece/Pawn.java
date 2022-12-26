package tr.zeltuv.chessjavafx.chess.piece;

import tr.zeltuv.chessjavafx.chess.ChessBoard;
import tr.zeltuv.chessjavafx.chess.Piece;
import tr.zeltuv.chessjavafx.chess.team.Team;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {
    private static final char ID = 'p';
    private static final String IMAGE_URL_BLACK = "b_pawn_png_shadow_128px.png";
    private static final String IMAGE_URL_WHITE = "w_pawn_png_shadow_128px.png";

    public Pawn(ChessBoard chessBoard, int x, int y, Team team) {
        super(chessBoard, ID, x, y, IMAGE_URL_BLACK,IMAGE_URL_WHITE, team, false);
    }

    @Override
    public List<Piece> getPreys() {
        return new ArrayList<>();
    }

    @Override
    public int[][] getDirections() {
        return new int[0][];
    }
}
