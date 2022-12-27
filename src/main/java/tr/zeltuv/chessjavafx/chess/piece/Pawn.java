package tr.zeltuv.chessjavafx.chess.piece;

import tr.zeltuv.chessjavafx.chess.ChessBoard;
import tr.zeltuv.chessjavafx.chess.Piece;
import tr.zeltuv.chessjavafx.chess.team.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Pawn extends Piece {
    private static final char ID = 'p';
    private static final String IMAGE_URL_BLACK = "b_pawn_png_shadow_128px.png";
    private static final String IMAGE_URL_WHITE = "w_pawn_png_shadow_128px.png";

    private boolean firstPlay = true;

    public Pawn(ChessBoard chessBoard, int x, int y, Team team) {
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
    public boolean move(int x, int y) {
        boolean b = super.move(x, y);

        if(b){
            firstPlay = false;
        }

        return b;
    }

    @Override
    public int[][] calculatePaths() {
        int y = getTeam().adjustDirection(getY(), 1);
        int y2 = getTeam().adjustDirection(getY() , 2);

        Piece potential1 = getChessBoard().lookupForPiece(getX(),y);
        Piece potential2 = getChessBoard().lookupForPiece(getX(),y2);

        if(potential1 != null){
            return new int[][]{};
        }

        if (firstPlay) {
            if(potential2 != null){
                return new int[][]{
                        {getX(),y}
                };
            }

            return new int[][]{
                    {getX(), y},
                    {getX(), y2}
            };
        }

        return new int[][]{
                {getX(),y}
        };
    }
}
