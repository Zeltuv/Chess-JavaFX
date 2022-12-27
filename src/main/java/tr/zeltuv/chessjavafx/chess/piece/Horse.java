package tr.zeltuv.chessjavafx.chess.piece;

import tr.zeltuv.chessjavafx.chess.ChessBoard;
import tr.zeltuv.chessjavafx.chess.Piece;
import tr.zeltuv.chessjavafx.chess.team.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Horse extends Piece {
    private static final char ID = 'h';
    private static final String IMAGE_URL_BLACK = "b_knight_png_shadow_128px.png";
    private static final String IMAGE_URL_WHITE = "w_knight_png_shadow_128px.png";

    public Horse(ChessBoard chessBoard, int x, int y, Team team) {
        super(chessBoard, ID, x, y, IMAGE_URL_BLACK, IMAGE_URL_WHITE, team);
    }

    @Override
    public List<Piece> calculatePreys() {
        List<Piece> preys = new ArrayList<>();

        Piece first = getChessBoard().lookupForPiece(getX()+1,getTeam().adjustDirection(getY(),1));
        Piece second = getChessBoard().lookupForPiece(getX()-1,getTeam().adjustDirection(getY(),1));

        preys.add(first);
        preys.add(second);

        return preys.stream().filter(Objects::nonNull)
                .filter(piece -> piece.getTeam()!=this.getTeam()).collect(Collectors.toList());
    }

    @Override
    public int[][] calculatePaths() {
        return new int[][]{
        };
    }

}
