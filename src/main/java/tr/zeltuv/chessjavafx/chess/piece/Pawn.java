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

    private boolean firstPlay = true;

    public Pawn(ChessBoard chessBoard, int x, int y, Team team) {
        super(chessBoard, ID, x, y, IMAGE_URL_BLACK, IMAGE_URL_WHITE, team);
    }

    @Override
    public List<Piece> calculatePreys() {
        List<Piece> preys = new ArrayList<>();

        int x1 =
        int[] secondCoordinate = new int[]{

        };

      /*  if (firstPlay) {
            int y2 = getTeam().adjustDirection(getY(), 2);

            if(y2 <0){
                Piece piece = getChessBoard().lookupForPiece(getX(), y2);
                if(piece!= null && piece.getTeam() != getTeam())
                    preys.add(piece);
            }
        }

        int y1 = getTeam().adjustDirection(getY(), 1);

        if(y1 <0){
            Piece piece = getChessBoard().lookupForPiece(getX(), y1);
            if(piece!= null && piece.getTeam() != getTeam())
                preys.add(piece);
        }
*/
        return preys;
    }

    @Override
    public int[][] calculatePaths() {
        int y = getTeam().adjustDirection(getY(), 1);
        int y2 = getTeam().adjustDirection(getY() , 2);

        if (firstPlay)
            return new int[][]{
                    {getX(), y},
                    {getX(),y2}
            };

        return new int[][]{
                {getX(),y}
        };
    }
}
