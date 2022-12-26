package tr.zeltuv.chessjavafx.chess.team;

import tr.zeltuv.chessjavafx.chess.Piece;

import java.util.ArrayList;
import java.util.List;

public class Team {

    private TeamColor teamColor;
    private List<Piece> pieces = new ArrayList<>();
    private int direction;

    public Team(TeamColor teamColor, boolean isTop) {
        this.teamColor = teamColor;
        this.direction = isTop?-1:1;
    }

    public TeamColor getTeamColor() {
        return teamColor;
    }

    public List<Piece> getPieces() {
        return pieces;
    }

    public void setPieces(List<Piece> pieces) {
        this.pieces = pieces;
    }

    public int getDirection() {
        return direction;
    }

    public int calculateDirection(int i) {
        return i*direction;
    }
}
