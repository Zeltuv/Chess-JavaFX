package tr.zeltuv.chessjavafx.chess.team;

import tr.zeltuv.chessjavafx.chess.Piece;

import java.util.ArrayList;
import java.util.List;

public class Team {

    //TODO convert to enum

    private TeamColor teamColor;
    private int direction;

    public Team(TeamColor teamColor, boolean isTop) {
        this.teamColor = teamColor;
        this.direction = isTop?-1:1;
    }

    public TeamColor getTeamColor() {
        return teamColor;
    }

    public int getDirection() {
        return direction;
    }

    public int calculateDirection(int i) {
        return i*direction;
    }
}
