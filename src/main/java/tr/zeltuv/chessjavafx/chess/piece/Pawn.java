package tr.zeltuv.chessjavafx.chess.piece;

import tr.zeltuv.chessjavafx.chess.ChessBoard;
import tr.zeltuv.chessjavafx.chess.Piece;
import tr.zeltuv.chessjavafx.chess.team.Team;

import java.util.List;

public class Pawn extends Piece {
    private static final String ID = "p";
    private static final String IMAGE_URL = "p";


    public Pawn(ChessBoard chessBoard, String id, int x, int y, String imageName, Team team, boolean canFly) {
        super(chessBoard, id, x, y, imageName, team, canFly);
    }

    @Override
    public List<Piece> getPreys() {
        return null;
    }

    @Override
    public int[][] getDirections() {
        return new int[0][];
    }
}
